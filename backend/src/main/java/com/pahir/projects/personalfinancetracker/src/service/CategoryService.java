package com.pahir.projects.personalfinancetracker.src.service;

import java.util.List;
import com.pahir.projects.personalfinancetracker.src.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pahir.projects.personalfinancetracker.src.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryByNameAndType(String name, String type) {
        return categoryRepository.findByNameAndType(name, type);
//                .orElseGet(() -> {
//                    Category newCategory = new Category();
//                    newCategory.setName(name);
//                    newCategory.setType(type);
//                    return categoryRepository.save(newCategory);
//                });
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}