# bankkata

Test application from outside, according to side effect

Problem description:  Bank kata

Create a simple bank application with the following features:

- Deposit into Account
- Withdraw from an Account
- Print a bank statement to the console.

Acceptance criteria
Statement should have the following the format:

    DATE       | AMOUNT  | BALANCE
    10/04/2014 | 500.00  | 1400.00
    02/04/2014 | -100.00 | 900.00
    01/04/2014 | 1000.00 | 1000.00


The operations must follow this signature:

    deposit [amount]
    withdraw [amount]
    print-statement []

Note: The solution is not idomatic Clojure because of the constraint to strictly follow the above signature. Ideally I would:

- pass in the concept of an account to the function so that the module does not need to maintain state
- pass the date into deposit and withdrawls so we could keep the local-date function private 
- pass a printer into the print-statement function so we wouldn't need the print-to-console function 