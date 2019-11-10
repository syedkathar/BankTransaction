package com.mebank.accountprocess;

import java.math.BigDecimal;

public class Result {
	
	BigDecimal total;
	int numberOfTransactions;
	
	Result(BigDecimal total, int numberOfTransactions) {
		this.total = total;
		this.numberOfTransactions = numberOfTransactions;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public int getNumberOfTransactions() {
		return numberOfTransactions;
	}
	public void setNumberOfTransactions(int numberOfTransactions) {
		this.numberOfTransactions = numberOfTransactions;
	}
	@Override
	public String toString() {
		return "Result [total=" + total + ", numberOfTransactions=" + numberOfTransactions + "]";
	}
	
	

}
