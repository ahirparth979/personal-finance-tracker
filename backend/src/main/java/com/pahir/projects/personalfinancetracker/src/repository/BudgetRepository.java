package com.pahir.projects.personalfinancetracker.src.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pahir.projects.personalfinancetracker.src.entity.Budget;
import com.pahir.projects.personalfinancetracker.src.entity.Users;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

	List<Budget> findByUser(Users user);}
