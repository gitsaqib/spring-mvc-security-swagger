package com.saqibayub.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saqibayub.domain.Transaction;
import com.saqibayub.domain.TransactionBuilder;
import com.saqibayub.openbank.OpenBankConnector;



@Service
public class TransactionImpl {

	@Autowired 
	OpenBankConnector openBankConnector;
	
	public List<Transaction> getTransactions(){
		List<Transaction> transactions=new ArrayList<Transaction>();
		List<com.saqibayub.openbank.domain.Transaction> openBankTransactions = openBankConnector.getTransactions();
		if(openBankTransactions!=null) {
		transactions = openBankTransactions.stream()
	            .map(transaction -> new TransactionBuilder().setOpenBankTransaction(transaction).build())
	            .collect(Collectors.<Transaction> toList());
		}
		return transactions;
	}
}
