package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

//    @PostMapping("/login")
//    public User login(@RequestBody String email, String password)
//    {
//
//    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable("id") String id  ) {
        return userService.getUserById(id);
    }

    @GetMapping("/user")
    public List<User> getAllUsers(@RequestParam(required = false) String email,
                                  @RequestParam(required = false) String firstName,
                                  @RequestParam(required = false) String lastName,
                                  @RequestParam(required = false) String phone) {
        return userService.getUserByConditions(email, lastName, firstName, phone);
    }
}
