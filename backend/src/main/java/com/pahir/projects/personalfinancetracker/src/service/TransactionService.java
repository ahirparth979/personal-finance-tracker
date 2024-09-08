package com.pahir.projects.personalfinancetracker.src.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pahir.projects.personalfinancetracker.src.entity.Transaction;
import com.pahir.projects.personalfinancetracker.src.entity.Category;
import com.pahir.projects.personalfinancetracker.src.entity.Users;
import com.pahir.projects.personalfinancetracker.src.repository.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    public Transaction addTransaction(Transaction transaction) {
        // Automatically categorize the transaction
        Category category = categoryService.getCategoryByNameAndType(transaction.getDescription(), transaction.getAmount().signum() > 0 ? "INCOME" : "EXPENSE");
        transaction.setCategory(category);
        transaction.setUser(userService.getCurrentUser());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByUser(Users user) {
        return transactionRepository.findByUser(user);
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}