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
import de.fasterfood.fasterfood.user.UserEntity;
import de.fasterfood.fasterfood.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public HomeController(IngredientRepository ingredientRepository, MealRepository mealRepository, OrderRepository orderRepository,
                          ProcessRepository processRepository, RecipeRepository recipeRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.ingredientRepository = ingredientRepository;
        this.mealRepository = mealRepository;
        this.orderRepository = orderRepository;
        this.processRepository = processRepository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public List<Meal> home(){
        return mealRepository.findAll();
    }

    @PostConstruct
    public void setupData(){

        if (userRepository.count() == 0) {
            userRepository.save(new UserEntity("admin", passwordEncoder.encode("hallo")));

            // Save all Meals to the database
            for (Meal meal: getMealsHashMap().values()) {
                mealRepository.save(meal);
            }

            // Available Ingredients: "Pita", "Wrap", "Baguette", "Beef", "Falafel", "Chicken", "Salad", "Tomatos", "Cucumber"
            // "Cabbage", "Onions", "Cocktail sauce", "Tomato sauce", "Garlic sauce", "Spicy sauce", "Bottle of Water - Bonaqua"
            //
            List<Ingredient> beef_kebab_ingredients = getIngredientsList("Pita", "Beef", "Salad", "Tomatos", "Spicy sauce");
            List<Ingredient> chicken_kebab_ingredients = getIngredientsList("Pita", "Chicken", "Salad", "Tomatos", "Cocktail sauce");
            List<Ingredient> falafel_kebab_ingredients = getIngredientsList("Falafel", "Beef", "Salad", "Tomatos", "Spicy sauce");
            List<Ingredient> falafel_durum_ingredients = getIngredientsList("Falafel", "Beef", "Salad", "Tomatos");
            List<Ingredient> turkishPizza_ingredients = getIngredientsList("Falafel", "Beef", "Salad", "Tomatos", "Garlic sauce");
            List<Ingredient> beef_durum_ingredients = getIngredientsList("Falafel", "Beef", "Salad", "Tomatos");
            List<Ingredient> chicken_durum_ingredients = getIngredientsList("Falafel", "Beef", "Salad", "Tomatos", "Garlic sauce");
            List<Ingredient> crog_ingredients = getIngredientsList("Falafel", "Beef", "Salad", "Tomatos");
            List<List<Ingredient>> listOfIngredientsList = List.of(beef_kebab_ingredients, chicken_kebab_ingredients, falafel_kebab_ingredients,
                    falafel_durum_ingredients,turkishPizza_ingredients,beef_durum_ingredients, chicken_durum_ingredients, crog_ingredients);
            // TODO Getränke noch hinzufügen und allgemein Liste korrigieren

            // Save all ingredients to the database
            for (Ingredient ingredient: getIngredientHashMap().values()) {
                ingredientRepository.save(ingredient);
            }

            // generate Random Orders
            for (int i = 0; i < 9; i++) {
                generateRandomOrders();
            }

            // Recipes are generated
            int i = 1;
            int j = 0;
            for (List<Ingredient> ingredientList : listOfIngredientsList) {
                for ( Ingredient ingredient : ingredientList ){
                    Recipe recipe = new Recipe(mealRepository.findByName(getMealTypes().get(j)).getId(), ingredientRepository.findByName(ingredient.getName()), i);
                    recipeRepository.save(recipe);
                    j++;
                }
                j = 0;
            }
        }
    }


    public void generateRandomOrders() {
        Random random = new Random();
        int randomnumber;
        List<Process> processList = new LinkedList<>();

        randomnumber = random.nextInt(3)+1;

        for (int i = 1; i < randomnumber; i++) {
            randomnumber = random.nextInt(9)+1;
            processList.add(new Process(mealRepository.findByName(getMealTypes().get(randomnumber)), mealRepository.findByName(getMealTypes().get(randomnumber)).getRetailPrice()));
        }

        Order order = new Order(LocalDate.now().minusDays(random.nextInt(2)), LocalTime.now().minusHours(random.nextInt(2)), processList);

        for (Process process : processList){
            processRepository.save(process);
        }

        orderRepository.save(order);

        for (Process process : processList){
            process.setOrder(order);
            processRepository.save(process);
        }
    }


    public List<Ingredient> getIngredientsList(String... ingList) {

        Map<String, Ingredient> ingredientMap = getIngredientHashMap();
        List<Ingredient> returnIngredientsList = new ArrayList<>();

        for (String ingredient : ingList) {
            returnIngredientsList.add(ingredientMap.get(ingredient));
        }

        return returnIngredientsList;
    }

//    public String getRandomMealType() {
//
//        Random random = new Random();
//        return getMealTypes().get(random.nextInt(getMealsHashMap().size()-1));
//    }

    public List<String> getMealTypes() {
        List<String> mealTypeList = new ArrayList<>();
        for(String mealName: getMealsHashMap().keySet()) {
            mealTypeList.add(mealName);
        }
        return mealTypeList;
    }

    public Map<String, Meal> getMealsHashMap() {

        Map<String, Meal> mealsMap = new LinkedHashMap<>();

        mealsMap.put("Beef Kebab", new Meal("Beef Kebab", 4.50));
        mealsMap.put("Chicken Kebab", new Meal("Chicken Kebab", 4.50));
        mealsMap.put("Falafel Kebab", new Meal("Falafel Kebab", 4));
        mealsMap.put("Falafel Durum", new Meal("Falafel Durum", 4.5));
        mealsMap.put("Turkish Pizza", new Meal("Turkish Pizza", 3.5));
        mealsMap.put("Beef Durum", new Meal("Beef Durum", 5));
        mealsMap.put("Chicken Durum", new Meal("Chicken Durum", 5));
        mealsMap.put("Croque", new Meal("Croque", 4));
        mealsMap.put("Salad", new Meal("Salad", 4.20));
        mealsMap.put("Coke", new Meal("Coke", 1.5));
        mealsMap.put("Turkish Tea", new Meal("Turkish Tea", 1.2));
        mealsMap.put("Fanta", new Meal("Fanta", 1.5));
        mealsMap.put("Water", new Meal("Water", 1.5));
        mealsMap.put("Beer", new Meal("Beer", 2.5));
        mealsMap.put("Rum", new Meal("Rum", 3));

        return mealsMap;
    }

    public Map<String, Ingredient> getIngredientHashMap() {

        Map<String, Ingredient> ingredientMap = new LinkedHashMap<>();

        ingredientMap.put("Pita", new Ingredient("Pita", 0.4, 300));
        ingredientMap.put("Wrap", new Ingredient("Wrap", 0.6, 400));
        ingredientMap.put("Baguette", new Ingredient("Baguette", 0.6, 100));
        ingredientMap.put("Beef", new Ingredient("Beef", 0.6, 60000));
        ingredientMap.put("Falafel", new Ingredient("Falafel", 0.4, 1000));
        ingredientMap.put("Chicken", new Ingredient("Chicken", 0.5, 2000));
        ingredientMap.put("Salad", new Ingredient("Salad", 0.1, 3000));
        ingredientMap.put("Tomatos", new Ingredient("Tomatos", 0.3, 2500));
        ingredientMap.put("Cucumber", new Ingredient("Cucumber", 0.2, 1500));
        ingredientMap.put("Cabbage", new Ingredient("Cabbage", 0.2, 1000));
        ingredientMap.put("Onions", new Ingredient("Onions", 0.1, 9000));
        ingredientMap.put("Cocktail sauce", new Ingredient("Cocktail sauce", 0.4, 500));
        ingredientMap.put("Tomato sauce", new Ingredient("Tomato sauce", 0.2, 200));
        ingredientMap.put("Garlic sauce", new Ingredient("Garlic sauce", 0.4, 400));
        ingredientMap.put("Spicy sauce", new Ingredient("Spicy sauce", 0.4, 500));
        ingredientMap.put("Bottle of Water - Bonaqua", new Ingredient("Bottle of Water - Bonaqua", 0.03, 400));

        return ingredientMap;
    }
}
