### Bank Transaction

A system that analyses financial transaction records.

A transaction record describes transferring money from one account to
another account. As such, each transaction record will have the
following fields:

• transactionId – The id of the transaction

• fromAccountId – The id of the account to transfer money from

• toAccountId – The id of the account to transfer money to

• createAt – the date and time the transaction was created (in the
format of
“DD/MM/YYYY hh:mm:ss”)

• amount – The amount that was transferred including dollars and
cents

• transactionType – The type of the transaction which could be
either PAYMENT or REVERSAL.

• relatedTransaction – In case of a REVERSAL transaction, this
will contain the id of the transaction it is reversing. In case of a
PAYMENT transaction this field would be empty.
The system will be initialised with an input file in CSV format containing
a list of transaction records.


### Build and Execution

To build the application:

```bash
./mvn clean build
```

To run the application:

```bash
 ./mvn run --args 'ACC334455,resources/input.csv,20/10/2018T12:00:00,20/10/2018T19:00:00'
```

Where `ACC334455` is the account id, 
`example/input.csv` is the transaction file, 
and the remaining fields are the start date and end date respectively,and each field is separated by a comma.

### Output

Prints the balance of transactions within the provided dates which also excludes transaction if it REVERSAL.
