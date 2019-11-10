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
