package com.example.stock.bankingsystem.service;


import com.example.stock.bankingsystem.Repository.UserRepository;
import com.example.stock.bankingsystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        // Save the user to the database
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        // Check if the user exists in the database
        if (userRepository.existsById(user.getId())) {
            // Save the updated user to the database
            return userRepository.save(user);
        } else {
            // Throw an exception or return null
            return null;
        }
    }

    public void deleteUser(Long id) {
        // Delete the user from the database
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        // Get the user from the database
        return userRepository.findById(id).orElse(null);
    }

    public Iterable<User> getAllUsers() {
        // Get all the users from the database
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        // Get the user from the database
        return userRepository.findById(id);
    }
}
