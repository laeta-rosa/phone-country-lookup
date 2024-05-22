# Country Phone Code Lookup Service

This service allows looking up the country of a phone number based on its phone code.

## Usage

Enter a phone number into the text field and click "Lookup". The service will return the country or countries associated with the entered phone number.

The phone number text field can handle a variety of formats, including:

- `+7-727-267-28-83`
- `+39.06.69883135`
- `+(371)256-856-55`

... and others. The service will process these inputs, standardizing and cleansing the phone number data to look up the correct information.

## Pre-Requisites

Before running the service, ensure you have Docker or database client installed on your machine.
This project uses the Maven Wrapper, so you don't require a local Maven installation. 

## Building the Application

To build an executable JAR file for the application, you can use the Maven Wrapper.
The steps are as follows:

1. Open a terminal
2. Navigate to the directory containing the `mvnw` file (Maven Wrapper)
3. Run the command `./mvnw clean install` (on Unix) or `mvnw.cmd clean install` (on Windows).

## Running the Service

You can run the service by using Docker Compose or the Maven Wrapper included with the project.

### Docker Compose

To run the service with Docker Compose, follow these steps:

1. Open a terminal
2. Navigate to the directory containing the docker-compose.yml file
3. Run the command `docker-compose up --build`. This will build the necessary images, if they don't exist, and start the service.

With Docker Compose, the database is automatically set up and managed for you.

### Maven Wrapper

To run the service with the Maven Wrapper, you need to have a database client installed and a database configured with the following settings:
- Database name: countrycodesdb
- Username: admin
- Password: strngpswrd
- Port: 3306

Here are the steps to run the service using the Maven Wrapper:

1. Ensure the database is running. You should consult your database client's manual for instructions.
2. Open a terminal
3. Navigate to the directory containing the `mvnw` file (Maven Wrapper)
4. Run the service by executing `./mvnw spring-boot:run` (on Unix) or `mvnw.cmd spring-boot:run` (on Windows).

## Accessing the Service

Once the service starts successfully, you can access the API at `http://localhost:8088/CountryLookup`. 

## Testing and Coverage Report Generation
This project uses the Jacoco Maven Plugin for running tests and generating test coverage reports. Follow the steps below to run the tests and generate the report:
1. Open a terminal
2. Navigate to the directory containing the `mvnw` file (Maven Wrapper)
3. Run the command `./mvnw clean test` (on Unix) or `mvnw.cmd clean test` (on Windows).
4. The test coverage report will be generated at the following location: target/site/jacoco/index.html
