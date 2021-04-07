package com.todo.ensolvers.activities;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ActivityConfig {

    @Bean
    CommandLineRunner commandLineRunner(ActivityRepository repository){
        return args -> {
            Activity act = new Activity(
                    "hacer ejercicio"
            );
            Activity act2 = new Activity(
                    "no hacer ejercicio"
            );

            repository.saveAll(
                    List.of(act,act2)
            );
        };
    }
}
