package com.example.orderserivce.service;

import com.example.orderserivce.entity.Order;
import com.example.orderserivce.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAllOrder(){
        return orderRepository.findAll();
    }

    public Order findOrderById(long id){
        return orderRepository.findById(id).get();
    }
    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order, long id){
        order.setId(id);
        return orderRepository.save(order);
    }

    public void deleteOrder(long id){
        orderRepository.deleteById(id);
    }
}
