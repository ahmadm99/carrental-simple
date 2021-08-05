package com.ahmad.carrental.config;

import com.ahmad.carrental.model.Car;
import com.ahmad.carrental.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataConfig {

    @Bean
    CommandLineRunner commandLineRunner(CarRepository carRepository){
        return args -> {
            Car car1 = new Car("Mercedes","Q-15432");
            Car car2 = new Car("BMW","N-08124");
            Car car3 = new Car("Audi","R-81721");

            carRepository.saveAll(Arrays.asList(car1,car2,car3));

        };
    }
}
