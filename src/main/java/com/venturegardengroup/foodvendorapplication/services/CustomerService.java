package com.venturegardengroup.foodvendorapplication.services;

import com.venturegardengroup.foodvendorapplication.models.Customer;
import com.venturegardengroup.foodvendorapplication.models.Notification;
import com.venturegardengroup.foodvendorapplication.models.Vendor;
import com.venturegardengroup.foodvendorapplication.repositories.CustomerRepository;
import com.venturegardengroup.foodvendorapplication.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> list(){
        return customerRepository.findAll();
    }

    public Customer getOne(Long id){
        return this.customerRepository.getOne(id);
    }

    public Customer create(String firstName, String lastName,
                           String phoneNumber, BigDecimal accountBalance){
        Customer newCustomer = new Customer(
                firstName, lastName, phoneNumber,
                accountBalance,
                new ArrayList<>(),
                new ArrayList<>(),
                LocalDateTime.now());
        return customerRepository.saveAndFlush(newCustomer);
    }

}
