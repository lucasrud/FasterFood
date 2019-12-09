package de.fasterfood.fasterfood.order;

import de.fasterfood.fasterfood.meal.Meal;
import de.fasterfood.fasterfood.meal.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
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

    @GetMapping("/fasterfood/order")
    public List<Meal> listOfItems () {
        List<Meal> meals = new LinkedList<>();

        for (Meal meal : mealRepository.findAll()) {
            meals.add(meal);
        }
        return meals;
    }

//    //TODO Pfade
    @PostMapping("/fasterfood/order")
    public List<Meal> order(@RequestBody List<Meal> meals){
        orderService.addOrder(meals);
        return listOfItems();
    }


}
