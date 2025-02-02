# 🛠️ Update Order Status Service

Welcome to the **Update Order Status Service**! This service handles the management of order status updates using a Node.js/Express backend, integrated with Swagger for API documentation and PostgreSQL for database management.

## 🧑‍💻 Project Structure

Here's an overview of the project structure:

```
└── 📁updateOrderStatusService
    └── 📁src
        └── app.js               # Main application file
        └── 📁config
            └── database.js      # Database configuration
            └── swaggerConfig.js # Swagger API documentation configuration
        └── 📁controllers
            └── orderController.js # Controller for handling order-related logic
        └── 📁models
            └── orderModel.js    # Order model for database schema
        └── 📁routes
            └── orderRoutes.js   # API routes for order endpoints
        └── 📁services
            └── orderService.js  # Business logic for order management
        └── 📁utils
            └── constants.js     # Constants like valid order statuses
    └── .gitignore                 # Git ignore file
    └── Dockerfile                 # Docker configuration for the application
    └── package-lock.json          # NPM lock file for dependencies
    └── package.json               # NPM package file with dependencies
```

## 🚀 Features

- **API Endpoints**: Expose RESTful endpoints for managing order statuses.
- **Swagger Integration**: Interactive API documentation to explore and test endpoints.
- **PostgreSQL**: Persistent storage of order data and status updates.
- **CORS Support**: Allow cross-origin requests from different domains.

## ⚙️ Setup Instructions

### 1. Clone the repository:
```bash
git clone https://github.com/kmamaguana/microserviciospf.git
```

### 2. Install dependencies:
```bash
cd microserviciospf/order/updateOrderStatusService
npm install
```

### 3. Setup environment variables:
Create a `.env` file at the root of the project and add the following variables:

```plaintext
DB_HOST=your-db-host
DB_PORT=5432
DB_USER=your-db-user
DB_PASSWORD=your-db-password
DB_NAME=your-db-name
PORT=3010
```

### 4. Run the app locally:
```bash
npm start
```

Your application will be running on `http://localhost:3010`.

### 5. Access Swagger API Documentation:
Visit `http://localhost:3010/api-docs` to interact with the API and test the endpoints.

## 🐳 Docker Support

To run the service inside a Docker container, follow these steps:

### 1. Build the Docker image:
```bash
docker build -t update-order-status-service .
```

### 2. Run the Docker container:
```bash
docker run -p 3010:3010 update-order-status-service
```

Your app will be available at `http://localhost:3010`.

## 📝 API Endpoints

### 1. Update Order Status

- **URL**: `/api/orders/{id}/status`
- **Method**: `PUT`
- **Description**: Update the status of an order.
  
#### Example Request:

```bash
PUT /api/orders/123/status
Content-Type: application/json

{
  "status": "shipped"
}
```

#### Possible Responses:

- **200 OK**: The order status was updated successfully.
  
  **Response**:
  ```json
  {
    "id": 123,
    "status": "shipped",
    "updatedAt": "2025-02-02T10:00:00Z"
  }
  ```

- **400 Bad Request**: Invalid status provided.
  
  **Response**:
  ```json
  {
    "error": "Invalid status"
  }
  ```

- **404 Not Found**: The order with the provided ID was not found.
  
  **Response**:
  ```json
  {
    "error": "Order not found"
  }
  ```

- **500 Internal Server Error**: A server error occurred.
  
  **Response**:
  ```json
  {
    "error": "Internal server error"
  }
  ```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
