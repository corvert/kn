package com.example.kn.service;

import com.example.kn.exception.CustomerNotFoundException;
import com.example.kn.model.Customer;
import com.example.kn.model.Order;
import com.example.kn.model.OrderLine;
import com.example.kn.model.Product;
import com.example.kn.repository.OrderLineRepository;
import com.example.kn.repository.OrderRepository;
import com.example.kn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private ProductRepository productRepository;

    public Order createOrder(Customer customer, Order order) throws CustomerNotFoundException {

        List<OrderLine> orderLines = order.getOrderLines();
        for (OrderLine orderLine : orderLines) {
            Product product = orderLine.getProduct();
            if (product.getId() == null) {
                productRepository.save(product);
            } else {
                product = productRepository.findById(product.getId()).orElseThrow();
            }

            orderLine.setProduct(product);

            orderLineRepository.save(orderLine);
        }
        Order savedOrder = toOrder(customer, order);
        return orderRepository.save(savedOrder);
    }

    public List<Order> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream()
                .sorted((date1, date2) -> date1.getOrderDate().compareTo(date2.getOrderDate()))
                .collect(Collectors.toList());
    }

    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

    private Order toOrder(Customer customer, Order order) {
        return Order.builder()
                .customer(customer)
                .orderDate(order.getOrderDate())
                .orderLines(order.getOrderLines())
                .build();
    }


    public List<Order> findOrderByCustomer(Customer customer) {
        return orderRepository.findAllByCustomerId(customer.getId());
    }

    public List<Order> findOrderByProduct(Product product) {
        //return orderLineRepository.findAllByProductId(product.getId());
        return orderRepository.findOrdersByProduct(product);
    }

    public List<Order> findOrdersByDate(LocalDate date) {
        return orderRepository.findAllByOrderDate(date);
    }


}
