package com.pahir.projects.personalfinancetracker.src.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pahir.projects.personalfinancetracker.src.entity.Budget;
import com.pahir.projects.personalfinancetracker.src.entity.Users;
import com.pahir.projects.personalfinancetracker.src.service.BudgetService;
import com.pahir.projects.personalfinancetracker.src.service.UserService;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        Budget savedBudget = budgetService.createBudget(budget);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBudget);
    }

    @GetMapping
    public ResponseEntity<List<Budget>> getBudgets() {
    	UserService userService = new UserService();
        Users currentUser = userService.getCurrentUser();
        List<Budget> budgets = budgetService.getBudgetsByUser(currentUser);
        return ResponseEntity.ok(budgets);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }
}