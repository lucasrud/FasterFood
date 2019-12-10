package de.fasterfood.fasterfood.main;

import de.fasterfood.fasterfood.ingredient.Ingredient;
import de.fasterfood.fasterfood.ingredient.IngredientRepository;
import de.fasterfood.fasterfood.meal.Meal;
import de.fasterfood.fasterfood.meal.MealRepository;
import de.fasterfood.fasterfood.order.Order;
import de.fasterfood.fasterfood.order.OrderRepository;
import de.fasterfood.fasterfood.process.Process;
import de.fasterfood.fasterfood.process.ProcessRepository;
import de.fasterfood.fasterfood.recipe.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


// Muss hier nicht noch eine Annotation hin? AK
@RestController
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

    @GetMapping("/")
    public List<Meal> home(){
        return mealRepository.findAll();
    }

    @PostConstruct
    public void setupData(){

        if (mealRepository.count() == 0) {
            Ingredient cowMeat = new Ingredient("cowMeat", 0.4, 20);
            Ingredient salad = new Ingredient("cowMeat", 0.4, 20);
            Ingredient pita = new Ingredient("pita", 0.4, 20);
            Ingredient sauce = new Ingredient("sauce", 0.4, 20);
            Ingredient veggies = new Ingredient("veggies", 0.4, 20);
            Ingredient dough = new Ingredient("dough", 0.4, 20);

            List<Ingredient> ingredients = new LinkedList<>();
            ingredients.add(cowMeat);
            ingredients.add(salad);
            ingredients.add(pita);
            ingredients.add(sauce);
            ingredients.add(veggies);

            HashMap<String, Integer> amountOfIng = new HashMap<>();
            Random random = new Random();

            for ( Ingredient ingredient : ingredients ){
                amountOfIng.put(ingredient.getName(), random.nextInt(8+1));
            }



            Meal kebab = new Meal("kebab", 6, ingredients, amountOfIng);
            Meal wrap = new Meal("pizza", 6, ingredients, amountOfIng);
            Meal turkishPizza = new Meal("turkishPizza", 6, ingredients, amountOfIng);
            Meal durum = new Meal("durum", 6, ingredients, amountOfIng);
            Meal crog = new Meal("crog", 6, ingredients, amountOfIng);


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
            mealRepository.save(turkishPizza);
            mealRepository.save(crog);
            mealRepository.save(durum);
            mealRepository.save(wrap);

            for (Process process : processes){
                processRepository.save(process);
            }

            orderRepository.save(order);
        }

    }
}
