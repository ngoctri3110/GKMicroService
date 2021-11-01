package com.example.orderserivce.controller;

import com.example.orderserivce.entity.Order;
import com.example.orderserivce.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> findAllOrder(){
        return orderService.findAllOrder();
    }
    @PostMapping
    public Order saveOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }

    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable("id") long id){
        return orderService.findOrderById(id);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable("id") long id, @RequestBody Order order){
        return orderService.updateOrder(order, id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        orderService.deleteOrder(id);
    }
}
