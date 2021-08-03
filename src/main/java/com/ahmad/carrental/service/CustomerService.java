package com.ahmad.carrental.service;

import com.ahmad.carrental.exception.ElementIsBusyException;
import com.ahmad.carrental.exception.IdNotFoundException;
import com.ahmad.carrental.model.Car;
import com.ahmad.carrental.model.Customer;
import com.ahmad.carrental.repository.CarRepository;
import com.ahmad.carrental.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CarRepository carRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void rentCar(Long customerId, Long carId) {
        if(carRepository.getById(carId).getCustomer() != null){
            throw new ElementIsBusyException("Car is already rented");
        }
        if(customerRepository.getById(customerId).getCar() != null){
            throw new ElementIsBusyException("Customer already rented a car");
        }
        Customer customer= customerRepository.findById(customerId).get();
        Car car = carRepository.findById(carId).get();
        customer.setCar(car);
        car.setCustomer(customer);
        car.setAvailable(false);
//        car.setOwner(customer.getName());
        customerRepository.save(customer);
        carRepository.save(car);
    }

    public void deleteRent(Long customerId, Long carId) {
        Customer customer = customerRepository.findById(customerId).get();
        Car car = carRepository.findById(carId).get();
//        customer.setCar(null);
        car.setCustomer(null);
        car.setAvailable(true);
//        car.setOwner(null);
        carRepository.save(car);
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        if(!customerRepository.existsById(customerId)){
            throw new IdNotFoundException("No customer found with Id = "+customerId);
        }
        if(customerRepository.getById(customerId).getCar() != null){
            throw new ElementIsBusyException("Customer is currently renting a car");
        }
        customerRepository.deleteById(customerId);
    }
}
