package de.fasterfood.fasterfood.main;

import de.fasterfood.fasterfood.ingredient.Ingredient;
import de.fasterfood.fasterfood.meal.Meal;
import de.fasterfood.fasterfood.order.Order;
import de.fasterfood.fasterfood.process.Process;

import javax.annotation.PostConstruct;
import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class HomeController {


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

        Date today = new Date();
        Time now = new Time(0);

        Order order = new Order(today, now, processes);


        // TODO: Save in repo
    }
}
