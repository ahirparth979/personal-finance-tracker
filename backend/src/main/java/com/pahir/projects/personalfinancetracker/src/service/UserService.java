package com.pahir.projects.personalfinancetracker.src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pahir.projects.personalfinancetracker.src.entity.Users;
import com.pahir.projects.personalfinancetracker.src.service.impl.UserDetailsServiceImpl;
import com.pahir.projects.personalfinancetracker.src.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetails loadUserByUsername(String username) {
        return userDetailsService.loadUserByUsername(username);
    }

    @Transactional
    public Users registerUser(Users user) {
        // Encode password
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//	  user.setPassword(user.getPassword());
        Users registeredUser = userRepository.save(user);

        // Set the authority for the user, e.g., "ROLE_USER"
        String authority = "ROLE_USER";
        String sql = "INSERT INTO authorities (username, authority) VALUES (?, ?)";
        jdbcTemplate.update(sql, registeredUser.getUsername(), authority);

        return registeredUser;
    }

    public Users getUserByUsername(String username) {
        Users user = userRepository.findByUsername(username);
//            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return user;
        }
//        return userRepository.findByUsername(username);
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Users user = userRepository.findByUsername(userDetails.getUsername());
//            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
//        return userRepository.findByUsername(userDetails.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
        // Implement logic to get the currently authenticated user
        // Possibly using Spring Security
    }
}