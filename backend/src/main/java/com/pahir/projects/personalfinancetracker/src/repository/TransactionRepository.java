package com.pahir.projects.personalfinancetracker.src.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pahir.projects.personalfinancetracker.src.entity.Transaction;
import com.pahir.projects.personalfinancetracker.src.entity.Users;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByUser(Users user);}
