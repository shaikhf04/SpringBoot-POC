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
