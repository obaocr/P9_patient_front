# P9 Mediscreen
Mediscreen si a medical front application, il allows CRUD operations for a Patient (after login and authentication) : 
1. List of all Patients
1. Create a Patient
2. Update a patient
3. Delete a Patient
  

## Technical:
1. Framework: Spring Boot v2.2.5
2. Java 8
3. Thymeleaf for Java template engine
4. Bootstrap v.4.3.1
5. MySQL 8.X and H2 for tests (Users)
6. Maven 3.6
7. Hibernate is used for the domain, but not used to init database object (please run script_data.sql)

## Setup 
1. Setup a database MySQL with "p9ocr" schema for developements and tests
2. Run script script_data.sql to create tables and users

## Unit Test
1. Unit tests are written for Domain, Repository and controller
2. Integration tests are written for controller

## Security
Spring security is used

# Maven
1. mvn clean install
2. mvn clean verify  : generate tests and tests reports
3. mvn site  : generate all reportings
4. mvn spring-boot:run (run app)
5. mvn spring-boot:stop (stop app) 

## Run & tests
1. Run MedicalFrontApplication
2. Open in a browser http://localhost:8047 for test environment

### Installing
A step by step series of examples that tell you how to get a development env running:
1.Install Java: https : https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
3.Spring : https://spring.io


### Testing
1. The app has unit tests and performances tests written.
2. To run the tests from gradle launch the gradle test task or the build, il will generate the test report with jacoco


### Other consideration
JAVADOC has been initialized and needs to be completed.

### Set up docker image/container n a local environment
1. Build the JAR for all projects (mvn package in a command window)
2. Set up the host file for the Docker container
Edit C:\Windows\System32\drivers\etc\hosts and add
   127.0.0.1 patient
   127.0.0.1 note
   127.0.0.1 patientrisk
3. Create a Docker network
   docker network create p9network
4. Buid the Docker image
   docker build --tag p9-patient .
   docker build --tag p9-notes .
   docker build --tag p9-patientrisk .
   docker build --tag p9-patientfront .
5. Run the container
   docker run -p 8045:8045 --name patient --network p9network p9-patient
   docker run -p 8049:8049 --name note --network p9network p9-note
   docker run -p 8052:8052 --name patientrisk --network p9network p9-patientrisk
   docker run -p 8047:8047 --name patientfront --network p9network p9-patientfront
6. Launch the application
   Start the MongoDB  : mongod --auth --port 27017
   Start the MySQL DB (auto lauch)
   Start the application p9_patient_front
    
