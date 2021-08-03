package com.ahmad.carrental.controller;

import com.ahmad.carrental.model.Customer;
import com.ahmad.carrental.repository.CustomerRepository;
import com.ahmad.carrental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(path = "customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @PutMapping(path = "customers/{customerId}/cars/{carId}")
    public ResponseEntity<Object> rentCar(@PathVariable("customerId") Long customerId, @PathVariable("carId") Long carId){
         customerService.rentCar(customerId,carId);
        HashMap<String, Object> body = new HashMap<>();
        body.put("message","Car Rented Successfully");
        body.put("status", HttpStatus.OK);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping(path = "customers/{customerId}/cars/{carId}")
    public ResponseEntity<Object> deleteRent(@PathVariable("customerId") Long customerId, @PathVariable("carId") Long carId){
        customerService.deleteRent(customerId,carId);
        HashMap<String, Object> body = new HashMap<>();
        body.put("message","Unrented Car Successfully");
        body.put("status", HttpStatus.OK);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping(path = "customers/{customerId}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("customerId") Long customerId){
        customerService.deleteCustomer(customerId);
        HashMap<String, Object> body = new HashMap<>();
        body.put("message","Customer Deleted Successfully");
        body.put("status", HttpStatus.OK);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
