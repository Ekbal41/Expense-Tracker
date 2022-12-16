package com.expebcetracker.expencetracker.service;

import java.util.List;

import com.expebcetracker.expencetracker.model.Expense;



public interface ExpenseService {
	
	List<Expense> findAll();
	
	Expense save(Expense expense);
	
	Expense findById(Long id);
	
	void delete(Long id);
}