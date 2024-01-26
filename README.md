Alpha

# Credit Application System

This repository contains a Credit Application System implemented using Kotlin, IntelliJ IDEA, H2 database, Spring Boot, Flyway, and Postman. The system is designed to manage credit applications, providing a robust and scalable solution for handling customer credit requests.

## Technologies Used

- **Kotlin:** The programming language used for building the application.
- **IntelliJ IDEA:** The integrated development environment used for Kotlin development.
- **H2 Database:** A lightweight, in-memory database used to store and retrieve application data during development.
- **Spring Boot:** A powerful framework for building Java-based applications, used for creating the backend of the credit application system.
- **Flyway Migration:** A database migration tool used for versioning and managing database schema changes.
- **Postman:** A popular API development and testing tool used for testing and documenting the application's APIs.

## Setup Instructions

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/credit-application-system.git
   cd credit-application-system
   ```

2. **Open in IntelliJ IDEA:**
   - Open IntelliJ IDEA.
   - Select "Open" and choose the project directory.

3. **Configure Database:**
   - Ensure that H2 database is properly configured in the application properties.

4. **Run Flyway Migrations:**
   - Run the Flyway migrations to set up the database schema.
     ```bash
     ./mvnw flyway:migrate
     ```

5. **Run the Application:**
   - Start the Spring Boot application in IntelliJ IDEA.

6. **Testing with Postman:**
   - Import the provided Postman collection to test the various endpoints.
   - Execute the requests to interact with the credit application system.

## Project Structure

The project follows a standard Spring Boot project structure with key components:

- **src/main/kotlin:** Contains the Kotlin source code.
- **src/main/resources:** Includes application properties, Flyway migration scripts, and other resources.
- **src/test:** Houses the test cases for the application.

## API Documentation

For detailed API documentation and usage examples, refer to the Postman collection included in the repository: [Credit_Application_System_Postman_Collection.json](postman/Credit_Application_System_Postman_Collection.json).

## Contributions

Contributions to the project are welcome. Feel free to submit issues, pull requests, or suggestions to enhance the Credit Application System.

## License

This project is licensed under the [iggcdev](LICENSE).

