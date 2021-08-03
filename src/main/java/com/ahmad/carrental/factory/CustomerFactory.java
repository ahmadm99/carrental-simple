package com.ahmad.carrental.factory;

import com.ahmad.carrental.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerFactory {
    public static Customer createCustomer(){
        return new Customer();
    }
}
