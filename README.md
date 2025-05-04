# AiDash Backend

AiDash is an AI Chatbot Creation and Deployment Platform. This repository contains the backend services built with Spring Boot.

## Project Structure

The project is organized into multiple modules:

- **application**: Main application module containing the Spring Boot application
- **common**: Shared utilities and common code
- **repository**: Database access layer with JPA and Liquibase
- **service**: Business logic layer with GraphQL API

## Technology Stack

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- Spring Security
- Spring GraphQL
- PostgreSQL
- Liquibase
- Maven

## Prerequisites

- Java 17
- Maven 3.8+
- PostgreSQL 15+

## Database Setup

1. Create a PostgreSQL database:

```sql
CREATE DATABASE aidash;
CREATE USER aidash WITH PASSWORD 'aidash';
GRANT ALL PRIVILEGES ON DATABASE aidash TO aidash;
```

2. The database schema will be automatically created by Liquibase on application startup.

## Building the Project

```bash
mvn clean install
```

## Running the Application

```bash
mvn spring-boot:run -pl application
```

The application will start on port 8080.

## GraphQL API

The GraphQL API is available at `/graphql`. You can use the GraphiQL interface at `/graphiql` for testing.

### Example Queries

#### Create User

```graphql
mutation {
  createUser(
    input: {
      email: "user@example.com"
      password: "password123"
      firstName: "John"
      lastName: "Doe"
      companyName: "Example Corp"
      phoneNumber: "+1234567890"
    }
  ) {
    id
    email
    firstName
    lastName
  }
}
```

#### Query User

```graphql
query {
  user(id: "user-id") {
    id
    email
    firstName
    lastName
    companyName
    phoneNumber
  }
}
```

## Development

### Project Structure

```
aidash-backend/
├── application/           # Main application module
├── common/               # Shared utilities
├── repository/           # Database access layer
│   └── src/main/resources/
│       └── db/changelog/ # Liquibase changelogs
└── service/             # Business logic layer
    └── src/main/resources/
        └── graphql/     # GraphQL schema
```

## Security

The application uses Spring Security for authentication and authorization. User passwords are encrypted using BCrypt.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request
