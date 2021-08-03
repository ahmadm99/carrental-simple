package com.ahmad.carrental.controller;

import com.ahmad.carrental.DTO.CarDTO;
import com.ahmad.carrental.model.Car;
import com.ahmad.carrental.repository.CarRepository;
import com.ahmad.carrental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    CarRepository carRepository;

    @GetMapping
    public List<Car> getAvailableCars(){
        return carService.getAvailableCars();
    }

    @GetMapping(path = "cars")
    public List<Car> getAllCars(){
        return carService.getAllCars();
    }

    @PostMapping
    public Car createCar(@RequestBody CarDTO carDTO){
        return carService.createCar(carDTO);
    }

    @DeleteMapping(path = "cars/{carId}")
    public ResponseEntity<Object> deleteCar(@PathVariable("carId") Long carId){
        carService.deleteCar(carId);
        HashMap<String, Object> body = new HashMap<>();
        body.put("message","Car Deleted Successfully");
        body.put("status", HttpStatus.OK);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
