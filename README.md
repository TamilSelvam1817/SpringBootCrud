# Spring Boot CRUD Application

A CRUD web application built using Java, Spring Boot, Spring Data JPA, MySQL, Maven, and Thymeleaf.

## Features
- Create records
- Read and display records
- Update existing records
- Delete records
- Server-rendered views using Thymeleaf
- Database persistence through JPA and MySQL

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Thymeleaf
- Maven

## Getting Started
Prerequisites: Java compatible with the project, Maven (or Maven Wrapper), and MySQL Server.

```bash
git clone https://github.com/TamilSelvam1817/SpringBootCrud.git
cd SpringBootCrud
```

Create a local database and configure the datasource in `src/main/resources/application.properties` or `application.yml`. Example values—replace these with your own:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

Do not commit real database credentials.

Run:
```bash
mvn spring-boot:run
```

Open the URL and port printed in the terminal. Spring Boot commonly uses `http://localhost:8080` unless configured differently.

## CRUD Flow
- **Create:** submit a form to add a record
- **Read:** display saved records
- **Update:** edit a record and save changes
- **Delete:** remove a record

The exact entity names and URL paths depend on the source code.

## Testing Checklist
- Create a record and verify it appears
- Refresh and confirm it persists
- Update and verify changes
- Delete and verify removal
- Test invalid input and database connection errors

## Developer
**Tamil Selvam D** · [GitHub](https://github.com/TamilSelvam1817) · [LinkedIn](https://www.linkedin.com/in/tamil-selvam-51b5b4291/?isSelfProfile=true)
