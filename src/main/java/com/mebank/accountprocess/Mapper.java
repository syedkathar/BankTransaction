package com.mebank.accountprocess;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.mebank.accountprocess.Transaction.TransactionType;

public class Mapper {
	
	
	/**
	 * Method read CSV file contains list of transactions separated by comma, and populate <Transaction> object.
	 * @param 	filePath		String
	 * @return	List			List of <Transaction> object
	 * @throws IOException		throws when failed to read CSV file	
	 * @throws ParseException	throws when failed to parse CSV file
	 */
	public List<Transaction> readFile(String filePath) throws IOException, ParseException {
		Reader reader = Files.newBufferedReader(Paths.get(filePath));
		CSVParser csvParser = new CSVParser(reader, CSVFormat.EXCEL.withFirstRecordAsHeader());
		Transaction transaction = null;
		List<Transaction> transactions = new ArrayList<Transaction>();
		for (CSVRecord csvRecord : csvParser) {
			transaction = new Transaction();
			transaction.setTransactionId(csvRecord.get("transactionId").trim());
	        transaction.setFromAccountId(csvRecord.get("fromAccountId").trim());
	        transaction.setToAccountId(csvRecord.get("toAccountId").trim());
	        transaction.setCreatedAt(formattedDate(csvRecord.get("createdAt").trim()));
	        BigDecimal amountBD = new BigDecimal(csvRecord.get("amount").trim());
	        transaction.setAmount(amountBD);
	        transaction.setTransactionType((csvRecord.get("transactionType").trim().equalsIgnoreCase("PAYMENT")?TransactionType.PAYMENT:TransactionType.REVERSAL));
	        if(csvRecord.isSet("relatedTransaction")) {
	        	transaction.setRelatedTransactionId(csvRecord.get("relatedTransaction").trim());
	        }
	        transactions.add(transaction);
		}
		csvParser.close();
		return transactions;
	}
	
	private Date formattedDate(String dateTimestamp) throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dateTimestamp);
		return date;
		
	}
}
