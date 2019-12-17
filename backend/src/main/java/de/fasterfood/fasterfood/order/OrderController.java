package de.fasterfood.fasterfood.order;

import de.fasterfood.fasterfood.editMeal.Meal;
import de.fasterfood.fasterfood.editMeal.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class OrderController {

    private OrderService orderService;
    private MealRepository mealRepository;

    @Autowired
    public OrderController (MealRepository mealRepository, OrderService orderService){
        this.orderService = orderService;
        this.mealRepository=mealRepository;
    }

    @GetMapping("/api/order")
    public List<Meal> listOfItems () {
        orderService.resetMap();
        return mealRepository.findAll();
    }

    @PostMapping("/api/order")
    public List<Meal> order(@RequestBody List<Meal> meals){
        orderService.addOrderandProcess(meals);
        return listOfItems();
    }
//
//    @PostMapping("/api/order/check")
//    public int orderCheck(@RequestBody List<Meal> meals){
//        return orderService.orderCheck(meals);
//    }

    @PostMapping("/api/stockcheck")
    public int stockCheck(@RequestBody Meal meal) {
//        if(meal.getId() == 0){
//            return 1;
//        }

        boolean a = orderService.generateIngredientMap(meal, "+");
        if(a){ return 1; }
        else { return 0; }
    }

    @PostMapping("/api/deleteFromCart")
    public int deleteFromCart(@RequestBody Meal meal){
        boolean a = orderService.generateIngredientMap(meal, "-");
        if(a){ return 1; }
        else { return 0; }
    }
}
