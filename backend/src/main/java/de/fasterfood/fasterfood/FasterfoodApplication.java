package de.fasterfood.fasterfood;

import de.fasterfood.fasterfood.main.Setup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FasterfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(FasterfoodApplication.class, args);
//		Setup.startSetup();
	}

}
