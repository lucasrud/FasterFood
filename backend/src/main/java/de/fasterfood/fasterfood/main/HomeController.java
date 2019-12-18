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
            Ingredient pita = new Ingredient("Pita", 0.4, 300);
            Ingredient dough = new Ingredient("Dough", 0.1, 5000);
            Ingredient wrap = new Ingredient("Wrap", 0.6, 400);
            Ingredient baguette = new Ingredient("Baguette", 0.6, 100);
            Ingredient beef = new Ingredient("Beef", 0.6, 20000);
            Ingredient falafel = new Ingredient("Falafel", 0.4, 10000);
            Ingredient chicken = new Ingredient("Chicken", 0.5, 20000);
            Ingredient salad = new Ingredient("Salad", 0.1, 30000);
            Ingredient tomatos = new Ingredient("Tomatos", 0.3, 25000);
            Ingredient cucumber = new Ingredient("Cucumber", 0.2, 15000);
            Ingredient cabbage = new Ingredient("Cabbage", 0.2, 10000);
            Ingredient onion = new Ingredient("Onions", 0.1, 20000);
            Ingredient cocktailSauce = new Ingredient("Cocktail sauce", 0.4, 500);
            Ingredient tomatoSauce = new Ingredient("Tomato sauce", 0.2, 200);
            Ingredient garlicSauce = new Ingredient("Garlic sauce", 0.4, 400);
            Ingredient spicySauce = new Ingredient("Spicy sauce", 0.4, 500);

            Ingredient wasserflasche = new Ingredient("Bottle of Water - Bonaqua", 0.03, 400);



            List<Ingredient> ingredients = new LinkedList<>();
            ingredients.add(dough);
//            ingredients.add(beef);
//            ingredients.add(salad);
//            ingredients.add(pita);
//            ingredients.add(sauce);
//            ingredients.add(veggies);


            HashMap<String, Integer> amountOfIng = new HashMap<>();
            Random random = new Random();

            for ( Ingredient ingredient : ingredients ){
                amountOfIng.put(ingredient.getName(), random.nextInt(8+1));
            }


            Meal beef_kebab = new Meal("Beef Kebab", 4.50);
            Meal chicken_kebab = new Meal("Chicken Kebab", 4.50);
            Meal falafel_kebab = new Meal("Falafel Kebab", 4);
            Meal falafel_durum = new Meal("Falafel Durum", 4.5);
            Meal turkishPizza = new Meal("Turkish Pizza", 3.5);
            Meal beef_durum = new Meal("Beef Durum", 5);
            Meal chicken_durum = new Meal("Chicken Durum", 5);
            Meal crog = new Meal("Croque", 4);
            Meal salat = new Meal("Salad", 4.20);
            Meal coca = new Meal("Coke", 1.5);
            Meal tee = new Meal("Turkish Tea", 1.2);
            Meal fanta = new Meal("Fanta", 1.5);
            Meal wasser = new Meal("Water", 1.5);
            Meal bier = new Meal("Beer", 2.5);
            Meal rum = new Meal("Rum", 3);



            Process process0 = new Process(beef_kebab, beef_kebab.getRetailPrice());
            Process process1 = new Process(beef_kebab, beef_kebab.getRetailPrice());

            List<Process> processes = new LinkedList<>();
            processes.add(process0);
            processes.add(process1);

            LocalDate today = LocalDate.now();
            LocalTime now = LocalTime.now();

            Order order = new Order(today, now, processes);

            for (Ingredient ingredient : ingredients){
                ingredientRepository.save(ingredient);
            }


            ingredientRepository.save(wasserflasche); // has dependency to Water
            ingredientRepository.save(pita);
            ingredientRepository.save(wrap);
            ingredientRepository.save(baguette);
            ingredientRepository.save(beef);
            ingredientRepository.save(chicken);
            ingredientRepository.save(falafel);
            ingredientRepository.save(salad);
            ingredientRepository.save(tomatos);
            ingredientRepository.save(cucumber);
            ingredientRepository.save(cabbage);
            ingredientRepository.save(onion);
            ingredientRepository.save(cocktailSauce);
            ingredientRepository.save(garlicSauce);
            ingredientRepository.save(spicySauce);
            ingredientRepository.save(tomatoSauce);

            mealRepository.save(beef_kebab);
            mealRepository.save(chicken_kebab);
            mealRepository.save(turkishPizza);
            mealRepository.save(crog);
            mealRepository.save(chicken_durum);
            mealRepository.save(beef_durum);
            mealRepository.save(falafel_durum);
            mealRepository.save(falafel_kebab);
            mealRepository.save(salat);
            mealRepository.save(coca);
            mealRepository.save(tee);
            mealRepository.save(fanta);
            mealRepository.save(wasser);
            mealRepository.save(bier);
            mealRepository.save(rum);


            int i = 1;
            for ( Ingredient ingredient : ingredients ){
                Recipe recipe = new Recipe(beef_kebab.getId(), ingredient, i);
                Recipe recipe2 = new Recipe(turkishPizza.getId(), ingredient, i);
                recipeRepository.save(recipe);
                recipeRepository.save(recipe2);
            }
            Recipe recipe3 = new Recipe(wasser.getId(), wasserflasche, 1);
            recipeRepository.save(recipe3);

            for (Process process : processes){
                processRepository.save(process);
            }

            orderRepository.save(order);

            for (Process process : processes){
                process.setOrder(order);
                processRepository.save(process);
            }
        }
    }
}
