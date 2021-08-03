package com.ahmad.carrental.controller;

import com.ahmad.carrental.model.Customer;
import com.ahmad.carrental.repository.CustomerRepository;
import com.ahmad.carrental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
