package de.fasterfood.fasterfood.order;

import de.fasterfood.fasterfood.editMeal.Meal;
import de.fasterfood.fasterfood.ingredient.Ingredient;
import de.fasterfood.fasterfood.ingredient.IngredientService;
import de.fasterfood.fasterfood.process.Process;
import de.fasterfood.fasterfood.process.ProcessRepository;
import de.fasterfood.fasterfood.recipe.Recipe;
import de.fasterfood.fasterfood.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.Receiver;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;


@Service
public class OrderService {

    private OrderRepository orderRepository;
    private ProcessRepository processRepository;
    private IngredientService ingredientService;
    private RecipeRepository recipeRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProcessRepository processRepository, IngredientService ingredientService, RecipeRepository recipeRepository){
        this.orderRepository = orderRepository;
        this.processRepository = processRepository;
        this.ingredientService = ingredientService;
        this.recipeRepository = recipeRepository;
    }

    public void addOrderandProcess(List<Meal> meals) {
        decreaseStock(meals);
        List<Process> processes = new LinkedList<>();
        List<Process> newProcesses = new LinkedList<>();
        for (Meal meal : meals) {
            if (!processes.isEmpty()) {
                for (int i = 0; i<processes.size(); i++) {
                    if (processes.get(i).getMeal().getId() == meal.getId()) {
                        processes.get(i).increaseQuantity();
                        break;

                    } else {
                        Process newProcess = new Process(meal, meal.getRetailPrice());
                        if (processes.contains(newProcess)) {  // check @Override Process.equals()
                            processes.get(processes.indexOf(newProcess)).increaseQuantity();

                        } else {
                            newProcesses.add(newProcess);
                        }
                        break;
                    }

                }

            } else {
                Process newProcess = new Process(meal, meal.getRetailPrice());
                newProcesses.add(newProcess);
            }

            processes = newProcesses;
        }
        for (Process process : processes) {
            processRepository.save(process);
        }
        Order order = new Order(LocalDate.now(), LocalTime.now(), processes);
        orderRepository.save(order);
    }

    public void decreaseStock(List<Meal> meals){

        for (Meal meal : meals) {
            List<Recipe> recipe = recipeRepository.findAllByMealId(meal.getId());
            for (Recipe instruction : recipe ){
                Ingredient ingredient = instruction.getIngredient();
                int amount = instruction.getAmount();
                ingredientService.decreaseStockFromOrder(ingredient, -amount);
            }
        }
    }

    public int orderCheck(List<Meal> meals){
        for (Meal meal : meals) {
            List<Recipe> recipes = recipeRepository.findAllByMealId(meal.getId());
            for (Recipe instruction : recipes ){
                Ingredient ingredient = instruction.getIngredient();
                int amount = instruction.getAmount();
                if (ingredient.getStock() < amount){
                    return 0;
                }
            }
        }
        return 1;
    }
}
