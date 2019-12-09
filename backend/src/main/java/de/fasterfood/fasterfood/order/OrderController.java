package de.fasterfood.fasterfood.order;

import de.fasterfood.fasterfood.meal.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class OrderController {

    private OrderRepository orderRepository;
    private MealRepository mealRepository;

    @Autowired
    public OrderController (OrderRepository orderRepository, MealRepository mealRepository){
        this.orderRepository=orderRepository;
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
//    @PostMapping("/fasterfood/order")
//    public


}
