package com.example.kn.controller;

import com.example.kn.exception.CustomerNotFoundException;
import com.example.kn.model.Customer;
import com.example.kn.model.Order;
import com.example.kn.service.CustomerService;
import com.example.kn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/create-customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        customerService.createCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/{id}/create-order")
    public ResponseEntity<Order> createOrder(@PathVariable("id") Long customerId, @RequestBody Order order)
    throws CustomerNotFoundException {
        Customer customer = customerService.findCustomerById(customerId);
        Order savedOrder = orderService.createOrder(customer, order);
        return ResponseEntity.ok(savedOrder);
    }
}
