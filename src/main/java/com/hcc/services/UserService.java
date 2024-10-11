package com.hcc.services;

import com.hcc.entities.User;
import com.hcc.repositories.UserRepository;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    // Register a new user
    public User registerNewUser(User user) {
        // Hash the user's password before saving it in the database
        String encodedPassword = passwordEncoder.getPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Save the user to the database
        return userRepository.save(user);
    }

    // Example additional method for updating user passwords
    public User updateUserPassword(Long userId, String newPassword) {
        // Find the user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Hash the new password before updating
        String encodedPassword = passwordEncoder.getPasswordEncoder().encode(newPassword);
        user.setPassword(encodedPassword);

        // Save the updated user
        return userRepository.save(user);
    }

    // Example method for finding a user by username
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }
}
