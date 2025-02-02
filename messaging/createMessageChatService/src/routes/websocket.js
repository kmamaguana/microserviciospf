const WebSocket = require('ws');
const { createMessage } = require('../controllers/chatController');

const initWebSocket = (server) => {
  const wss = new WebSocket.Server({ server });

  wss.on('connection', (ws) => {
    console.log('Cliente conectado');

    // Manejo de mensajes enviados por los clientes
    ws.on('message', async (data) => {
      try {
        const { action, payload } = JSON.parse(data);

        if (action === 'sendMessage') {
          const { username, content } = payload;

          // Verificación de datos recibidos
          if (!username || !content) {
            ws.send(JSON.stringify({ error: 'Faltan datos: username o content' }));
            return;
          }

          // Crear un nuevo mensaje en la base de datos
          const newMessage = await createMessage(username, content);

          // Enviar el mensaje creado a todos los clientes conectados
          wss.clients.forEach((client) => {
            if (client.readyState === WebSocket.OPEN) {
              client.send(JSON.stringify({
                action: 'newMessage',
                payload: newMessage
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
