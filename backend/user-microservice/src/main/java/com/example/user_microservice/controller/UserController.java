package com.example.user_microservice.controller;

import com.example.user_microservice.model.user.UserModel;
import com.example.user_microservice.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/me")
    public UserModel getMyProfile() {
        return service.getMyProfile();
    }

    @GetMapping()
    public List<UserModel> getAll(){
        return service.getAllUsers();
    }
}