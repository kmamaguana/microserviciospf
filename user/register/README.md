# RegisterFashionHub 📝

## Description

**RegisterFashionHub** is a microservice dedicated to managing user registration for applications. It implements a system for registering new users, validating data, and generating JWT tokens for user authentication. It is part of a microservices architecture that also includes modules for login, password recovery, and auditing.

This service is developed with Spring Boot 🚀, secured with Spring Security 🔒, and uses JWT for authentication and authorization of users 🔑.

## Project Structure 📂

```plaintext
└── 📁registerFashionHub
    └── 📁config
        └── ApplicationConfig.java  # General beans configuration.
        └── OpenApiConfig.java      # Swagger/OpenAPI configuration.
        └── PostgresqlConfig.java   # PostgreSQL and default data configuration.
        └── SecurityConfig.java     # Security configuration and JWT management.
    └── 📁controller
        └── AuthController.java     # Authentication controller (registration).
    └── 📁jwt
        └── JwtAuthenticationFilter.java  # JWT authentication filter.
        └── JwtUtils.java                 # Utilities for handling JWT.
    └── 📁model
        └── Role.java                # Model for user roles.
        └── User.java                # Model for users.
    └── 📁repository
        └── UserRepository.java      # JPA repository for the User entity.
    └── 📁request
        └── RegisterRequest.java        # Request object for registering a new user.
    └── 📁response
        └── AuthResponse.java        # Response object with JWT.
    └── 📁service
        └── AuthService.java         # User registration service.
        └── 📁impl
            └── AuthServiceImpl.java # Implementation of the user registration service.
    └── 📁resources
        └── application.properties   # Application configuration.
```

## Required Environment Variables 🌍

To ensure proper functioning of the microservice, configure the following environment variables:

```properties
# Application Configuration
spring.application.name=${APP_NAME}
server.port=${SERVER_PORT}

# Database Configuration
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=${DB_DRIVER}
spring.datasource.hikari.connection-timeout=${DB_CONNECTION_TIMEOUT}
spring.datasource.hikari.maximum-pool-size=${DB_MAX_POOL_SIZE}

# JPA Configuration
spring.jpa.hibernate.ddl-auto=${JPA_DDL_AUTO}
spring.jpa.show-sql=${JPA_SHOW_SQL}
spring.jpa.open-in-view=${JPA_OPEN_IN_VIEW}
spring.jpa.database-platform=${JPA_DATABASE_PLATFORM}

# JWT Configuration
jwt.secret=${JWT_SECRET}
jwt.expiration=${JWT_EXPIRATION}

# Domain URL
url.dominio=${DOMINIO_URL}
```

## Technologies Used ⚙️

- **Spring Boot** 🚀: Framework for enterprise application development.
- **Spring Security** 🔒: Security for authentication and authorization.
- **JWT (JSON Web Token)** 🔑: Token-based authentication.
- **Spring Data JPA** 🔄: Interaction with relational databases.
- **PostgreSQL** 🗄️: Database for storing users.
- **Lombok** 🧑‍💻: Reduces boilerplate code by generating methods.
- **Docker** 🐳: Containerization of the microservice.
- **OpenAPI** 📜: API documentation with Swagger.

## Main Features ⚡

- **User Registration** 📝: Allows users to register in the system.
- **JWT Generation** 🔑: Generates a JWT token for user authentication.

## Installation and Running 🏃‍♀️

### Prerequisites ⚙️
- **Java 17 or higher** ☕
- **Maven** 📦
- **Docker** 🐳 (optional, for containerization)

### Local Setup 🛠️

1. Clone the repository:
   ```bash
   git clone https://github.com/kmamaguana/microserviciospf.git
   ```

2. Navigate to the project directory:
   ```bash
   cd user/register
   ```

3. Configure the environment variables mentioned above in a `.env` file or directly in your local environment.

4. Build the project:
   ```bash
   mvn clean install
   ```

5. Run the application:
   ```bash
   java -jar target/registerfashionhub-0.0.1-SNAPSHOT.jar
   ```

### Using Docker 🐳

1. Build the Docker image:
   ```bash
   docker build -t registerfashionhub .
   ```

2. Run the container:
   ```bash
   docker run -p 3001:3001 --env-file .env registerfashionhub
   ```

3. The application will be available at: `http://localhost:3001`

## Main Endpoints 🛣️

| Method | Endpoint           | Description                |
|--------|--------------------|----------------------------|
| POST   | `/auth/register`    | Register a new user.       |

## API Documentation 📜

The service includes OpenAPI documentation accessible at:
```
http://localhost:3001/swagger-ui.html
```

## Architecture Notes 🏗️

This microservice is part of a modular ecosystem. Other services may include:

- **Login** (`loginFashionHub`): Manages login and JWT generation 🔑.
- **Password Recovery** (`passwordForgotFashionHub`) 🔑.
- **Password Reset** (`passwordResetFashionHub`) 🔑.

All services can be integrated through an API Gateway to simplify client interaction.

## License 📜

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
