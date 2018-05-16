# Project Vending Machine

A vending machine application that accepts coins (1, 5, 20, 50 ) and dispenses products and prices
CANDY (10), SNACK (50), NUTS (75), Coke (150), Bottle Water (100).

## Design
A vending machine object that mimics a real vending machine, so payments must be made before selecting 
a product.  The action of selecting a product will validate and calculate change.

Making a payment will create a Payment object held in a PaymentRequest on the Vending Machine.  
Since a real vending machine processes one payment at a time. Only a single payment
request is handled at a time.  Payments are created with the allowed set of coins.

## Getting Started

Check out project

### Installing

mvn clean install

## Running the tests

mvn clean test

### To run

java -jar target/vending-machine-1.0-SNAPSHOT.jar

### Command Line

java -jar target/vending-machine-1.0-SNAPSHOT.jar

Select user; 1:consumer, 2:supplier
1
1:Change user 
2:Make payment 
3:Cancel last request 
4:Exit 
2
Make payment for CANDY = 10p, SNACK = 50p, NUTS = 75p, Coke = 150p, Bottle Water = 100p
Accepted coins in pence are : 1, 5, 20, 50, 100
20

Select product(price): 1:CANDY (10), 2:SNACK (50), 3:NUTS (75), 4:Coke (150), 5:Bottle Water (100))
1

Selected product is CANDY with change 10 pence

1:Change user 
2:Make payment 
3:Cancel last request 
4:Exit 
