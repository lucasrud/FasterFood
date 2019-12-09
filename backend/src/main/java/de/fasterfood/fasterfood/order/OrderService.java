package de.fasterfood.fasterfood.order;

import de.fasterfood.fasterfood.meal.Meal;
import de.fasterfood.fasterfood.process.Process;
import de.fasterfood.fasterfood.process.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;


@Service
public class OrderService {

    private OrderRepository orderRepository;
    private ProcessRepository processRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProcessRepository processRepository){
        this.orderRepository = orderRepository;
        this.processRepository = processRepository;
    }

    public void addOrder(List<Meal> meals) {
        List<Process> processes = new LinkedList<>();
        for (Meal meal : meals) {
            if (!processes.isEmpty()) {
                for (Process process : processes) {
                    if (process.getMeal().getId() == meal.getId()) {
                        process.increaseQuantity();
                    } else {
                        Process newProcess = new Process(meal, meal.getPurchasePrice());
                        processes.add(newProcess);
                    }
                }
            } else {
                Process newProcess = new Process(meal, meal.getPurchasePrice());
                processes.add(newProcess);
            }
            for (Process process : processes) {
                processRepository.save(process);
            }
            Order order = new Order(LocalDate.now(), LocalTime.now(), processes);
            orderRepository.save(order);
        }
    }


}
