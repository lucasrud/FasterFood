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

    public void addOrderandProcess(List<Meal> meals) {
        List<Process> processes = new LinkedList<>();
        List<Process> newProcesses = new LinkedList<>();
        for (Meal meal : meals) {
            if (!processes.isEmpty()) {
                for (int i = 0; i<processes.size(); i++) {
                    if (processes.get(i).getMeal().getId() == meal.getId()) {
                        processes.get(i).increaseQuantity();
                    } else {

                        Process newProcess = new Process(meal, meal.getRetailPrice());

                        if (processes.contains(newProcess)) {  // check @Override Process.equals()
                            processes.get(i).increaseQuantity();
                        } else {
                            newProcesses.add(newProcess);
                        }
                    }
                }

            } else {
                Process newProcess = new Process(meal, meal.getRetailPrice());
                newProcesses.add(newProcess);
            }

            processes = newProcesses;
        }
        for (Process process : processes) {
            processRepository.save(process);
        }
        Order order = new Order(LocalDate.now(), LocalTime.now(), processes);
        orderRepository.save(order);
    }
}
