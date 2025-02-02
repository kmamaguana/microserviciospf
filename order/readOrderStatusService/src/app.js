const express = require('express');
const dotenv = require('dotenv');
const { connectDB } = require('./config/config');
const swaggerUi = require('swagger-ui-express');
const swaggerDocs = require('./config/swaggerConfig');
const orderRoutes = require('./routes/orderRoutes');
const Order = require('./models/orderModel'); 
const WebSocket = require('ws');

// Load environment variables
dotenv.config();

// Initialize Express app
const app = express();

// Connect to MongoDB
connectDB();

// Serve Swagger documentation
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerDocs));

// Use order routes
app.use('/api', orderRoutes);

// WebSocket server
const wss = new WebSocket.Server({ noServer: true });

wss.on('connection', (ws) => {
  console.log('New WebSocket connection');
  
  // Maneja el mensaje recibido desde el cliente
  ws.on('message', async (message) => {
    console.log('Received message:', message);

    // Parsear el mensaje recibido
    const data = JSON.parse(message);

    // Suponiendo que el mensaje contiene un `orderId`
    const orderId = data.orderId;

    if (orderId) {
      try {
        // Buscar el estado del pedido en la base de datos
        const order = await Order.findOne({ orderId });

        if (order) {
          // Si se encuentra el pedido, enviar el estado
          const orderStatus = { status: order.status };
          ws.send(JSON.stringify(orderStatus));
        } else {
          // Si no se encuentra el pedido, enviar un error
          ws.send(JSON.stringify({ error: 'Order not found' }));
        }
      } catch (error) {
        console.error('Error fetching order status:', error);
        ws.send(JSON.stringify({ error: 'Internal server error' }));
      }
    } else {
      // Si no se recibe un `orderId` en el mensaje
      ws.send(JSON.stringify({ error: 'Order ID is required' }));
    }
  });

  // Enviar un mensaje inicial al cliente
  ws.send(JSON.stringify({ message: 'Welcome to the Order Status WebSocket' }));
});

// Configuración de actualización del servidor para WebSocket
const server = app.listen(process.env.PORT, () => {
  console.log(`Server running on port ${process.env.PORT}`);
  console.log(`Swagger docs available at http://localhost:${process.env.PORT}/api-docs`);
});

server.on('upgrade', (request, socket, head) => {
  wss.handleUpgrade(request, socket, head, (ws) => {
    wss.emit('connection', ws, request);
  });
});