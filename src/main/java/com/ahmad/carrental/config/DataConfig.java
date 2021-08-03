package com.ahmad.carrental.config;

import com.ahmad.carrental.model.Car;
import com.ahmad.carrental.model.Customer;
import com.ahmad.carrental.repository.CarRepository;
import com.ahmad.carrental.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataConfig {

    @Bean
    CommandLineRunner commandLineRunner(CarRepository carRepository, CustomerRepository customerRepository){
        return args -> {
            Car car1 = new Car("Mercedes","Q-15432",true);
            Car car2 = new Car("BMW","N-08124",true);
            Car car3 = new Car("Audi","R-81721",true);

            Customer customer1 = new Customer("Ahmad");
            Customer customer2 = new Customer("Baraa");
            Customer customer3 = new Customer("Waleed");

//            car1.setCustomer(customer1);
//            customer1.setCar(car1);


            carRepository.saveAll(Arrays.asList(car1,car2,car3));
            customerRepository.saveAll(Arrays.asList(customer1,customer2,customer3));

        };
    }
}
