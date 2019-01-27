package com.saqibayub.openbank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Transaction {
	String id;
	Account this_account; 
	Account other_account;
	TransactionDetail details;
	public String getId() {
		return id;
	}
	public Transaction setId(String id) {
		this.id = id;
		return this;
	}
	public Account getThis_account() {
		return this_account;
	}
	public Transaction setThis_account(Account this_account) {
		this.this_account = this_account;
		return this;
	}
	public Account getOther_account() {
		return other_account;
	}
	public Transaction setOther_account(Account other_account) {
		this.other_account = other_account;
		return this;
	}
	public TransactionDetail getDetails() {
		return details;
	}
	public Transaction setDetails(TransactionDetail details) {
		this.details = details;
		return this;
	}

	
	
}
