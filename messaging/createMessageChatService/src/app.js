const express = require('express');
const http = require('http');
const connectDB = require('./config/db');
const { initWebSocket } = require('./routes/websocket');
require('dotenv').config();

const app = express();
const server = http.createServer(app);

// Conectar a MongoDB
connectDB();

// Iniciar WebSocket
initWebSocket(server);

// Iniciar servidor
const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
  console.log(`Servidor WebSocket en el puerto ${PORT}`);
});
