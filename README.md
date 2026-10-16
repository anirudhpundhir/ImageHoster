# ImageHoster

A server-rendered Spring Boot web application where users register, log in, upload images with tags, and comment on them. Built as a course project for the IIITB PG Diploma.

## Tech stack
Java, Spring Boot 2.0 (Web MVC), Thymeleaf, Spring Data JPA / Hibernate, PostgreSQL, JUnit + Spring Boot Test (MockMvc).

## Features
- User registration, login and logout (session-based)
- Upload images with title, description and tags; images stored Base64-encoded in the database
- View an image with its tags and comments; add comments
- Edit and delete images (no ownership check is enforced yet)

## Structure
`controller` (Home, User, Image, Comment) → `service` → `repository` (JPA `EntityManager`) → `model` (`User`, `UserProfile`, `Image`, `Tag`, `Comment`). Templates live in `src/main/resources/templates`.

## How to run
1. Create a PostgreSQL database `imageHoster` on `localhost:5432`.
2. Set credentials in `ImageHoster/src/main/resources/META-INF/persistence.xml` (schema is created via `hbm2ddl.auto=update`).
3. Run:
   ```bash
   cd ImageHoster
   mvn spring-boot:run      # http://localhost:8080
   mvn test                 # controller tests
   ```

## Key concepts
Spring MVC controllers, Thymeleaf templating, JPA entity relationships (one-to-many, many-to-many), HTTP session handling, MockMvc controller tests.
