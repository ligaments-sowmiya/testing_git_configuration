# ecommerce-api

Auto-generated REST API using Java Spring Boot

## Generated on
2025-12-01 12:57:28

## Models
User

## Architecture
- **Entity Layer**: JPA entities with Lombok annotations
- **DTO Layer**: Data Transfer Objects for API communication
- **Repository Layer**: Spring Data JPA repositories
- **Service Layer**: Business logic and entity-DTO conversion
- **Controller Layer**: REST API endpoints

## Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

## Installation

1. Build the project:
```bash
mvn clean install
```

2. Run the application:
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

### General
- `GET /` - API information

### Resources
- `GET /api/users` - List all Users
- `POST /api/users` - Create a User
- `GET /api/users/{id}` - Get a User by ID
- `PUT /api/users/{id}` - Update a User
- `DELETE /api/users/{id}` - Delete a User

## Database
Database: mysql

## Project Structure
```
src/main/java/com/example/ecommerce/
├── Application.java           # Main Spring Boot application
├── controller/                # REST controllers
│   └── *Controller.java
├── dto/                      # Data Transfer Objects
│   └── *DTO.java
├── entity/                   # JPA entities
│   └── *.java
├── repository/               # Spring Data repositories
│   └── *Repository.java
└── service/                  # Business logic
    └── *Service.java
```

## Testing
Use tools like Postman, cURL, or your favorite API client to test the endpoints.

Example cURL request:
```bash
curl -X GET http://localhost:8080/api/users
```
