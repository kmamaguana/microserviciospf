const express = require('express');
const http = require('http');
const connectDB = require('./config/db');
const handleWebSocket = require('./routes/websocket');
require('dotenv').config();

const app = express();
const server = http.createServer(app);

connectDB();

handleWebSocket(server);

const PORT = process.env.PORT || 3000;
server.listen(PORT, () => console.log(`Servidor escuchando en el puerto ${PORT}`));
