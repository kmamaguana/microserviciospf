# Prueba no me acuerdo
# LoginFashionHub

## Descripción

**LoginFashionHub** es un microservicio dedicado exclusivamente a la gestión de autenticación para aplicaciones. Implementa un sistema de inicio de sesión y generación de tokens JWT para el manejo seguro de sesiones. Es parte de una arquitectura basada en microservicios que divide funcionalidades como registro, recuperación de contraseñas y auditorías en módulos separados.

Este servicio está desarrollado con Spring Boot, asegurado con Spring Security, y utiliza JWT para la autenticación y autorización de usuarios.

## Estructura del Proyecto

```plaintext
└── 📁loginFashionHub
    └── 📁config
        └── ApplicationConfig.java  # Configuración general de beans.
        └── OpenApiConfig.java      # Configuración de Swagger/OpenAPI.
        └── SecurityConfig.java     # Configuración de seguridad y manejo de JWT.
    └── 📁controller
        └── AuthController.java     # Controlador de autenticación (login).
    └── 📁jwt
        └── JwtAuthenticationFilter.java  # Filtro de autenticación JWT.
        └── JwtUtils.java                 # Utilidades para manejar JWT.
    └── 📁model
        └── Role.java                # Modelo para roles de usuario.
        └── User.java                # Modelo para usuarios.
    └── 📁repository
        └── UserRepository.java      # Repositorio JPA para la entidad User.
    └── 📁request
        └── LoginRequest.java        # Objeto de solicitud para iniciar sesión.
    └── 📁response
        └── AuthResponse.java        # Objeto de respuesta con JWT.
    └── 📁service
        └── AuthService.java         # Servicio de autenticación.
        └── 📁impl
            └── AuthServiceImpl.java # Implementación del servicio de autenticación.
    └── 📁resources
        └── application.properties   # Configuración de la aplicación.
```

## Variables de entorno requeridas

Para el correcto funcionamiento del microservicio, debes configurar las siguientes variables de entorno:

```properties
# Configuración de la aplicación
spring.application.name=${APP_NAME}
server.port=${SERVER_PORT}

# Configuración de la base de datos
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=${DB_DRIVER}
spring.datasource.hikari.connection-timeout=${DB_CONNECTION_TIMEOUT}
spring.datasource.hikari.maximum-pool-size=${DB_MAX_POOL_SIZE}

# Configuración de JPA
spring.jpa.hibernate.ddl-auto=${JPA_DDL_AUTO}
spring.jpa.show-sql=${JPA_SHOW_SQL}
spring.jpa.open-in-view=${JPA_OPEN_IN_VIEW}
spring.jpa.database-platform=${JPA_DATABASE_PLATFORM}

# Configuración de JWT
jwt.secret=${JWT_SECRET}
jwt.expiration=${JWT_EXPIRATION}

# Configuración de correo electrónico
spring.mail.host=${MAIL_HOST}
spring.mail.port=${MAIL_PORT}
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=${MAIL_SMTP_AUTH}
spring.mail.properties.mail.smtp.starttls.enable=${MAIL_STARTTLS}

# URLs de clientes
url.reset-password-client1=${RESET_PASSWORD_URL}
url.unlock-account-client1=${UNLOCK_ACCOUNT_URL}

# URL del dominio
url.dominio=${DOMINIO_URL}
```

## Tecnologías utilizadas

- **Spring Boot**: Framework para desarrollo de aplicaciones empresariales.
- **Spring Security**: Seguridad para autenticación y autorización.
- **JWT (JSON Web Token)**: Manejo de autenticación basada en tokens.
- **Spring Data JPA**: Interacción con bases de datos relacionales.
- **PostgreSQL**: Base de datos para almacenamiento de usuarios.
- **Lombok**: Reducción de código repetitivo mediante generación de métodos.
- **Docker**: Contenerización del microservicio.
- **OpenAPI**: Documentación del API con Swagger.

## Funcionalidades principales

- **Inicio de sesión**: Autenticación de usuarios mediante username y password.
- **Generación de JWT**: Tokens firmados para la autenticación y autorización de usuarios.

## Instalación y ejecución

### Requisitos previos
- **Java 17 o superior**
- **Maven**
- **Docker** (opcional, para contenerización)

### Configuración local

1. Clona el repositorio:
   ```bash
   git clone <url-del-repositorio>
   ```

2. Accede al directorio del proyecto:
   ```bash
   cd loginFashionHub
   ```

3. Configura las variables de entorno mencionadas anteriormente en un archivo `.env` o directamente en tu entorno local.

4. Construye el proyecto:
   ```bash
   mvn clean install
   ```

5. Ejecuta la aplicación:
   ```bash
   java -jar target/loginfashionhub-0.0.1-SNAPSHOT.jar
   ```

### Uso con Docker

1. Construye la imagen Docker:
   ```bash
   docker build -t loginfashionhub .
   ```

2. Ejecuta el contenedor:
   ```bash
   docker run -p 3002:3002 --env-file .env loginfashionhub
   ```

3. La aplicación estará disponible en: `http://localhost:3002`

## Endpoints principales

| Método | Endpoint           | Descripción                |
|--------|--------------------|----------------------------|
| POST   | `/auth/login`      | Inicio de sesión.          |

## Documentación del API

El servicio incluye documentación OpenAPI accesible en:
```
http://localhost:3002/swagger-ui.html
```

## Notas sobre la arquitectura

Este microservicio es parte de un ecosistema modular. Otros servicios pueden incluir:

- **Registro** (`registerFashionHub`): Gestión de nuevos usuarios.
- **Recuperación de contraseña** (`passwordForgotFashionHub`).
- **Cambiar contraseña** (`passwordResetFashionHub`).

Todos los servicios pueden integrarse a través de un API Gateway para simplificar la interacción con el cliente.

## Licencia 

Este proyecto está bajo la licencia [MIT](https://opensource.org/licenses/MIT).

