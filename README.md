# Spring-boot POC

### Create a Employee Management System API

### **Requirements**:

1. **RESTful API**:
    - Create endpoints to perform CRUD (Create, Read, Update, Delete) operations for employees.
    - API Endpoints:
        - `POST /employees` – Create a new employee.
        - `GET /employees/{id}` – Retrieve an employee by ID.
        - `PUT /employees/{id}` – Update an employee’s information.
        - `DELETE /employees/{id}` – Delete an employee by ID.
        - `GET /employees` – Retrieve all employees.
2. **Data Model**:
    - Employee should have fields:
        - `id` (auto-generated),
        - `firstName`,
        - `lastName`,
        - `email`,
        - `department`,
        - `salary`.
3. **Database**:
    - Use an in-memory database like H2 for simplicity.
    - Use Spring Data JPA for persistence.
4. **Validation**:
    - Use validation annotations to validate input data.
5. **Error Handling**:
    - Implement proper error handling (e.g., `EmployeeNotFoundException`, validation errors).
6. **Testing**: - (In progress)
    - Write
    - test cases for the API endpoints using Spring Boot Test framework (e.g., `@WebMvcTest`, `@DataJpaTest`).


2 October 2024:
-Technology 
	jjwt 0.11.5
	H2 – embedded database

1. Implement JWT Security Implementation
2. Implement Repositories
3. Configure Spring Security
4. Implement UserDetailsService in Service class
5. Filter the request using JWTFilterRequest
6. Create JWT Utility class - token generation, validation, extraction
7. Handle Authentication Exception 
8. Define payload for Authentication Request and Response
9. Create Spring Controllers
10. Test the Endpoints

4 October 2024:

1. Application properties - profiling dev, test
2. Open API - swagger 
3. Sonar Lint for code analysis

7 October 2024:

1. Logging in SpringBoot
2. Feign Client in microservice


