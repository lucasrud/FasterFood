package de.fasterfood.fasterfood.finance;

import de.fasterfood.fasterfood.process.Process;
import de.fasterfood.fasterfood.process.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;


@RestController
public class FinanceController {

    ProcessRepository processRepository;

    @Autowired
    public FinanceController(ProcessRepository processRepository){
        this.processRepository = processRepository;
    }

    @GetMapping("/api/finance")
    public List<Process> turnover(){
        return processRepository.findAll();
    }

    @GetMapping("/api/finance/today")
    public List<Process> dailyTurnover(){
        return processRepository.findAllByOrderDate(LocalDate.now());
    }
}
