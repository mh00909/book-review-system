# BookReview Application 

This project is a comprehensive web application built using Java Spring Boot for the backend, with a basic frontend implemented using HTML, JavaScript, and CSS. The main focus of this project is on the backend services and APIs.

## Features
- **User Authentication**: Secure login and registration with JWT-based authentication.
- **Book Management**: Users can browse, search, and view details of books.
- **Review System**: Users can write reviews, mark them as helpful, and view others' reviews.
- **Author Profiles**: Detailed pages for authors.
- **Community Interaction**: Users can view other users' profiles, including their reviews.
- **Messaging**: Users can send and receive messages.

## Technologies Used
- Backend: Java Spring Boot, Spring Security, Spring Data JPA, JWT
- Frontend: HTML, CSS, JavaScript (Basic implementation)
- Database: PostgreSQL
- Build Tool: Maven

## Installation
1. Clone the repository:
```
https://github.com/mh00909/book-review-system
```
2. Configure the database: update the `application.properties` file with your credentials. If you want to have some initial data, you can remove the comments from the DataInitialization.java file.
3. Install dependencies:
```
mvn clean install
```
4. Run the application:
```
mvn spring-boot:run
```
5. You can access the application at: `http://localhost:8080`
