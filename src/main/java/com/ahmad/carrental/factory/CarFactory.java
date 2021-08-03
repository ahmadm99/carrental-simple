package com.ahmad.carrental.factory;

import com.ahmad.carrental.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarFactory {
    public static Car createCar(){
        return new Car();
    }
}
