package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public String addUser(@RequestBody User user) {
        userRepository.save(user);

        return "success";
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") String id  ) {
        Optional<User> user = userRepository.findById(id);

        return user.orElseThrow(() -> new RuntimeException("user not found"));
    }

    @GetMapping("/user")
    public List<User> getAllUsers(@RequestParam(required = false) String email, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        return userRepository.findByOptionalFields(lastName, firstName, email);
    }
}
