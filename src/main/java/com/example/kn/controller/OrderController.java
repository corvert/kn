package com.example.kn.controller;


import com.example.kn.model.Customer;
import com.example.kn.model.Order;
import com.example.kn.model.Product;
import com.example.kn.service.CustomerService;
import com.example.kn.service.OrderService;
import com.example.kn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    @GetMapping("/all-orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{customerId}/customer-order-list")
    public ResponseEntity<List<Order>> getOrderByCustomerId(@PathVariable("customerId") Long customerId){
        Customer customer = customerService.findCustomerById(customerId);
        List<Order> orderListByCustomer = orderService.findOrderByCustomer(customer);
        return ResponseEntity.ok(orderListByCustomer);
    }

    @GetMapping("/{productId}/product-order-list")
    public ResponseEntity<List<Order>> getOrderByProductId(@PathVariable("productId") Long productId){
        Product product = productService.findProductId(productId);
        List<Order> orderListByProduct = orderService.findOrderByProduct(product);
        return ResponseEntity.ok(orderListByProduct);
    }

    @GetMapping("/{orderDate}/order-list-by-date")
    public ResponseEntity<List<Order>> getOrdersByDate(@PathVariable("orderDate")LocalDate orderDate){
        List<Order> orderListByDate = orderService.findOrdersByDate(orderDate);
        return ResponseEntity.ok(orderListByDate);
    }


}
