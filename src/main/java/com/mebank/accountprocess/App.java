package com.mebank.accountprocess;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
    	try {
    		System.out.println("Input Values :-\n accountID: "+args[0]+"\n filtPath "+args[1] + "\n startDate: "+args[2] + "\n endDate: "+args[3]);
    		Result result = calculate(args[0], args[1], args[2], args[3]);
        	System.out.println("Output Values: \nAmount:  "+result.getTotal() +"\n Number of Transactions : "+result.getNumberOfTransactions());
    	}
    	catch(ParseException e) {
    		
    	} catch (IOException e) {
			
		}
    }
    
    private static Result calculate(String accountId, String filePath, String startDate, String endDate) throws ParseException, IOException {
    	TransactionProcessor transactionProcessor = null;
    	Date start = formattedDate(startDate);
    	Date end = formattedDate(endDate);
    	List<Transaction> transactions = new Mapper().readFile(filePath);
    	if(transactions.isEmpty()) {
    		return new Result(BigDecimal.ZERO, 0);
    	}
    	transactionProcessor = new TransactionProcessor();
    	Result result = transactionProcessor.calculateBalance(accountId, start, end, transactions);
    	return result;
    	
    }
    
    private static Date formattedDate(String dateTimestamp) throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss").parse(dateTimestamp);
		return date;
	}
}
