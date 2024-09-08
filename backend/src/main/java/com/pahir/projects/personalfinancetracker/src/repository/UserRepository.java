package com.pahir.projects.personalfinancetracker.src.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pahir.projects.personalfinancetracker.src.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	Users findByUsername(String username);}
