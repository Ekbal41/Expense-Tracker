package com.expebcetracker.expencetracker.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.expebcetracker.expencetracker.model.Expense;
import com.expebcetracker.expencetracker.service.ExpenseService;

@Controller
public class ExpenseController {
	private final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/images/";

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

	@PostMapping("/expense/add/save")
	public String saveExpense(@ModelAttribute Expense expense, @RequestParam("file") MultipartFile file,
			RedirectAttributes attributes) {
		// check if file is empty
		if (file.isEmpty()) {
			attributes.addFlashAttribute("message", "Please select a file to upload.");
			return "redirect:/expense/add";
		}

		// normalize the file path
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		// save the file on the local file system
		try {
			Path path = Paths.get(UPLOAD_DIR + fileName);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// return success response
		attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

		expense.setImagename(fileName);
		expenseService.save(expense);
		return "redirect:/expense/add";
	}

}
