# 🛒 **Order Status WebSocket Service**

This service allows you to check the status of an order in real-time via WebSocket. Clients can connect to the server and send an `orderId` to receive the status of the corresponding order.

## 🚀 Technologies Used

- **Node.js**: JavaScript runtime for the server.
- **Express**: Framework for managing the HTTP server.
- **WebSocket (ws)**: For real-time communication between the client and the server.
- **Mongoose**: For connecting to MongoDB and managing data.
- **dotenv**: For securely handling environment variables.
- **MongoDB**: NoSQL database for storing order information.

## 📂 Directory Structure

```
└── 📁readOrderStatusService
    └── 📁src
        └── app.js
        └── 📁config
            └── config.js
            └── swaggerConfig.js
        └── 📁controllers
            └── orderController.js
        └── 📁models
            └── orderModel.js
        └── 📁routes
            └── orderRoutes.js
        └── 📁services
            └── orderService.js
    └── .env
    └── .gitignore
    └── Dockerfile
    └── package-lock.json
    └── package.json
    └── README.md
```

## 🛠️ Setup

### 1. **Clone the repository**

Clone this repository to your local machine:

```bash
git clone https://github.com/kmamaguana/microserviciospf.git
cd microserviciospf/order/readOrderStatusService
```

### 2. **Install dependencies**

Install the necessary dependencies:

```bash
npm install
```

### 3. **Configure environment variables**

Create a `.env` file in the root of the project with the following configuration:

```env
PORT=3014
DB_USER=root
DB_PASSWORD=example
DB_HOST=localhost
DB_PORT=27058
DB_NAME=messages
AUTH_SOURCE=admin
```

Make sure to adjust the database credentials as needed.

### 4. **Start the server**

To start the server, run the following command:

```bash
npm start
```

The WebSocket server will be available on the configured port (default is `3014`).

---

## 📄 WebSocket, HTTP, and Swagger API Usage

### 1. **WebSocket Server**

- **URL**: `ws://localhost:3014`
- **Usage**: Connect to the WebSocket server using any WebSocket client (e.g., Postman, Insomnia, or a WebSocket client in your application).
- **Example of Message**: Send the `orderId` to the server.

Example:

```json
"12345"
```

- **Response**: The server will respond with the order status.

Example:

```json
{
  "orderId": "12345",
  "status": "Shipped"
}
```

If the order is not found, you will receive an error message:

```json
{
  "error": "Order not found"
}
```

---

### 2. **HTTP REST API**

For testing via HTTP (e.g., Postman):

- **URL**: `http://localhost:3014/api/order/{id}/status`
- **Method**: `GET`
- **Example Request**: `GET http://localhost:3014/api/order/12345/status`
  
The response will be the status of the order:

```json
{
  "status": "Shipped"
}
```

If the order is not found:

```json
{
  "message": "Order not found"
}
```

---

### 3. **Swagger Documentation**

Once the server is running, you can access the Swagger API documentation:

- **URL**: `http://localhost:3014/api-docs`
- **Usage**: Swagger will provide a detailed overview of the available HTTP endpoints, including request and response examples.

---

## 📂 Important Files

- **`.env`**: Contains environment variables for configuring the port and database.
- **`src/app.js`**: Configures the Express server and manages WebSocket connections.
- **`src/services/orderService.js`**: Contains the logic to retrieve the order status.
- **`src/models/orderModel.js`**: Defines the schema for orders in MongoDB.
- **`src/controllers/orderController.js`**: Handles the HTTP request logic for retrieving order status.
- **`src/routes/orderRoutes.js`**: Defines the API routes for HTTP requests.
- **`src/config/swaggerConfig.js`**: Configures the Swagger documentation.
- **`.gitignore`**: Excludes files and folders such as `node_modules` and `.env`.

---

## 🚀 Running in Docker

You can also run this service inside a Docker container. Here’s how you can set it up:

### **1. Build the Docker image**

```bash
docker build -t order-status-service .
```

### **2. Run the Docker container**

```bash
docker run -p 3014:3014 --env-file .env order-status-service
```

### **3. Access the service**

Once the container is running, you can access the WebSocket server, HTTP API, and Swagger documentation at:

- **WebSocket URL**: `ws://localhost:3014`
- **HTTP URL**: `http://localhost:3014/api/order/{id}/status`
- **Swagger Documentation**: `http://localhost:3014/api-docs`

---

## 💡 Example Flow

Here’s how the flow of actions would look like:

1. **WebSocket**: 
   - Connect to `ws://localhost:3014`
   - Send the message `"12345"` (an `orderId`)
   - Receive the order status response: `{ "orderId": "12345", "status": "Shipped" }`

2. **HTTP API**:
   - Send a `GET` request to `http://localhost:3014/api/order/12345/status`
   - Receive the status response: `{ "status": "Shipped" }`

3. **Swagger**:
   - Visit `http://localhost:3014/api-docs` to see the API documentation and test the endpoints interactively.

---

## 📝 License

This project is licensed under the MIT License. See the `LICENSE` file for more details.
