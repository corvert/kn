package com.example.kn.controller;

import com.example.kn.exception.OrderLineNotFoundException;
import com.example.kn.model.OrderLine;
import com.example.kn.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderLineController {

    @Autowired
    private OrderLineService orderLineService;

    @PutMapping("/{orderLineId}/update-quantity")
    public OrderLine updateOrderLineQuantity(
            @PathVariable Long orderLineId,
            @RequestParam int newQuantity
    ) throws OrderLineNotFoundException {
        return orderLineService.updateOrderLineQuantity(orderLineId, newQuantity);
    }
}
