package accountprocess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mebank.accountprocess.Mapper;
import com.mebank.accountprocess.Result;
import com.mebank.accountprocess.Transaction;
import com.mebank.accountprocess.TransactionProcessor;

public class BalanceCalculatorTest {
	
	
	Mapper mapper = new Mapper();
	Date start = null ;
	Date end = null;
	Result result = null;
	TransactionProcessor transactionProcessor = new TransactionProcessor();
	
	
	@Test
	@DisplayName("when transactions have - 2 PAYMENT & 1 REVERSAL")
	public void testWhen_2payment_1reversal() throws IOException, ParseException {
		
		String accountId = "ACC334455";
		String startDate = "20/10/2018T12:00:00";
		String endDate = "20/10/2018T19:50:00";
		List<Transaction> transactions = mapper.readFile("src/test/resources/input.csv");
		
		result = transactionProcessor.calculateBalance(accountId, formattedDate(startDate), formattedDate(endDate), transactions);
		
		assertEquals(1, result.getNumberOfTransactions());
		assertEquals(new BigDecimal("-25.00"), result.getTotal());
		
	}
	@Test
	@DisplayName("when transactions has - 1 PAYMENT")
	public void testWhen_single_payment() throws IOException, ParseException {
		String accountId = "ACC334455";
		String startDate = "20/10/2018T12:00:00";
		String endDate = "20/10/2018T13:50:00";
		List<Transaction> transactions = mapper.readFile("src/test/resources/input.csv");
		
		result = transactionProcessor.calculateBalance(accountId, formattedDate(startDate), formattedDate(endDate), transactions);
		
		assertEquals(1, result.getNumberOfTransactions());
		assertEquals(new BigDecimal("-25.00"), result.getTotal());
		
	}
	
	@Test
	@DisplayName("when transactions have - 1 PAYMENT & 1 REVERSAL")
	public void testWhen_1payment_1reversal() throws IOException, ParseException {
		String accountId = "ACC334455";
		String startDate = "20/10/2018T17:30:00";
		String endDate = "20/10/2018T20:50:00";
		List<Transaction> transactions = mapper.readFile("src/test/resources/input.csv");
		result = transactionProcessor.calculateBalance(accountId, formattedDate(startDate), formattedDate(endDate), transactions);
		
		assertEquals(0, result.getNumberOfTransactions());
		assertEquals(new BigDecimal("0"), result.getTotal());
	}
	
	@Test
	@DisplayName("when transactions have - 3 PAYMETS & 1 REVERSAL")
	public void testWhen_3payment_1reversal() throws IOException, ParseException {
		String accountId = "ACC334455";
		String startDate = "20/10/2018T12:00:00";
		String endDate = "21/10/2018T19:50:00";
		List<Transaction> transactions = mapper.readFile("src/test/resources/input.csv");
		result = transactionProcessor.calculateBalance(accountId, formattedDate(startDate), formattedDate(endDate), transactions);
		assertEquals(2, result.getNumberOfTransactions());
		assertEquals(new BigDecimal("-32.25"), result.getTotal());
	}
	
	@Test
	@DisplayName("when transaction have either no payment or reversal")
	public void testWhen_no_payment_no_reversal() throws IOException, ParseException {
		String accountId = "ACC998877";
		String startDate = "20/10/2018T12:00:00";
		String endDate = "20/10/2018T15:50:00";
		List<Transaction> transactions = mapper.readFile("src/test/resources/input.csv");
		result = transactionProcessor.calculateBalance(accountId, formattedDate(startDate), formattedDate(endDate), transactions);
		assertEquals(0, result.getNumberOfTransactions());
		assertEquals(new BigDecimal("0"), result.getTotal());
		
	}
	
	private  Date formattedDate(String dateTimestamp) throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss").parse(dateTimestamp);
		return date;
	}

}
