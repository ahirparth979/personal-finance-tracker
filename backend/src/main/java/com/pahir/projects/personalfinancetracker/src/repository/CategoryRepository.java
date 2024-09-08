package com.pahir.projects.personalfinancetracker.src.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pahir.projects.personalfinancetracker.src.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByNameAndType(String name, String type);
	
	List<Category> findAll();}
