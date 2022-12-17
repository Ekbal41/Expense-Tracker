package com.expebcetracker.expencetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.expebcetracker.expencetracker.model.Expense;
import com.expebcetracker.expencetracker.service.ExpenseService;

@Controller
public class ExpenseController {

	@Autowired
	ExpenseService expenseService;

	@GetMapping("/")
	public String getExpenses(Model model) {
		model.addAttribute("expenses", expenseService.findAll());
		return "index";
	}

	@GetMapping("/expense/add")
	public String addExpense(Model model) {
		model.addAttribute("expense", new Expense());
		return "add";
	}

	@PostMapping("/expense/add/save")
	public String saveExpense(@ModelAttribute Expense expense) {
		expenseService.save(expense);
		return "redirect:/";
	}

	@GetMapping("/showUpdateForm/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Expense expense = expenseService.findById(id);
		model.addAttribute("expense", expense);
		return "add";
	}

	@GetMapping("/deleteExpense/{id}")
	public String deleteExpense(@PathVariable("id") long id) {

		expenseService.delete(id);
		return "redirect:/";
	}

}
