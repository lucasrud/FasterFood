package de.fasterfood.fasterfood.order;

import de.fasterfood.fasterfood.editMeal.Meal;
import de.fasterfood.fasterfood.editMeal.MealRepository;
import de.fasterfood.fasterfood.ingredient.Ingredient;
import de.fasterfood.fasterfood.ingredient.IngredientRepository;
import de.fasterfood.fasterfood.ingredient.IngredientService;
import de.fasterfood.fasterfood.process.Process;
import de.fasterfood.fasterfood.process.ProcessRepository;
import de.fasterfood.fasterfood.recipe.Recipe;
import de.fasterfood.fasterfood.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@Service
public class OrderService {

    private MealRepository mealRepository;
    private OrderRepository orderRepository;
    private ProcessRepository processRepository;
    private IngredientService ingredientService;
    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;
    private HashMap<Integer, Integer> ingredients;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProcessRepository processRepository,
                        IngredientService ingredientService, RecipeRepository recipeRepository,
                        MealRepository mealRepository, IngredientRepository ingredientRepository) {
        this.orderRepository = orderRepository;
        this.processRepository = processRepository;
        this.ingredientService = ingredientService;
        this.recipeRepository = recipeRepository;
        this.mealRepository = mealRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredients = new HashMap<>();
    }

    public void resetMap() {
        this.ingredients = new HashMap<>();
    }

    public void addOrderandProcess(List<Meal> meals) {
        decreaseStock();

        HashMap<Integer, Integer> map = generateMap(meals);
        List<Process> processes = generateProcessesFromMap(map);

        Order order = new Order(LocalDate.now(), LocalTime.now(), processes);
        orderRepository.save(order);
        for (Process process : processes) {
            process.setOrder(order);
            processRepository.save(process);
        }
    }


    private HashMap<Integer, Integer> generateMap(List<Meal> meals) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (Meal meal : meals) {

            if (!map.containsKey(meal.getId())) {
                map.put(meal.getId(), 1);
            } else {
                int first = map.get(meal.getId());
                int sum = first + 1;
                map.put(meal.getId(), sum);
            }

        }

        return map;
    }

    public List<Process> generateProcessesFromMap(HashMap<Integer, Integer> map) {
        List<Process> processes = new LinkedList<>();

        for (Integer mealId : map.keySet()) {

            Optional<Meal> meal = mealRepository.findById(mealId);
            double price = meal.get().getRetailPrice() * map.get(mealId);

            Process newProcess = new Process(meal.get(), price);
            newProcess.setQuantity(map.get(mealId));

            processes.add(newProcess);
        }

        return processes;
    }

    private void decreaseStock() {
        for (Integer ingredientId : ingredients.keySet()) {
            Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
            int amount = ingredients.get(ingredientId);
            ingredientService.decreaseStockFromOrder(ingredient.get(), -amount);
        }
        ingredients = new HashMap<>();
    }

    public boolean generateIngredientMap(Meal meal, String action) {
        List<Recipe> recipes = recipeRepository.findAllByMealId(meal.getId());

        for (Recipe recipe : recipes) {
            int id = recipe.getIngredient().getId();

            if (ingredients.containsKey(id)) {
                int newVal = 0;
                if (action.equals("+")) {
                    newVal = recipe.getAmount() + ingredients.get(id);
                } else {
                    newVal = ingredients.get(id) - recipe.getAmount();
                }
                ingredients.put(id, newVal);
            } else {
                ingredients.put(id, recipe.getAmount());
            }
        }
        return orderCheck(meal);
    }


    public boolean orderCheck(Meal meal) {

        for (Integer ingredientId : ingredients.keySet()) {
            Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
            Recipe recipe = recipeRepository.findByIngredientIdAndMealId(ingredientId, meal.getId());

            if (ingredients.get(ingredientId) > ingredient.get().getStock()) {
                ingredients.put(ingredientId,ingredients.get(ingredientId)-recipe.getAmount());
                return false;
            }
        }
        return true;
    }

}
