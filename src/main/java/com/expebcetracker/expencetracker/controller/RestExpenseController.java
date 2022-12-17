package com.expebcetracker.expencetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expebcetracker.expencetracker.model.Expense;
import com.expebcetracker.expencetracker.service.ExpenseService;

@RestController
public class RestExpenseController {
    @Autowired
	ExpenseService expenseService;

    @GetMapping("/getAllExpenses")
    public List<Expense> getAllExpenses() {
        return expenseService.findAll();
    }
    
}
