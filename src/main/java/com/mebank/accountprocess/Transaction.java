package com.mebank.accountprocess;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
	
	enum TransactionType { PAYMENT, REVERSAL }
	String transactionId;
	String fromAccountId ;
	String toAccountId;
	Date createdAt;
	BigDecimal amount;
	TransactionType transactionType;
	String relatedTransactionId;
	
	
	public String getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	public String getFromAccountId() {
		return fromAccountId;
	}


	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}


	public String getToAccountId() {
		return toAccountId;
	}


	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	

	public String getRelatedTransactionId() {
		return relatedTransactionId;
	}


	public void setRelatedTransactionId(String relatedTransactionId) {
		this.relatedTransactionId = relatedTransactionId;
	}


	public TransactionType getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

}
