package com.example.demo.controllers;

import com.example.demo.dto.OrderRequest;
import com.example.demo.entities.Order;
import com.example.demo.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@Valid @RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }

    //Get all orders
    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    //Get order by id
    @GetMapping("/{id}")
    public Order getOrderByid(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

}
