package com.example.userservice.controller;

import com.example.userservice.Service.UserService;
import com.example.userservice.VO.ResponseTemplateVO;
import com.example.userservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithOrder(@PathVariable("id") Long userId){
        return userService.getUserWithOrder(userId);
    }

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }
}
