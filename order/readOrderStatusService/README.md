
# Order Status WebSocket Service

This service allows you to check the status of an order in real-time via WebSocket. Clients can connect to the server and send an `orderId` to receive the status of the corresponding order.

## Technologies Used

- **Node.js**: JavaScript runtime for the server.
- **Express**: Framework for managing the HTTP server.
- **WebSocket (ws)**: For real-time communication between the client and the server.
- **Mongoose**: For connecting to MongoDB and managing data.
- **dotenv**: For securely handling environment variables.
- **MongoDB**: NoSQL database for storing order information.

## Setup

### 1. **Clone the repository**

   Clone this repository to your local machine:

   ```bash
   git clone <repository_url>
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

## File Structure

The project follows a layered architecture and employs **Clean Code** principles. Below are the key files and folders:

- **`.env`**: Contains environment variables for port and database configuration.
- **`src/app.js`**: Main file that configures the Express server and WebSocket server.
- **`src/services/orderService.js`**: Logic for retrieving the order status from the database.
- **`.gitignore`**: Excludes unnecessary files and folders (e.g., `node_modules` and `.env` file).

## Using WebSocket

1. **Connect to the WebSocket server**

   To connect to the WebSocket server, you can use any WebSocket client. If you're using **Postman** or **Insomnia**, you can configure a WebSocket connection with the URL:

   ```
   ws://localhost:3014
   ```

2. **Send a message with the `orderId`**

   Once connected to the WebSocket server, send a message with the `orderId` you want to check. The server will respond with the order status.

   Example of a sent message:
   ```json
   "12345"
   ```

3. **Receive the order status**

   The server will send a message with the order status. If the order is found, the response will be:

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

## Important Files

- **`.env`**: Contains environment variables for configuring the port and database.
- **`src/app.js`**: Configures the Express server and manages WebSocket connections.
- **`src/services/orderService.js`**: Contains the logic to retrieve the order status.
- **`.gitignore`**: Excludes files and folders such as `node_modules` and `.env`.

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.
```

