package com.example.kn.service;

import com.example.kn.exception.OrderLineNotFoundException;
import com.example.kn.model.Order;
import com.example.kn.model.OrderLine;
import com.example.kn.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderLineService {
    @Autowired
    private OrderLineRepository orderLineRepository;

    public OrderLine updateOrderLineQuantity(Long orderLineId, int newQuantity) throws OrderLineNotFoundException {
        Optional<OrderLine> optionalOrderLine = orderLineRepository.findById(orderLineId);

        if (optionalOrderLine.isPresent()) {
            OrderLine orderLine = optionalOrderLine.get();
            orderLine.setQuantity(newQuantity);
            return orderLineRepository.save(orderLine);
        } else {
            throw new OrderLineNotFoundException("Order Line not found with id: " + orderLineId);
        }
    }
}
