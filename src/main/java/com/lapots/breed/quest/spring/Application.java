package com.lapots.breed.quest.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner loadData(PlayerRepository repository) {
        return (args) -> {
          repository.save(new Player("MasterGamer", 10, "Warrior"));
          repository.save(new Player("Mechanizer", 5, "Warrior"));
          repository.save(new Player("Elba", 10, "Magician"));
          repository.save(new Player("Magik", 6, "Magician"));
        };
    }
}
