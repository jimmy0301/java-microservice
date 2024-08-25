package com.example.demo.dao;
import java.util.List;

import com.example.demo.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    public User findByFirstName(String firstName);
    public User findByEmail(String email);
    public User findByEmailAndPassword(String email, String password);

    default List<User> findByOptionalFields(String lastName, String firstName, String email, String phone) {
        User user = new User(firstName, lastName, email, phone);

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<User> example = Example.of(user, matcher);

        return findAll(example);
    }
}
