# Notes API

A showcase Spring Boot application built as part of a coding task for a Java Backend Intern role.  
It provides a simple REST service for managing notes, demonstrating full CRUD operations.

## Prerequisites

- **Java 21**
- **Maven 3**

## Technologies Used

- **Spring Boot** – application framework
- **Spring Data JPA** – data persistence
- **H2 Database** – in-memory database
- **Swagger UI** – interactive API documentation
- **Lombok** – boilerplate code reduction
- **JUnit5** and **Mockito** – testing frameworks

## Entities

### Author

- `id` (Long, primary key)
- `name` (String, not null)

### Note

- `id` (Long, primary key)
- `title` (String, not null)
- `content` (String, optional)
- `createdAt` (LocalDateTime, auto-generated)
- `author` (Many-to-One → Author)

## API endpoints

- `POST /api/v1/authors` - create author
- `GET /api/v1/authors` - fetch all authors
- `GET /api/v1/authors/{id}` - fetch author by id

- `POST /api/v1/notes` - create note
- `GET /api/v1/notes` - fetch all notes
- `GET /api/v1/notes/{id}` - fetch note by id
- `DELETE /api/v1/notes/{id}` - remove note by id

## Features

- Clean separation of concerns between `Entity`, `Controller` and `Service` (MVC pattern)
- Centralized error handling via GlobalExceptionHandler with consistent error responses
- All services and API endpoints are properly tested using `JUnit5` and `Mockito`
- Interactive API documentation via Swagger UI

## Running the Project

```bash
cd notes-api
mvn clean test
mvn spring-boot:run
```

Once started, the application will be available at http://localhost:8080 and Swagger UI at http://localhost:8080/swagger-ui.html
