package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

//    @Autowired
//    private MongoTemplate mongoTemplate;
    @Value("${spring.encoder.length}")
    private int encoderLength;
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder;

    public User saveUser(User user) {
        encoder = new BCryptPasswordEncoder(encoderLength);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getUserById(String id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found")));
    }

    public List<User> getUserByConditions(String email, String lastName, String firstName, String phone) {
        return userRepository.findByOptionalFields(lastName, firstName, email, phone);
    }
}
