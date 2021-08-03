package com.ahmad.carrental.service;

import com.ahmad.carrental.DTO.CarDTO;
import com.ahmad.carrental.factory.CarFactory;
import com.ahmad.carrental.model.Car;
import com.ahmad.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public Car createCar(CarDTO carDTO) {
        Car car = CarFactory.createCar();
        car.setModel(carDTO.getModel());
        car.setLicense(carDTO.getLicense());
        car.setAvailable(carDTO.getAvailable());
        return carRepository.save(car);
    }
}
