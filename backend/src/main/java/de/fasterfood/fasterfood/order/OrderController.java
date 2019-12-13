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

    @GetMapping("/api/fasterfood/order")
    public List<Meal> listOfItems () {
        return mealRepository.findAll();
    }

    @PostMapping("/api/fasterfood/order")
    public List<Meal> order(@RequestBody List<Meal> meals){
        System.out.println("hi");
        orderService.addOrderandProcess(meals);
        return listOfItems();
    }
}
