package com.hcc.controllers;

import com.hcc.dto.AuthCredentialsRequest;
import com.hcc.dto.AuthResponse;
import com.hcc.services.UserDetailServiceImpl;
import com.hcc.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    // POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthCredentialsRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            final String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }

    // POST /api/auth/validate
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        final String jwtToken = token.substring(7); // Remove "Bearer " prefix
        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtil.getUsernameFromToken(jwtToken));

        if (jwtUtil.validateToken(jwtToken, userDetails)) {
            return ResponseEntity.ok("Token is valid");
        } else {
            return ResponseEntity.status(401).body("Invalid Token");
        }
    }
}
