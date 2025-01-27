# Hola

# PasswordResetFashionHub 🔒

## Descripción 📝

**PasswordResetFashionHub** es un microservicio diseñado para gestionar el restablecimiento de contraseñas en aplicaciones. Permite a los usuarios solicitar el restablecimiento de sus contraseñas mediante un token único, gestionando la expiración de dichos tokens y asegurando la correcta actualización de contraseñas.

Este servicio está desarrollado utilizando **Spring Boot**, **Spring Security** y **JWT** para la autenticación, y se conecta a una base de datos **PostgreSQL** para gestionar los usuarios y los tokens de restablecimiento.

## Estructura del Proyecto 📂

```plaintext
└── 📁passwordresetfashionhub
    └── 📁config
        └── ApplicationConfig.java
        └── PostgresqlConfig.java
        └── SecurityConfig.java
    └── 📁controller
        └── AuthController.java
    └── 📁model
        └── PasswordResetToken.java
        └── Role.java
        └── User.java
    └── 📁repository
        └── PasswordResetTokenRepository.java
        └── UserRepository.java
    └── 📁service
        └── PasswordResetService.java
        └── impl
            └── PasswordResetServiceImpl.java
    └── PasswordResetFashionHubApplication.java
```

## Tecnologías utilizadas ⚙️

- **Spring Boot**: Framework para desarrollo rápido de aplicaciones empresariales.
- **Spring Security**: Seguridad para control de acceso y autenticación.
- **JWT (JSON Web Token)**: Sistema de token para autenticación.
- **Spring Data JPA**: Acceso a bases de datos relacionales con Hibernate.
- **PostgreSQL**: Base de datos utilizada para almacenamiento.

## Funcionalidades principales ✨

- **Restablecimiento de contraseñas**: Permite a los usuarios cambiar su contraseña mediante un token de restablecimiento.
- **Gestión de tokens**: Generación y validación de tokens para el restablecimiento de contraseñas.

## Instalación y ejecución 🚀

### Requisitos previos
- **Java 17 o superior** ☕
- **Maven** 📦
- **PostgreSQL** (o base de datos configurada) 🗄️

### Configuración local

1. Clona el repositorio:
   ```bash
   git clone https://github.com/kmamaguana/microserviciospf.git
   ```

2. Accede al directorio del proyecto:
   ```bash
   cd user/passwordReset
   ```

3. Configura las variables de entorno mencionadas a continuación en un archivo `.env` o directamente en tu entorno local.

4. Construye el proyecto:
   ```bash
   mvn clean install
   ```

5. Ejecuta la aplicación:
   ```bash
   java -jar target/passwordresetfashionhub-0.0.1-SNAPSHOT.jar
   ```

### Configuración de Variables de Entorno ⚙️

Asegúrate de configurar las siguientes variables de entorno en el archivo `.env` o en tu entorno local:

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

# Email Configuration
spring.mail.host=${MAIL_HOST}
spring.mail.port=${MAIL_PORT}
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=${MAIL_SMTP_AUTH}
spring.mail.properties.mail.smtp.starttls.enable=${MAIL_STARTTLS}

# Client URLs
url.reset-password-client1=${RESET_PASSWORD_URL}
url.unlock-account-client1=${UNLOCK_ACCOUNT_URL}

# Dominio URL
url.dominio=${DOMINIO_URL}
```

### Uso con Docker 🐳

1. Construye la imagen Docker:
   ```bash
   docker build -t passwordresetfashionhub .
   ```

2. Ejecuta el contenedor:
   ```bash
   docker run -p 3003:3003 --env-file .env passwordresetfashionhub
   ```

3. La aplicación estará disponible en: `http://localhost:3003`

## Endpoints principales 🔑

| Método | Endpoint              | Descripción                        |
|--------|-----------------------|------------------------------------|
| POST   | `/auth/reset-password` | Restablece la contraseña de un usuario. |

## Documentación del API 📜

El servicio incluye documentación OpenAPI accesible en:
```
http://localhost:3003/swagger-ui.html
```

## Licencia 📑

Este proyecto está bajo la licencia [MIT](https://opensource.org/licenses/MIT).
```