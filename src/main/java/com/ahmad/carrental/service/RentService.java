package com.ahmad.carrental.service;

import com.ahmad.carrental.exception.ElementIsBusyException;
import com.ahmad.carrental.exception.IdNotFoundException;
import com.ahmad.carrental.model.Car;
import com.ahmad.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RentService {


    @Autowired
    CarRepository carRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void rentCar(String customer, Long carId) throws InterruptedException, CannotAcquireLockException {
        TimeUnit.SECONDS.sleep(2);
        if(!carRepository.existsById(carId)){
            throw new IdNotFoundException("No car found with id = "+carId);
        }
        if (carRepository.getById(carId).getOwner() != null) {
            throw new ElementIsBusyException("Car is already rented");
        }
        Car car = carRepository.findById(carId).get();
        car.setOwner(customer);
        try {
            carRepository.saveAndFlush(car);
        } catch (CannotAcquireLockException exception) {
            throw new ElementIsBusyException("Sorry you were late. Car with id = " + carId + " is already rented");
        }
    }

    public void deleteRent(Long carId) {
        Car car = carRepository.findById(carId).get();
        car.setOwner(null);
        carRepository.save(car);
    }
}
