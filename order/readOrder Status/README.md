

### **README.md**

```markdown
# Order Status API

Una API simple para consultar el estado de los pedidos. Esta API está construida con Express, MongoDB y Swagger para la documentación.

## Descripción

Esta API permite obtener el estado de un pedido dado su `orderId`. Utiliza un modelo de base de datos MongoDB para almacenar los pedidos y un servicio para recuperar su estado. La documentación de la API está disponible a través de Swagger UI.

## Tecnologías utilizadas

- **Node.js**: Para el servidor y la lógica de la API.
- **Express**: Framework para crear el servidor y manejar las rutas.
- **MongoDB**: Base de datos NoSQL para almacenar los pedidos.
- **Mongoose**: Para interactuar con MongoDB desde Node.js.
- **Swagger**: Para la documentación interactiva de la API.

## Instalación

Sigue estos pasos para instalar y ejecutar el proyecto en tu máquina local.

### Requisitos previos

- Node.js
- MongoDB (puede ser una instancia local o remota)

### Pasos para la instalación

1. Clona el repositorio:

    ```bash
    git clone https://github.com/tu-usuario/order-status-api.git
    ```

2. Navega a la carpeta del proyecto:

    ```bash
    cd order-status-api
    ```

3. Instala las dependencias:

    ```bash
    npm install
    ```

4. Crea un archivo `.env` en la raíz del proyecto y agrega tus configuraciones de MongoDB:

    ```env
    DB_USER=root
    DB_PASSWORD=example
    DB_HOST=localhost
    DB_PORT=27058
    DB_NAME=messages
    AUTH_SOURCE=admin

    PORT=5000
    ```

5. Inicia el servidor:

    ```bash
    npm start
    ```

El servidor debería estar corriendo en `http://localhost:5000`.

## Rutas disponibles

### Obtener el estado de un pedido

- **URL**: `/api/order/{id}/status`
- **Método**: `GET`
- **Descripción**: Devuelve el estado de un pedido basado en su `orderId`.
- **Parámetros**:
  - `id` (path parameter): El `orderId` del pedido.
- **Respuesta exitosa**:
  ```json
  {
    "status": "Shipped"
  }
  ```
- **Respuesta cuando no se encuentra el pedido**:
  ```json
  {
    "message": "Order not found"
  }
  ```

## Documentación de la API

La documentación de la API está disponible a través de Swagger UI. Puedes acceder a ella en:

```
http://localhost:5000/api-docs
```

## Contribuciones

Las contribuciones son bienvenidas. Si tienes alguna sugerencia o encuentras un error, por favor abre un *issue* o realiza un *pull request*.

## Licencia

Este proyecto está licenciado bajo la [MIT License](LICENSE).
```

