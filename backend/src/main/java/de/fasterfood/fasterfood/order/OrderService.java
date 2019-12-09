package de.fasterfood.fasterfood.order;

import de.fasterfood.fasterfood.meal.Meal;
import de.fasterfood.fasterfood.process.Process;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService {

    public void addOrder(List<Meal> meals) {

        List<Process> processes = new LinkedList<>();

        for (Meal meal : meals) {
            Process process = new Process(meal, meal.getPurchasePrice());
            processes.add(process);
        }
    }
}
