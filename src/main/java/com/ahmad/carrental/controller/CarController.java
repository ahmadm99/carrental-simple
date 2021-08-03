package com.ahmad.carrental.controller;

import com.ahmad.carrental.DTO.CarDTO;
import com.ahmad.carrental.model.Car;
import com.ahmad.carrental.repository.CarRepository;
import com.ahmad.carrental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    CarRepository carRepository;

    @GetMapping
    public List<Car> getCars(){
        return carService.getCars();
    }

    @PostMapping
    public Car createCar(@RequestBody CarDTO carDTO){
        return carService.createCar(carDTO);
    }
}
