package com.example.kn.service;

import com.example.kn.model.Customer;
import com.example.kn.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    public Customer createCustomer(Customer customer) {
        Customer savedCustomer = toCustomer(customer);
        return customerRepository.save(savedCustomer);
    }

    public Customer findCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow();
    }

    private Customer toCustomer(Customer customer) {
        return Customer.builder()
                .registrationCode(customer.getRegistrationCode())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .telephone(customer.getTelephone())
                .build();
    }


}
