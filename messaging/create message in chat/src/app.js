const express = require('express');
const http = require('http');
const WebSocket = require('ws');
const { createMessage, getMessages } = require('./controllers/chatController'); // Asegúrate de que la ruta al controlador sea correcta
const connectDB = require('./config/db');
require('dotenv').config();

const app = express();
const server = http.createServer(app);

// Conectar a la base de datos MongoDB
connectDB();

// Middleware para procesar datos JSON en las solicitudes
app.use(express.json());

// Ruta para crear un mensaje (REST API)
app.post('/api/messages', async (req, res) => {
    const { username, content } = req.body;
    if (!username || !content) {
        return res.status(400).json({ error: 'Faltan datos: username o content' });
    }
    try {
        const newMessage = await createMessage(username, content);
        res.status(201).json(newMessage);
    } catch (err) {
        console.error('Error al crear el mensaje:', err.message);
        res.status(500).json({ error: 'Error al crear el mensaje' });
    }
});

// Ruta para obtener todos los mensajes (REST API)
app.get('/api/messages', async (req, res) => {
    try {
        const messages = await getMessages();
        res.status(200).json(messages);
    } catch (err) {
        console.error('Error al obtener los mensajes:', err.message);
        res.status(500).json({ error: 'Error al obtener los mensajes' });
    }
});

// WebSocket para chat en tiempo real
const wss = new WebSocket.Server({ server });

wss.on('connection', (ws) => {
    console.log('Cliente WebSocket conectado');

    // Manejar mensajes de WebSocket
    ws.on('message', async (data) => {
        try {
            const { action, payload } = JSON.parse(data);

            // Validar que 'payload' tenga los datos correctos
            if (!payload || !payload.username || !payload.content) {
                ws.send(JSON.stringify({ error: 'Faltan datos: username o content' }));
                return;
            }

            if (action === 'sendMessage') {
                // Crear un nuevo mensaje usando WebSocket
                const newMessage = await createMessage(payload.username, payload.content);

                // Enviar el nuevo mensaje a todos los clientes conectados
                wss.clients.forEach((client) => {
                    if (client.readyState === WebSocket.OPEN) {
                        client.send(JSON.stringify({ action: 'newMessage', payload: newMessage }));
                    }
                });
            } else if (action === 'getMessages') {
                // Obtener mensajes usando WebSocket
                const messages = await getMessages();
                ws.send(JSON.stringify({ action: 'messageHistory', payload: messages }));
            } else {
                ws.send(JSON.stringify({ error: 'Acción no válida' }));
            }
        } catch (err) {
            console.error('Error al procesar mensaje:', err.message);
            ws.send(JSON.stringify({ error: 'Error procesando mensaje' }));
        }
    });

    ws.on('close', () => {
        console.log('Cliente WebSocket desconectado');
    });
});

// Configuración del servidor
const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
    console.log(`Servidor escuchando en el puerto ${PORT}`);
});
