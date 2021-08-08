package com.ahmad.carrental.controller;

import com.ahmad.carrental.DTO.CarDTO;
import com.ahmad.carrental.DTO.ResponseDTO;
import com.ahmad.carrental.model.Car;
import com.ahmad.carrental.service.CarService;
import com.ahmad.carrental.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    RentService rentService;


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
    public ResponseEntity<ResponseDTO> deleteCar(@PathVariable("carId") Long carId){
        carService.deleteCar(carId);
        return new ResponseEntity<>(new ResponseDTO("Car deleted successfully"), HttpStatus.OK);
    }

    @PutMapping(path = "customers/{customer}/cars/{carId}")
//    @Retryable(value = CannotAcquireLockException.class)
    public ResponseEntity<ResponseDTO> rentCar(@PathVariable("customer") String customer, @PathVariable("carId") Long carId) throws InterruptedException {
        rentService.rentCar(customer,carId);
        return new ResponseEntity<>(new ResponseDTO("Car rented successfully"), HttpStatus.OK);
    }

    @DeleteMapping(path = "unrent/cars/{carId}")
    public ResponseEntity<ResponseDTO> deleteRent(@PathVariable("carId") Long carId){
        rentService.deleteRent(carId);
        return new ResponseEntity<>(new ResponseDTO("Unrented car successfully"), HttpStatus.OK);
    }
}
