package com.pahir.projects.personalfinancetracker.src.rest;

import com.pahir.projects.personalfinancetracker.src.jwt.JwtUtils;
import com.pahir.projects.personalfinancetracker.src.jwt.LoginRequest;
import com.pahir.projects.personalfinancetracker.src.jwt.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import com.pahir.projects.personalfinancetracker.src.jwt.ActiveTokenManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.pahir.projects.personalfinancetracker.src.entity.Users;
import com.pahir.projects.personalfinancetracker.src.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private ActiveTokenManager activeTokenManager;

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users user) {
        Users registeredUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @GetMapping("/me")
    public ResponseEntity<Users> getCurrentUser() {
        Users currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(currentUser);
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
//        // Authenticate the user and generate a JWT token
//        String token = jwtTokenUtil.generateToken(userDetails);
//        return ResponseEntity.ok(token);

        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Invalidate old token
        activeTokenManager.removeToken(loginRequest.getUsername());

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        activeTokenManager.addToken(loginRequest.getUsername(), jwtToken);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        LoginResponse response = new LoginResponse(userDetails.getUsername(), roles, jwtToken);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7); // Remove "Bearer " prefix
        activeTokenManager.removeToken(jwtUtils.getUserNameFromJwtToken(token));
        return ResponseEntity.ok().build();
    }
}