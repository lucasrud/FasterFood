package de.fasterfood.fasterfood.order;

import de.fasterfood.fasterfood.editMeal.Meal;
import de.fasterfood.fasterfood.editMeal.MealRepository;
import de.fasterfood.fasterfood.ingredient.Ingredient;
import de.fasterfood.fasterfood.ingredient.IngredientService;
import de.fasterfood.fasterfood.process.Process;
import de.fasterfood.fasterfood.process.ProcessRepository;
import de.fasterfood.fasterfood.recipe.Recipe;
import de.fasterfood.fasterfood.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.sound.midi.Receiver;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@Service
public class OrderService {

    private MealRepository mealRepository;
    private OrderRepository orderRepository;
    private ProcessRepository processRepository;
    private IngredientService ingredientService;
    private RecipeRepository recipeRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProcessRepository processRepository,
                        IngredientService ingredientService, RecipeRepository recipeRepository,
                        MealRepository mealRepository){
        this.orderRepository = orderRepository;
        this.processRepository = processRepository;
        this.ingredientService = ingredientService;
        this.recipeRepository = recipeRepository;
        this.mealRepository = mealRepository;
    }


    public void addOrderandProcess(List<Meal> meals) {
        decreaseStock(meals);

        HashMap<Integer, Integer> map = generateMap(meals);
        List <Process> processes = generateProcessesFromMap(map);

        for (Process process : processes){
            processRepository.save(process);
        }

        Order order = new Order(LocalDate.now(), LocalTime.now(), processes);
        orderRepository.save(order);
    }


    private HashMap<Integer, Integer> generateMap(List<Meal> meals){
        HashMap<Integer, Integer> map = new HashMap<>();

        for (Meal meal : meals){

            if(!map.containsKey(meal.getId())){
                map.put(meal.getId(), 1);
            } else{
                int first = map.get(meal.getId());
                int sum = first + 1;
                map.put(meal.getId(), sum);
            }

        }

        return map;
    }

    public List<Process> generateProcessesFromMap(HashMap<Integer, Integer> map){
        List<Process> processes = new LinkedList<>();

        for (Integer mealId : map.keySet()){

            Optional<Meal> meal = mealRepository.findById(mealId);
            double price = meal.get().getRetailPrice() * map.get(mealId);

            Process newProcess = new Process(meal.get(), price);
            newProcess.setQuantity(map.get(mealId));

            processes.add(newProcess);
        }

        return processes;
    }

    private void decreaseStock(List<Meal> meals) {
        for (Meal meal : meals) {
            List<Recipe> recipes = recipeRepository.findAllByMealId(meal.getId());
            for (Recipe instruction : recipes) {
                Ingredient ingredient = instruction.getIngredient();
                int amount = instruction.getAmount();
                ingredientService.decreaseStockFromOrder(ingredient, -amount);
            }
        }
    }


    public int orderCheck(List<Meal> meals){
        HashMap<Ingredient, Integer> map = new HashMap<>();
        for (Meal meal : meals) {
            List<Recipe> recipes = recipeRepository.findAllByMealId(meal.getId());
            for (Recipe instruction : recipes ){
                Ingredient ingredient = instruction.getIngredient();
                int amount = instruction.getAmount();

                if(!map.containsKey(ingredient)){
                    map.put(ingredient, amount);
                }else{
                    int first = map.get(ingredient);
                    int sum = first + amount;
                    map.put(ingredient, sum);
                }
            }
        }

        for (Ingredient ingredient : map.keySet()){
            if (ingredient.getStock() < map.get(ingredient)){
                return 0;
            }
        }
        return 1;

    }
 }
