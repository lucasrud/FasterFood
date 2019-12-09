package de.fasterfood.fasterfood.main;

import de.fasterfood.fasterfood.ingredient.Ingredient;
import de.fasterfood.fasterfood.ingredient.IngredientRepository;
import de.fasterfood.fasterfood.meal.Meal;
import de.fasterfood.fasterfood.meal.MealRepository;
import de.fasterfood.fasterfood.order.Order;
import de.fasterfood.fasterfood.order.OrderRepository;
import de.fasterfood.fasterfood.process.Process;
import de.fasterfood.fasterfood.process.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;


public class HomeController {

    private IngredientRepository ingredientRepository;
    private MealRepository mealRepository;
    private OrderRepository orderRepository;
    private ProcessRepository processRepository;

    @Autowired
    public HomeController(IngredientRepository ingredientRepository, MealRepository mealRepository, OrderRepository orderRepository,
                          ProcessRepository processRepository){
        this.ingredientRepository = ingredientRepository;
        this.mealRepository = mealRepository;
        this.orderRepository = orderRepository;
        this.processRepository = processRepository;
    }

    @PostConstruct
    public void setupData(){

        Ingredient cowMeat = new Ingredient("cowMeat", 0.4, 20);
        Ingredient salad = new Ingredient("cowMeat", 0.4, 20);
        Ingredient pita = new Ingredient("pita", 0.4, 20);
        Ingredient sauce = new Ingredient("sauce", 0.4, 20);
        Ingredient veggies = new Ingredient("veggies", 0.4, 20);

        List<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(cowMeat);
        ingredients.add(salad);
        ingredients.add(pita);
        ingredients.add(sauce);
        ingredients.add(veggies);

        Meal kebab = new Meal("kebab", 6, ingredients);

        Process process0 = new Process(kebab, kebab.getRetailPrice());
        Process process1 = new Process(kebab, kebab.getRetailPrice());

        List<Process> processes = new LinkedList<>();
        processes.add(process0);
        processes.add(process1);

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        Order order = new Order(today, now, processes);

        for (Ingredient ingredient : ingredients){
            ingredientRepository.save(ingredient);
        }
        mealRepository.save(kebab);

        for (Process process : processes){
            processRepository.save(process);
        }

        orderRepository.save(order);

    }
}
