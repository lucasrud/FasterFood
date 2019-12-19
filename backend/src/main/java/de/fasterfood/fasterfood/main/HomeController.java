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

                          ProcessRepository processRepository, RecipeRepository recipeRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.ingredientRepository = ingredientRepository;
        this.mealRepository = mealRepository;
        this.orderRepository = orderRepository;
        this.processRepository = processRepository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public List<Meal> home() {
        return mealRepository.findAll();
    }

    @PostConstruct
    public void setupData() {

        if (userRepository.count() == 0) {
            userRepository.save(new UserEntity("admin", passwordEncoder.encode("hallo")));
        }

        if (mealRepository.count() == 0) {

            Ingredient pita = new Ingredient("Pita", 0.4, 300);
            Ingredient dough = new Ingredient("Dough", 0.1, 2);
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

            for (Ingredient ingredient : ingredients) {
                amountOfIng.put(ingredient.getName(), random.nextInt(8 + 1));
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

            for (Ingredient ingredient : ingredients) {
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

            for (Ingredient ingredient : ingredients) {
                Recipe recipe = new Recipe(beef_kebab.getId(), ingredient, i);
                Recipe recipe2 = new Recipe(turkishPizza.getId(), ingredient, i);
                recipeRepository.save(recipe);
                recipeRepository.save(recipe2);
            }

            Recipe recipe3 = new Recipe(wasser.getId(), wasserflasche, 1);
            recipeRepository.save(recipe3);

            for (Meal meal : mealRepository.findAll()) {

                List<Recipe> recipesForMeal = recipeRepository.findAllByMealId(meal.getId());
                double sum = 0;

                for (Recipe recipe : recipesForMeal) {
                    sum += recipe.getIngredient().getPurchasePrice() * recipe.getAmount();
                }

                meal.setPurchasePrice(sum);
                mealRepository.save(meal);

            }

            for (Process process : processes) {
                processRepository.save(process);
            }

            orderRepository.save(order);

            for (Process process : processes) {

                process.setOrder(order);
                processRepository.save(process);
            }
        }
    }
}


//package de.fasterfood.fasterfood.main;
//
//import de.fasterfood.fasterfood.ingredient.Ingredient;
//import de.fasterfood.fasterfood.ingredient.IngredientRepository;
//import de.fasterfood.fasterfood.editMeal.Meal;
//import de.fasterfood.fasterfood.editMeal.MealRepository;
//import de.fasterfood.fasterfood.order.Order;
//import de.fasterfood.fasterfood.order.OrderRepository;
//import de.fasterfood.fasterfood.process.Process;
//import de.fasterfood.fasterfood.process.ProcessRepository;
//import de.fasterfood.fasterfood.recipe.Recipe;
//import de.fasterfood.fasterfood.recipe.RecipeRepository;
//import de.fasterfood.fasterfood.user.UserEntity;
//import de.fasterfood.fasterfood.user.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import javax.annotation.PostConstruct;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.*;
//
//
//@RestController
//public class HomeController {
//
//    private IngredientRepository ingredientRepository;
//    private MealRepository mealRepository;
//    private OrderRepository orderRepository;
//    private ProcessRepository processRepository;
//    private RecipeRepository recipeRepository;
//    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public HomeController(IngredientRepository ingredientRepository, MealRepository mealRepository, OrderRepository orderRepository,
//                          ProcessRepository processRepository, RecipeRepository recipeRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
//        this.ingredientRepository = ingredientRepository;
//        this.mealRepository = mealRepository;
//        this.orderRepository = orderRepository;
//        this.processRepository = processRepository;
//        this.recipeRepository = recipeRepository;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @GetMapping("/")
//    public List<Meal> home(){
//        return mealRepository.findAll();
//    }
//
//    @PostConstruct
//    public void setupData(){
//
//        if (userRepository.count() == 0) {
//            userRepository.save(new UserEntity("admin", passwordEncoder.encode("hallo")));
//
//            // Save all Meals to the database
//            for (Meal meal: getMealsHashMap().values()) {
//                mealRepository.save(meal);
//            }
//
//            // Available Ingredients: "Pita", "Wrap", "Baguette", "Beef", "Falafel", "Chicken", "Salad", "Tomatos", "Cucumber"
//            // "Cabbage", "Onions", "Cocktail sauce", "Tomato sauce", "Garlic sauce", "Spicy sauce", "Bottle of Water - Bonaqua"
//            //
//            List<Ingredient> beef_kebab_ingredients = getIngredientsList("Pita", "Beef", "Salad", "Tomatos", "Spicy sauce");
//            List<Ingredient> chicken_kebab_ingredients = getIngredientsList("Pita", "Chicken", "Salad", "Tomatos", "Cocktail sauce");
//            List<Ingredient> falafel_kebab_ingredients = getIngredientsList("Falafel", "Beef", "Salad", "Tomatos", "Spicy sauce");
//            List<Ingredient> falafel_durum_ingredients = getIngredientsList("Falafel", "Beef", "Salad", "Tomatos");
//            List<Ingredient> turkishPizza_ingredients = getIngredientsList("Falafel", "Beef", "Salad", "Tomatos", "Garlic sauce");
//            List<Ingredient> beef_durum_ingredients = getIngredientsList("Falafel", "Beef", "Salad", "Tomatos");
//            List<Ingredient> chicken_durum_ingredients = getIngredientsList("Falafel", "Beef", "Salad", "Tomatos", "Garlic sauce");
//            List<Ingredient> crog_ingredients = getIngredientsList("Falafel", "Beef", "Salad", "Tomatos");
//            List<List<Ingredient>> listOfIngredientsList = List.of(beef_kebab_ingredients, chicken_kebab_ingredients, falafel_kebab_ingredients,
//                    falafel_durum_ingredients,turkishPizza_ingredients,beef_durum_ingredients, chicken_durum_ingredients, crog_ingredients);
//            // TODO Getränke noch hinzufügen und allgemein Liste korrigieren
//
//            // Save all ingredients to the database
//            for (Ingredient ingredient: getIngredientHashMap().values()) {
//                ingredientRepository.save(ingredient);
//            }
//
//            // generate Random Orders
//            for (int i = 0; i < 9; i++) {
//                generateRandomOrders();
//            }
//
//            // Recipes are generated
////            Recipe recipeBeefKebap = new Recipe(mealRepository.findByName("Beef Kebap").getId(), ingredientRepository.findByName("Beef"), 50);
////            Recipe recipeBeefKebap2 = new Recipe(mealRepository.findByName("Beef Kebap").getId(), ingredientRepository.findByName("Salad"), 30);
////            Recipe recipeBeefKebap3 = new Recipe(mealRepository.findByName("Beef Kebap").getId(), ingredientRepository.findByName("Tomatos"), 20);
////            Recipe recipeBeefKebap4 = new Recipe(mealRepository.findByName("Beef Kebap").getId(), ingredientRepository.findByName("Pita"), 50);
////            Recipe recipeBeefKebap5 = new Recipe(mealRepository.findByName("Beef Kebap").getId(), ingredientRepository.findByName("Spicy Sauce"), 10);
////            recipeRepository.save(recipeBeefKebap);
////            recipeRepository.save(recipeBeefKebap2);
////            recipeRepository.save(recipeBeefKebap3);
////            recipeRepository.save(recipeBeefKebap4);
////            recipeRepository.save(recipeBeefKebap5);
//            int i = 1;
//            int j = 0;
//            for (List<Ingredient> ingredientList : listOfIngredientsList) {
//                for ( Ingredient ingredient : ingredientList ){
//                    Recipe recipe = new Recipe(mealRepository.findByName(getMealTypes().get(j)).getId(), ingredientRepository.findByName(ingredient.getName()), i);
//                    recipeRepository.save(recipe);
//                    j++;
//                }
//                j = 0;
//            }
//        }
//    }
//
//
//    public void generateRandomOrders() {
//        Random random = new Random();
//        int randomnumber;
//        List<Process> processList = new LinkedList<>();
//
//        randomnumber = random.nextInt(3)+1;
//
//        for (int i = 1; i < randomnumber; i++) {
//            randomnumber = random.nextInt(9)+1;
//            processList.add(new Process(mealRepository.findByName(getMealTypes().get(randomnumber)), mealRepository.findByName(getMealTypes().get(randomnumber)).getRetailPrice()));
//        }
//
//        Order order = new Order(LocalDate.now().minusDays(random.nextInt(2)), LocalTime.now().minusHours(random.nextInt(2)), processList);
//
//        for (Process process : processList){
//            processRepository.save(process);
//        }
//
//        orderRepository.save(order);
//
//        for (Process process : processList){
//            process.setOrder(order);
//            processRepository.save(process);
//        }
//    }
//
//
//    public List<Ingredient> getIngredientsList(String... ingList) {
//
//        Map<String, Ingredient> ingredientMap = getIngredientHashMap();
//        List<Ingredient> returnIngredientsList = new ArrayList<>();
//
//        for (String ingredient : ingList) {
//            returnIngredientsList.add(ingredientMap.get(ingredient));
//        }
//
//        return returnIngredientsList;
//    }
//
////    public String getRandomMealType() {
////
////        Random random = new Random();
////        return getMealTypes().get(random.nextInt(getMealsHashMap().size()-1));
////    }
//
//    public List<String> getMealTypes() {
//        List<String> mealTypeList = new ArrayList<>();
//        for(String mealName: getMealsHashMap().keySet()) {
//            mealTypeList.add(mealName);
//        }
//        return mealTypeList;
//    }
//
//    public Map<String, Meal> getMealsHashMap() {
//
//        Map<String, Meal> mealsMap = new LinkedHashMap<>();
//
//        mealsMap.put("Beef Kebab", new Meal("Beef Kebab", 4.50));
//        mealsMap.put("Chicken Kebab", new Meal("Chicken Kebab", 4.50));
//        mealsMap.put("Falafel Kebab", new Meal("Falafel Kebab", 4));
//        mealsMap.put("Falafel Durum", new Meal("Falafel Durum", 4.5));
//        mealsMap.put("Turkish Pizza", new Meal("Turkish Pizza", 3.5));
//        mealsMap.put("Beef Durum", new Meal("Beef Durum", 5));
//        mealsMap.put("Chicken Durum", new Meal("Chicken Durum", 5));
//        mealsMap.put("Croque", new Meal("Croque", 4));
//        mealsMap.put("Salad", new Meal("Salad", 4.20));
//        mealsMap.put("Coke", new Meal("Coke", 1.5));
//        mealsMap.put("Turkish Tea", new Meal("Turkish Tea", 1.2));
//        mealsMap.put("Fanta", new Meal("Fanta", 1.5));
//        mealsMap.put("Water", new Meal("Water", 1.5));
//        mealsMap.put("Beer", new Meal("Beer", 2.5));
//        mealsMap.put("Rum", new Meal("Rum", 3));
//
//        return mealsMap;
//    }
//
//    public Map<String, Ingredient> getIngredientHashMap() {
//
//        Map<String, Ingredient> ingredientMap = new LinkedHashMap<>();
//
//        ingredientMap.put("Pita", new Ingredient("Pita", 0.4, 300));
//        ingredientMap.put("Wrap", new Ingredient("Wrap", 0.6, 400));
//        ingredientMap.put("Baguette", new Ingredient("Baguette", 0.6, 100));
//        ingredientMap.put("Beef", new Ingredient("Beef", 0.6, 60000));
//        ingredientMap.put("Falafel", new Ingredient("Falafel", 0.4, 1000));
//        ingredientMap.put("Chicken", new Ingredient("Chicken", 0.5, 2000));
//        ingredientMap.put("Salad", new Ingredient("Salad", 0.1, 3000));
//        ingredientMap.put("Tomatos", new Ingredient("Tomatos", 0.3, 2500));
//        ingredientMap.put("Cucumber", new Ingredient("Cucumber", 0.2, 1500));
//        ingredientMap.put("Cabbage", new Ingredient("Cabbage", 0.2, 1000));
//        ingredientMap.put("Onions", new Ingredient("Onions", 0.1, 9000));
//        ingredientMap.put("Cocktail sauce", new Ingredient("Cocktail sauce", 0.4, 500));
//        ingredientMap.put("Tomato sauce", new Ingredient("Tomato sauce", 0.2, 200));
//        ingredientMap.put("Garlic sauce", new Ingredient("Garlic sauce", 0.4, 400));
//        ingredientMap.put("Spicy sauce", new Ingredient("Spicy sauce", 0.4, 500));
//        ingredientMap.put("Bottle of Water - Bonaqua", new Ingredient("Bottle of Water - Bonaqua", 0.03, 400));
//
//        return ingredientMap;
//    }
//}
