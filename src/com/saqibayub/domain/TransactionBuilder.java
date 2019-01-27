package com.saqibayub.domain;

import com.saqibayub.openbank.domain.Transaction;

public class TransactionBuilder {

	Transaction openBankTransaction;

	public TransactionBuilder setOpenBankTransaction(Transaction openBankTransaction) {
		this.openBankTransaction = openBankTransaction;
		return this;
	}
	
	public com.saqibayub.domain.Transaction build() {
		com.saqibayub.domain.Transaction transaction = new com.saqibayub.domain.Transaction();

		transaction.setId(openBankTransaction.getId());
		transaction.setAccountId(openBankTransaction.getThis_account().getId());
		transaction.setCounterpartyAccount(openBankTransaction.getOther_account().getNumber());
		transaction.setTransactionType(openBankTransaction.getDetails().getType());
		
		return transaction; 
	}
}
