# Recruitment Service

## Overview

The Recruitment Service is a Spring Boot application designed to manage various aspects of a recruitment system. It provides endpoints for handling job seekers, employers, job listings, resumes, and administrative tasks.

## Features

- **Authentication**: User login functionality.
- **Job Seeker Management**: Create, update, retrieve, and delete job seekers.
- **Employer Management**: Create, update, retrieve, and delete employers.
- **Job Listing Management**: Create, update, retrieve, and delete job listings.
- **Resume Management**: Create, update, retrieve, and delete resumes.
- **Administrative Operations**: Filter statistics by day and find matching seekers for jobs.

## How to Run the Project

### Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher

### Steps

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/recruitment-service.git
    cd recruitment-service
    ```

2. **Build the project**:
    ```bash
    mvn clean install
    ```

3. **Run the application**:
    ```bash
    mvn spring-boot:run
    ```

4. **Access Swagger UI**:
    Open your browser and navigate to `http://localhost:8080/swagger-ui.html` to see the API documentation and test the endpoints.

## API Endpoints

### Authentication

- **POST /auth/login**: User login.

### Job Seekers

- **POST /seekers**: Create a new job seeker.
- **PUT /seekers/{id}**: Update an existing job seeker.
- **GET /seekers/{id}**: Get a job seeker by ID.
- **GET /seekers**: List all job seekers.
- **DELETE /seekers/{id}**: Delete a job seeker by ID.

### Employers

- **POST /employers**: Create a new employer.
- **PUT /employers/{id}**: Update an existing employer.
- **GET /employers/{id}**: Get an employer by ID.
- **GET /employers**: List all employers.
- **DELETE /employers/{id}**: Delete an employer by ID.

### Jobs

- **POST /jobs**: Create a new job listing.
- **PUT /jobs/{id}**: Update an existing job listing.
- **GET /jobs/{id}**: Get a job listing by ID.
- **GET /jobs**: List all job listings.
- **DELETE /jobs/{id}**: Delete a job listing by ID.

### Resumes

- **POST /resumes**: Create a new resume.
- **PUT /resumes/{id}**: Update an existing resume.
- **GET /resumes/{id}**: Get a resume by ID.
- **GET /resumes**: List all resumes.
- **DELETE /resumes/{id}**: Delete a resume by ID.

### Administrative Operations

- **GET /admin/filterByDay**: Get statistics filtered by day.
- **GET /admin/findMatchingSeeker/{id}**: Find matching seeker for a job by ID.

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.
