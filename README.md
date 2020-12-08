# Poseidon application
This application is a financial web application, il allows display, create, update and deletes : 
1. Bidlist
2. Rating
3. Curvepoint
4. Trade
5. Rulename
    

## Technical:

1. Framework: Spring Boot v2.2.5
2. Java 8
3. Thymeleaf for Java template engine
4. Bootstrap v.4.3.1
5. MySQL 8.X and H2 for tests
6. Maven 3.6
7. Hibernate is used for the domain, but not used to init database object (please run script_data.sql)

## Setup 
1. Setup a database MySQL with "testp7" schema for developements and tests
2. Run script script_data.sql to create tables and users

## Unit Test
1. Unit tests are written for Domain, Repository and controller
2. Integration tests are written for controller

## Security
Spring security is used

# Maven
mvn clean install
mvn clean verify  : generate tests and tests reports
mvn site  : generate all reportings
mvn spring-boot:run (run app)
mvn spring-boot:stop (stop app) 

## Run & tests
1. Run MedicalCareApplication
2. Open in a browser http://localhost:8046 for test environment