package com.ahmad.carrental.config;

import com.ahmad.carrental.model.Car;
import com.ahmad.carrental.model.Role;
import com.ahmad.carrental.model.User;
import com.ahmad.carrental.repository.CarRepository;
import com.ahmad.carrental.repository.RoleRepository;
import com.ahmad.carrental.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataConfig {

    @Bean
    CommandLineRunner commandLineRunner(CarRepository carRepository, UserRepository userRepository, RoleRepository roleRepository){
        return args -> {
            Car car1 = new Car("Mercedes","Q-15432");
            Car car2 = new Car("BMW","N-08124");
            Car car3 = new Car("Audi","R-81721");

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            User user1 = new User("ahmad","ahmad@gmail.com",encoder.encode("123"),true);
            User user2 = new User("baraa", "baraa@gmail.com", encoder.encode("123"),true);

            Role role1 = new Role("ADMIN");
            Role role2 = new Role("USER");


            Set<Role> roles = new HashSet<>();
            roles.add(role1);
            Set<Role> rolesUser = new HashSet<>();
//            rolesUser.add(role1);
            rolesUser.add(role2);

            roleRepository.saveAll(Arrays.asList(role1,role2));

            user1.setRoles(roles);
            user2.setRoles(rolesUser);


            userRepository.saveAll(Arrays.asList(user1,user2));

            carRepository.saveAll(Arrays.asList(car1,car2,car3));

        };
    }
}
