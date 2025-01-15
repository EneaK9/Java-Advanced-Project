Hospital Management System - Polis Healthy Hospital

Project Overview

The Hospital Management System is a comprehensive web application developed for Polis Healthy Hospital (PHH). This system is designed to manage hospital operations such as department management, patient records, admissions/discharges, and clinical records. The application leverages Java Spring Boot for the backend and integrates with MySQL as the relational database.

This project includes the following core functionalities:

Department Management: CRUD operations for hospital departments.

Patient Management: CRUD operations for patient records, including linking patients to departments.

Admissions and Discharges: Manage patient admissions and track discharge details.

Clinical Records: Maintain clinical notes and histories for patients.

RESTful API implementation and testing.

Unit testing using JUnit.

Features

Department Management

Create: Add new departments with unique names and descriptions.

Read: Retrieve all departments or a specific department by ID.

Update: Modify department details.

Delete: Remove a department if no patients are linked to it.

Patient Management

Create: Add patient records with personal details and link them to a department.

Read: Retrieve patient details by ID or list all patients.

Update: Modify patient information.

Delete: Remove patient records.

Admissions and Discharges

Admission Management: Record the admission date and other details.

Discharge Management: Track discharge dates, reasons (e.g., healthy, deceased, transferred), and additional notes.

Clinical Records

Create Clinical Notes: Add patient-specific clinical notes.

Retrieve Clinical Records: View patient clinical history.

Update and Delete Clinical Notes: Modify or remove records.

Technologies Used

Java 17: Primary programming language.

Spring Boot 3.4.1: Backend framework.

MySQL 8.0.40: Relational database.

JUnit: Unit testing framework.

Postman: API testing.

Maven: Dependency and build management.

Setup Instructions

Prerequisites

Java 17 or later installed.

Maven installed.

MySQL installed and running.

IDE (e.g., IntelliJ IDEA, Eclipse) for development (optional).

Step-by-Step Setup

Clone the Repository:

git clone <repository_url>
cd hospital-management

Configure the Database:

Create a MySQL database:

CREATE DATABASE hospital_management;

Update the application.properties file in src/main/resources:

spring.datasource.url=jdbc:mysql://localhost:3306/hospital_management
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

Build the Project:

mvn clean install

Run the Application:

mvn spring-boot:run

Access the Application:

The backend API runs on http://localhost:8080.

Test endpoints using Postman.

API Endpoints

Department Endpoints

Get All Departments

Method: GET

Endpoint: /api/departments

Response:

[
  {
    "id": 1,
    "name": "Cardiology",
    "description": "Heart-related treatments"
  }
]

Get Department by ID

Method: GET

Endpoint: /api/departments/{id}

Create Department

Method: POST

Endpoint: /api/departments

Request Body:

{
  "name": "Neurology",
  "description": "Brain and nerve treatments"
}

Update Department

Method: PUT

Endpoint: /api/departments/{id}

Delete Department

Method: DELETE

Endpoint: /api/departments/{id}

Patient Endpoints

Get All Patients

Method: GET

Endpoint: /api/patients

Create Patient

Method: POST

Endpoint: /api/patients

Request Body:

{
  "firstName": "John",
  "lastName": "Doe",
  "dateOfBirth": "1990-01-01",
  "address": "123 Main St",
  "phone": "1234567890",
  "departmentId": 1
}

Unit Testing

Unit tests were written using JUnit for core services.

Sample Tests

DepartmentServiceTest

Test getDepartmentById for success and failure scenarios.

Validate exception messages for not-found cases.

PatientServiceTest

Test createPatient and getPatientById functionality.

Running Tests

To run all tests:

mvn test

Screenshots

Postman API Tests

Example of GET /api/departments:


Example of POST /api/patients:


Unit Test Results

Successful mvn test run:


Future Enhancements

Implement Swagger UI for API documentation.

Add user authentication and authorization.

Deploy the application to a cloud platform like AWS or Heroku.

Authors

Your Name

License

This project is licensed under the MIT License.
