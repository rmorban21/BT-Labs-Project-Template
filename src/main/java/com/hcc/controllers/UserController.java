package com.hcc.controllers;

import com.hcc.dto.AuthCredentialsRequest;
import com.hcc.entities.User;
import com.hcc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint to register a new user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody AuthCredentialsRequest request) {
        // Call the UserService to handle the registration logic
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        User savedUser = userService.registerNewUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // Endpoint to update user password
    @PutMapping("/update-password/{id}")
    public ResponseEntity<User> updateUserPassword(@PathVariable Long id, @RequestBody String newPassword) {
        // Call the UserService to handle the password update logic
        User updatedUser = userService.updateUserPassword(id, newPassword);
        return ResponseEntity.ok(updatedUser);
    }

    // Endpoint to find a user by username
    @GetMapping("/find/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
        // Call the UserService to find the user by username
        User user = userService.findUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
