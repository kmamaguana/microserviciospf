// src/routes/websocket.js (real_time_chat_service)
const WebSocket = require('ws');

const initWebSocket = (server) => {
  const wss = new WebSocket.Server({ server });

  wss.on('connection', (ws) => {
    console.log('Cliente conectado a real_time_chat_service');

    // Manejo de mensajes enviados por los clientes
    ws.on('message', (data) => {
      try {
        const { action, payload } = JSON.parse(data);

        if (action === 'newMessage') {
          const { username, content } = payload;

          console.log(`Nuevo mensaje de ${username}: ${content}`);
          
          // Enviar el mensaje a todos los clientes conectados
          wss.clients.forEach((client) => {
            if (client.readyState === WebSocket.OPEN) {
              client.send(JSON.stringify({
                action: 'newMessage',
                payload: payload
              }));
            }
          });
        } else {
          ws.send(JSON.stringify({ error: 'Acción no válida' }));
        }
      } catch (err) {
        console.error('Error procesando mensaje:', err.message);
        ws.send(JSON.stringify({ error: 'Error procesando mensaje' }));
      }
    });

    ws.on('close', () => {
      console.log('Cliente desconectado');
    });
  });
};

module.exports = { initWebSocket };
