package com.example.stock.bankingsystem.controller;

import com.example.stock.bankingsystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.stock.bankingsystem.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping(value = "/createuser", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<User> createUser(@RequestBody User user) {
                return ResponseEntity.ok(userService.createUser(user));
        }

        @GetMapping("/getuser/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
                User user = userService.getUserById(id);
                if (user != null) {
                        return ResponseEntity.ok(user);
                } else {
                        return ResponseEntity.notFound().build();
                }
        }

        @GetMapping("/getallusers")
        public ResponseEntity<Iterable<User>> getAllUsers() {
                return ResponseEntity.ok(userService.getAllUsers());
        }

        @PutMapping("/update")
        public ResponseEntity<User> updateUser(@RequestBody User user) {
                User updatedUser = userService.updateUser(user);
                if (updatedUser != null) {
                        return ResponseEntity.ok(updatedUser);
                } else {
                        return ResponseEntity.notFound().build();
                }
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteUser(@PathVariable Long id) {
                Optional<User> userOptional = userService.findById(id);
                if (userOptional.isPresent()) {
                        User user = userOptional.get();
                        // Ensure all non-null fields are set
                        if (user.getFirstname() == null) {
                                user.setFirstname("");
                        }
                        // Handle any relationships with other entities
                        // ...
                        userService.deleteUser(user.getId());
                        return ResponseEntity.ok().build();
                } else {
                        return ResponseEntity.notFound().build();
                }
        }
}