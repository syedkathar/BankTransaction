package com.mebank.accountprocess;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.mebank.accountprocess.Transaction.TransactionType;

public class TransactionProcessor {
	
	
	/**
	 * It calculates account balance which will sum of funds that were transferred to/from the account in a given time frame.
	 * If it has reversing transaction, it will be omitted from the calculation.
	 * @param accountId		String
	 * @param startDate		Date
	 * @param endDate		Date
	 * @param transactions	List
	 * @return	Result
	 */
	public Result calculateBalance(String accountId, Date startDate, Date endDate, List<Transaction> transactions) {
		
		// filter transactions which has matching from and to accounts
		List<Transaction> filteredTransactions = transactions.stream()
			.filter( transaction -> 
			(transaction.fromAccountId.equals(accountId) ||
					transaction.toAccountId.equals(accountId))).collect(Collectors.toList());
		
		
		// reversal transactions
		List<String> reversalTransactions = filteredTransactions.stream().filter(
				transaction -> transaction.transactionType.equals(TransactionType.REVERSAL))
				.map(transaction -> transaction.getRelatedTransactionId()).collect(Collectors.toList());
		
		// filter ONLY required transactions
		List<Transaction> requiredTransactions = filteredTransactions.stream()
				.filter(transaction -> startDate.before(transaction.getCreatedAt()) &&
						endDate.after(transaction.getCreatedAt()) && 
								!reversalTransactions.contains(transaction.getTransactionId()) && 
								!transaction.transactionType.equals(TransactionType.REVERSAL)).collect(Collectors.toList());
		
		
		List<BigDecimal> transactionAmounts = requiredTransactions.stream().map(transaction -> transaction.amount).collect(Collectors.toList());
		
		if(requiredTransactions.isEmpty()) {
			return new Result(BigDecimal.ZERO, 0);
		}
		
		BigDecimal addedBalanceAmount = transactionAmounts.stream().reduce(BigDecimal.ZERO, BigDecimal::subtract);
		
		return new Result(addedBalanceAmount, requiredTransactions.size());
	}

}
