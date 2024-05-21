# Country Phone Code Lookup Service

This service allows looking up the country of a phone number based on its phone code.

## Pre-Requisites

Before running the service, ensure you have Docker or database client installed on your machine.

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

1. Ensure your database is running. You should consult your database client's manual for instructions.
2. Open a terminal
3. Navigate to the directory containing the `mvnw` file (Maven Wrapper)
4. Run the command `./mvnw clean install` (on Unix) or `mvnw.cmd clean install` (on Windows). This will build the application and create a target directory with the necessary JAR file.
5. Once the build is complete, you can run the service by executing `./mvnw spring-boot:run` (on Unix) or `mvnw.cmd spring-boot:run` (on Windows).

## Accessing the Service

Once the service starts successfully, you can access the API at `http://localhost:8088/CountryLookup`. 
