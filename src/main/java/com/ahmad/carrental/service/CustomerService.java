package com.ahmad.carrental.service;

import com.ahmad.carrental.DTO.CustomerDTO;
import com.ahmad.carrental.exception.ElementIsBusyException;
import com.ahmad.carrental.exception.IdNotFoundException;
import com.ahmad.carrental.factory.CustomerFactory;
import com.ahmad.carrental.model.Car;
import com.ahmad.carrental.model.Customer;
import com.ahmad.carrental.repository.CarRepository;
import com.ahmad.carrental.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CarRepository carRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public void rentCar(Long customerId, Long carId) throws InterruptedException, DataIntegrityViolationException {
        if(carRepository.getById(carId).getCustomer() != null){
            throw new ElementIsBusyException("Car is already rented");
        }
        if(customerRepository.getById(customerId).getCar() != null){
            throw new ElementIsBusyException("Customer already rented a car");
        }
        TimeUnit.SECONDS.sleep(2);
        Customer customer= customerRepository.findById(customerId).get();
        Car car = carRepository.findById(carId).get();
        customer.setCar(car);
        car.setCustomer(customer);
        car.setAvailable(false);
//        car.setOwner(customer.getName());
        try {
            customerRepository.saveAndFlush(customer);
            carRepository.saveAndFlush(car);
        }
        catch(DataIntegrityViolationException exception){
            throw new ElementIsBusyException("Sorry you were late. Car with id = "+carId+" is already rented");
        }
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

    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerFactory.createCustomer();
        customer.setName(customerDTO.getName());
        return customerRepository.save(customer);
    }
}
