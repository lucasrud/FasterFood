package de.fasterfood.fasterfood.main;

import de.fasterfood.fasterfood.ingredient.Ingredient;
import de.fasterfood.fasterfood.ingredient.IngredientRepository;
import de.fasterfood.fasterfood.editMeal.Meal;
import de.fasterfood.fasterfood.editMeal.MealRepository;
import de.fasterfood.fasterfood.order.Order;
import de.fasterfood.fasterfood.order.OrderRepository;
import de.fasterfood.fasterfood.process.Process;
import de.fasterfood.fasterfood.process.ProcessRepository;
import de.fasterfood.fasterfood.recipe.Recipe;
import de.fasterfood.fasterfood.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@RestController
public class HomeController {

    private IngredientRepository ingredientRepository;
    private MealRepository mealRepository;
    private OrderRepository orderRepository;
    private ProcessRepository processRepository;
    private RecipeRepository recipeRepository;

    @Autowired
    public HomeController(IngredientRepository ingredientRepository, MealRepository mealRepository, OrderRepository orderRepository,
                          ProcessRepository processRepository, RecipeRepository recipeRepository){
        this.ingredientRepository = ingredientRepository;
        this.mealRepository = mealRepository;
        this.orderRepository = orderRepository;
        this.processRepository = processRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/")
    public List<Meal> home(){
        return mealRepository.findAll();
    }

    @PostConstruct
    public void setupData(){

        if (mealRepository.count() == 0) {
            Ingredient cowMeat = new Ingredient("cowMeat", 0.4, 20);
            Ingredient salad = new Ingredient("salad", 0.4, 20);
            Ingredient pita = new Ingredient("pita", 0.4, 20);
            Ingredient sauce = new Ingredient("sauce", 0.4, 20);
            Ingredient veggies = new Ingredient("veggies", 0.4, 20);
            Ingredient dough = new Ingredient("dough", 0.4, 20);
            Ingredient test1 = new Ingredient("DeleteableNoDependency1", 0.25, 750);
            Ingredient test2 = new Ingredient("DeleteableNoDependency2", 0.12, 1200);
            Ingredient test3 = new Ingredient("DeleteableNoDependency3", 0.07, 380);
            Ingredient test4 = new Ingredient("DeleteableNoDependency4", 0.03, 8400);
            Ingredient wasserflasche = new Ingredient("Bottle of Water - Bonaqua", 0.03, 8400);



            List<Ingredient> ingredients = new LinkedList<>();
            ingredients.add(cowMeat);
            ingredients.add(salad);
            ingredients.add(pita);
            ingredients.add(sauce);
            ingredients.add(veggies);
            ingredients.add(dough);


            HashMap<String, Integer> amountOfIng = new HashMap<>();
            Random random = new Random();

            for ( Ingredient ingredient : ingredients ){
                amountOfIng.put(ingredient.getName(), random.nextInt(8+1));
            }


            Meal kebab = new Meal("kebab", 4.50);
            Meal wrap = new Meal("pizza", 7);
            Meal turkishPizza = new Meal("turkishPizza", 3.5);
            Meal durum = new Meal("durum", 5);
            Meal crog = new Meal("crog", 6);
            Meal salat = new Meal("Salat", 4.20);
            Meal coca = new Meal("Coke", 1.5);
            Meal tee = new Meal("Tee", 1.2);
            Meal fanta = new Meal("Fanta", 1.5);
            Meal wasser = new Meal("Wasser", 1.1);
            Meal bier = new Meal("Bier", 2.5);
            Meal rum = new Meal("Rum", 3);



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

            ingredientRepository.save(test1);    // without dependency to a meal
            ingredientRepository.save(test2);    // without dependency to a meal
            ingredientRepository.save(test3);    // without dependency to a meal
            ingredientRepository.save(test4);    // without dependency to a meal
            ingredientRepository.save(wasserflasche);    // has dependency to Water

            mealRepository.save(kebab);
            mealRepository.save(turkishPizza);
            mealRepository.save(crog);
            mealRepository.save(durum);
            mealRepository.save(wrap);
            mealRepository.save(salat);
            mealRepository.save(coca);
            mealRepository.save(tee);
            mealRepository.save(fanta);
            mealRepository.save(wasser);
            mealRepository.save(bier);
            mealRepository.save(rum);


            int i = 1;
            for ( Ingredient ingredient : ingredients ){
                Recipe recipe = new Recipe(kebab.getId(), ingredient, i);
                Recipe recipe2 = new Recipe(turkishPizza.getId(), ingredient, i);
                recipeRepository.save(recipe);
                recipeRepository.save(recipe2);
                i++;
            }
            Recipe recipe3 = new Recipe(wasser.getId(), wasserflasche, 1);
            recipeRepository.save(recipe3);

            for (Process process : processes){
                processRepository.save(process);
            }

            orderRepository.save(order);
        }
    }
}
