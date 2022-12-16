package com.expebcetracker.expencetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.expebcetracker.expencetracker.model.Expense;




public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}