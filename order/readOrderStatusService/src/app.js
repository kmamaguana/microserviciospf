const express = require('express');
const WebSocket = require('ws');
const dotenv = require('dotenv');
const connectDB = require('./utils/db');
const orderService = require('./services/orderService');

// Cargar las variables de entorno desde el archivo .env
dotenv.config();

// Crear una aplicación Express (aún para otros usos)
const app = express();
connectDB();

// Crear el servidor WebSocket
const wss = new WebSocket.Server({ noServer: true });

// Escuchar conexiones WebSocket
wss.on('connection', (ws) => {
  console.log('Cliente conectado');
  
  // Recibir mensajes desde el cliente
  ws.on('message', (message) => {
    console.log('Mensaje recibido: ', message);
    
    // Procesar el mensaje (esperamos que el mensaje sea un ID de pedido)
    const orderId = message;

    // Obtener el estado del pedido
    orderService.getOrderStatus(orderId)
      .then(status => {
        // Enviar la respuesta al cliente
        ws.send(JSON.stringify({ orderId, status }));
      })
      .catch(err => {
        ws.send(JSON.stringify({ error: 'Pedido no encontrado' }));
      });
  });

  // Enviar un mensaje al cliente cuando se conecte
  ws.send(JSON.stringify({ message: 'Conexión establecida. Envíame un ID de pedido.' }));
});

// Configurar el servidor Express para usar WebSocket
const server = app.listen(process.env.PORT, () => {
  console.log(`Servidor Express corriendo en el puerto ${process.env.PORT}`);
});

// Manejar la actualización de WebSocket
server.on('upgrade', (request, socket, head) => {
  wss.handleUpgrade(request, socket, head, (ws) => {
    wss.emit('connection', ws, request);
  });
});
