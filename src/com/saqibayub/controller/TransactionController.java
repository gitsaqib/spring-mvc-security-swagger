package com.saqibayub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saqibayub.domain.Transaction;
import com.saqibayub.impl.TransactionImpl;

@RestController
public class TransactionController {

	@Autowired
	TransactionImpl transactionImpl;
	@GetMapping("/transaction/")
	public List<Transaction> getTransactions() {
		return transactionImpl.getTransactions();
	}
	
}
