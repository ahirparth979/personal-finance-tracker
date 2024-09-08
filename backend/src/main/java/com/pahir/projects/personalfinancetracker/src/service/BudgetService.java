package com.pahir.projects.personalfinancetracker.src.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pahir.projects.personalfinancetracker.src.entity.Budget;
import com.pahir.projects.personalfinancetracker.src.entity.Users;
import com.pahir.projects.personalfinancetracker.src.repository.BudgetRepository;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    public Budget createBudget(Budget budget) {
        budget.setUser(userService.getCurrentUser());
        budget.setCategory(categoryService.getCategoryByNameAndType(budget.getCategory().getName(), budget.getCategory().getType()));
        return budgetRepository.save(budget);
    }

    public List<Budget> getBudgetsByUser(Users user) {
        return budgetRepository.findByUser(user);
    }

    public void deleteBudget(Long budgetId) {
        budgetRepository.deleteById(budgetId);
    }
}