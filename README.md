# Hospital Management System

## Project Overview
The **Hospital Management System** is a software application developed to manage admissions, discharges, departments, and patient clinical records for "Polis Healthy Hospital" (PHH). The project is built using **Java Spring Boot** with a **MySQL** database and provides a RESTful API for managing hospital data.

## Features
- **Manage Departments**: CRUD operations for hospital departments.
- **Manage Patients**: CRUD operations for patient records, including assigning patients to departments.
- **Manage Admissions and Discharges**: Track patient admissions, discharges, and their reasons (Healthy, Transferred, Deceased).
- **Manage Clinical Records**: Maintain clinical notes for each patient.
- **Error Handling**: User-friendly error messages and server-side exception handling.
- **Unit Testing**: Automated unit tests using JUnit for key services.

---

## Prerequisites
To set up and run the project, ensure you have the following installed:

1. **Java**: Version 23.
2. **Maven**: For building the project.
3. **MySQL**: For the relational database.
4. **Postman** (optional): For testing the RESTful APIs.

---

## Technologies Used
- **Spring Boot 3.4.1**
- **MySQL 8.0.34**
- **JUnit 5** for testing
- **Maven** for dependency management
- **Lombok** for reducing boilerplate code

---

## Project Structure
```
hospital-management/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.polis.hospitalmanagement/
│   │   │       ├── config/              # Swagger configuration (optional)
│   │   │       ├── controller/          # RESTful controllers
│   │   │       ├── model/               # Entity models
│   │   │       ├── repository/          # JPA repositories
│   │   │       └── service/             # Business logic
│   │   └── resources/
│   │       ├── application.properties   # Database and server configurations
│   └── test/
│       └── java/
│           └── com.polis.hospitalmanagement/
│               └── service/             # Unit tests for services
├── pom.xml                              # Maven configuration
└── README.md                            # Project documentation
```

---

## Setup Instructions

### Step 1: Clone the Repository
Clone the project repository from GitHub:
```bash
https://github.com/EneaK9/Java-Advanced-Project
cd hospital-management
```

### Step 2: Configure the Database
Create a MySQL database named `hospital_management` and configure the credentials in `application.properties`:
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_management
spring.datasource.username=root
spring.datasource.password=yourpassword

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Step 3: Build the Project
Build the project using Maven:
```bash
mvn clean install
```

### Step 4: Run the Application
Start the Spring Boot application:
```bash
mvn spring-boot:run
```
The application will be available at `http://localhost:8080`.

---

## API Endpoints

### Department Management
| HTTP Method | Endpoint                | Description                    |
|-------------|-------------------------|--------------------------------|
| GET         | `/api/departments`      | Get all departments            |
| GET         | `/api/departments/{id}` | Get a department by ID         |
| POST        | `/api/departments`      | Create a new department        |
| PUT         | `/api/departments/{id}` | Update an existing department  |
| DELETE      | `/api/departments/{id}` | Delete a department            |

### Patient Management
| HTTP Method | Endpoint            | Description                |
|-------------|---------------------|----------------------------|
| GET         | `/api/patients`     | Get all patients           |
| GET         | `/api/patients/{id}`| Get a patient by ID        |
| POST        | `/api/patients`     | Create a new patient       |
| PUT         | `/api/patients/{id}`| Update an existing patient |
| DELETE      | `/api/patients/{id}`| Delete a patient           |

### Admission and Discharge Management
| HTTP Method | Endpoint                 | Description                        |
|-------------|--------------------------|------------------------------------|
| GET         | `/api/admissions`        | Get all admissions                 |
| GET         | `/api/admissions/{id}`   | Get an admission by ID             |
| POST        | `/api/admissions`        | Create a new admission             |
| PUT         | `/api/admissions/{id}`   | Update an existing admission       |
| DELETE      | `/api/admissions/{id}`   | Delete an admission                |

### Clinical Records Management
| HTTP Method | Endpoint                     | Description                          |
|-------------|------------------------------|--------------------------------------|
| GET         | `/api/clinical-records`      | Get all clinical records             |
| GET         | `/api/clinical-records/{id}` | Get a clinical record by ID          |
| POST        | `/api/clinical-records`      | Create a new clinical record         |
| PUT         | `/api/clinical-records/{id}` | Update an existing clinical record   |
| DELETE      | `/api/clinical-records/{id}` | Delete a clinical record             |

---

## Unit Testing
Unit tests have been written for the following services:

### Coverage
1. **DepartmentService**
2. **PatientService**
3. **AdmissionService**
4. **ClinicalRecordService**

### Example Test Output
Run tests using:
```bash
mvn test
```

Sample output:
```
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

## Screenshots
### Postman API Tests
![Postman Tests](https://via.placeholder.com/800x400 "Postman Tests Screenshot")

### Unit Test Results
![JUnit Tests](https://via.placeholder.com/800x400 "JUnit Tests Screenshot")

---

## Contributors
- **Your Name** - [your-email@example.com](mailto:your-email@example.com)

---

## License
This project is licensed under the MIT License. See the LICENSE file for details.
