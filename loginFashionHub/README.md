# LoginFashionHub

## Descripción

**LoginFashionHub** es un sistema de gestión de autenticación y autorización para aplicaciones, diseñado para ofrecer funcionalidades avanzadas como el inicio de sesión, registro, restablecimiento de contraseñas, desbloqueo de cuentas, auditoría y programación de eliminación de cuentas.

Este proyecto está construido utilizando Spring Boot y cuenta con soporte para JWT, repositorios para manejo de entidades en bases de datos, y un sistema de programación para la eliminación automática de cuentas.

## Estructura del Proyecto

```plaintext
└── 📁loginfashionhub
    └── 📁component
        └── AccountDeletionScheduler.java
    └── 📁config
        └── ApplicationConfig.java
        └── PostgresqlConfig.java
        └── SecurityConfig.java
    └── 📁controller
        └── AdminController.java
        └── AuthController.java
        └── UserController.java
    └── 📁jwt
        └── JwtAuthenticationFilter.java
        └── JwtUtils.java
    └── 📁model
        └── AccountUnlockToken.java
        └── AuditLog.java
        └── PasswordResetToken.java
        └── Role.java
        └── User.java
    └── 📁repository
        └── AccountUnlockTokenRepository.java
        └── AuditLogRepository.java
        └── PasswordResetTokenRepository.java
        └── UserRepository.java
    └── 📁request
        └── LoginRequest.java
        └── PasswordChangeRequest.java
        └── RegisterAdminRequest.java
        └── RegisterRequest.java
        └── UserUpdateRequest.java
    └── 📁response
        └── AuthResponse.java
        └── UserResponse.java
    └── 📁service
        └── AccountUnlockService.java
        └── AuditLogService.java
        └── AuthService.java
        └── 📁impl
            └── AccountUnlockServiceImpl.java
            └── AuditLogServiceImpl.java
            └── AuthServiceImpl.java
            └── PasswordResetServiceImpl.java
            └── UserServiceImpl.java
        └── PasswordResetService.java
        └── UserService.java
    └── LoginFashionHubApplication.java
```

## Tecnologías utilizadas

- **Spring Boot**: Framework para desarrollo rápido de aplicaciones empresariales.
- **Spring Security**: Seguridad para control de acceso y autenticación.
- **Spring Data JPA**: Acceso a bases de datos relacionales con Hibernate.
- **JWT (JSON Web Token)**: Sistema de token para autenticación.
- **Lombok**: Para generación automática de getters, setters, constructores y otros métodos.
- **Maven**: Para la gestión de dependencias del proyecto.
- **PostgreSQL**: Base de datos utilizada para almacenamiento.

## Funcionalidades principales

- **Autenticación**: Inicio de sesión mediante username y contraseña con soporte JWT.
- **Registro**: Registro de nuevos usuarios.
- **Cambio de contraseña**: Proceso para modificar la contraseña de un usuario.
- **Desbloqueo de cuentas**: Recuperación de cuentas bloqueadas mediante token.
- **Auditoría**: Registro de actividades y accesos dentro del sistema.
- **Eliminación programada de cuentas**: Automatización de la eliminación de cuentas pasados 31 días desde su solicitud.

## Instalación y ejecución

1. Clona el repositorio:
   ```bash
   git clone <url-del-repositorio>
   ```

2. Accede al directorio del proyecto:
   ```bash
   cd loginfashionhub
   ```

3. Construye el proyecto utilizando Maven:
   ```bash
   mvn clean install
   ```

4. Configura el archivo `application.properties` con los detalles de conexión a la base de datos.

5. Ejecuta la aplicación:
   ```bash
   java -jar target/loginfashionhub.jar
   ```

6. Accede al servidor en tu navegador en la dirección: `http://localhost:8080`

## Desarrollo

Para contribuir al proyecto, sigue estos pasos:

1. **Fork** el repositorio.
2. Haz un **clone** de tu repositorio forked:
   ```bash
   git clone <tu-repositorio-forked>
   ```
3. Crea una nueva **rama** para tu nueva funcionalidad:
   ```bash
   git checkout -b feature/mi-nueva-funcionalidad
   ```
4. Realiza tus cambios y **comenta** las modificaciones de acuerdo con los principios de **Commit Conventional**.
5. Haz **push** de tus cambios al repositorio remoto:
   ```bash
   git push origin feature/mi-nueva-funcionalidad
   ```
6. Abre un **Pull Request** en el repositorio original para revisar tus cambios.

## Licencia

Este proyecto está bajo la licencia [MIT](https://opensource.org/licenses/MIT).

```

Este README.md proporciona una visión general del proyecto, cómo configurarlo, ejecutarlo y contribuir a su desarrollo.