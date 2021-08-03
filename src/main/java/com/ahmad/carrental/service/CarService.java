package com.ahmad.carrental.service;

import com.ahmad.carrental.DTO.CarDTO;
import com.ahmad.carrental.exception.ElementIsBusyException;
import com.ahmad.carrental.exception.IdNotFoundException;
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

    public List<Car> getAvailableCars() {
        return carRepository.findAvailableCars();
    }

    public Car createCar(CarDTO carDTO) {
        Car car = CarFactory.createCar();
        car.setModel(carDTO.getModel());
        car.setLicense(carDTO.getLicense());
        car.setAvailable(carDTO.getAvailable());
        return carRepository.save(car);
    }

    public void deleteCar(Long carId) {
        if(!carRepository.existsById(carId)){
            throw new IdNotFoundException("No car found with Id = "+carId);
        }
        if(carRepository.getById(carId).getCustomer() != null){
            throw new ElementIsBusyException("Car is currently rented");
        }
        carRepository.deleteById(carId);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
}
