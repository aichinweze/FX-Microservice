FX-Microservice
===============

This repository comprises a Foreign Exchange Microservice which performs a currency conversion for a given amount of money and 
returns the converted amount using the appropriate exchange rate. This Microservice architecture consists of three Spring Boot 
APIs that: 

* Apply banking fees to the amount that will be processed
* Get appropriate FX rate from a database of conversion rates
* Convert an amount of money from one currency to another
   

How to use 
----------

_Preparing the Environment_

1) Clone the three folders at the root of this project.

2) Open three Command Prompt terminals to the directory containing the three folders at the root of this project.

 

_Running the Spring Boot APIs_ 

1) Start the Fx-Microservice API
      * In the first of the three terminals, type the command: "cd fx-microservice\fxmicroservice\target"

      * Run the spring boot application with the command: "java -jar fxmicroservice-0.0.1-SNAPSHOT.jar"

      * Leave this terminal to run 

2) Start the Fee-Mircroservice API
      * In the first of the three terminals, type the command: "cd fee-microservice\feemicroservice\target"

      * Run the spring boot application with the command: "java -jar feemicroservice-0.0.1-SNAPSHOT.jar"

      * Leave this terminal to run 

3) Start the CC-Microservice API
      * In the first of the three terminals, type the command: "cd cc-microservice\ccmicroservice\target"

      * Run the spring boot application with the command: "java -jar ccmicroservice-0.0.1-SNAPSHOT.jar"

      * Leave this terminal to run 

 

_Performing A Conversion_

1) With the three terminals running in the background, open Google Chrome. 

2) To perform a conversion, type the following into the search bar at the top of your browser: "http://localhost:8082/v1/api/from/{from_code}/to/{to_code}/{amount}"

        {from_code} = short-code for currency being converted from
        {to_code} = short-code for currency being converted to
        {amount} = amount that you wish to convert
    
    The following will convert 10 GBP to 12.90USD, using the default GBP-USD rate = 1.29: "http://localhost:8082/v1/api/from/GBP/to/USD/10"
    

_Updating and Adding new FX rates_ 
1) With the three terminals running in the background, open Google Chrome. 
2) You can update the exchange rate or add a new one by inputting this into the search bar: "http://localhost:8080/v1/api/id/{id}/from/{from}/to/{to}/rate/{rate}" 

        {id} = ID of this exchange rate
        {from} = short-code for currency you wish to convert from 
        {to} = short-code for currency you wish to convert to 
        {rate} = exchange rate

      The following will add or update an exchange rate for GBP-EUR: "http://localhost:8080/v1/api/id/141/from/GBP/to/EUR/rate/1.10" 
  
This program does not save any data so any changes made are only retained during the lifetime of the APIs.
