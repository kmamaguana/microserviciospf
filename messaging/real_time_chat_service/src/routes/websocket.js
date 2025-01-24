const WebSocket = require('ws');
const { createMessage, getMessages } = require('../controllers/chatController');

const handleWebSocket = (server) => {
    const wss = new WebSocket.Server({ server });

    wss.on('connection', (ws) => {
        console.log('Cliente conectado');

        ws.on('message', async (data) => {
            try {
                const { action, payload } = JSON.parse(data);

                if (action === 'sendMessage') {
                    const newMessage = await createMessage(payload.username, payload.content);
                    wss.clients.forEach((client) => {
                        if (client.readyState === WebSocket.OPEN) {
                            client.send(JSON.stringify({ action: 'newMessage', payload: newMessage }));
                        }
                    });
                } else if (action === 'getMessages') {
                    const messages = await getMessages();
                    ws.send(JSON.stringify({ action: 'messageHistory', payload: messages }));
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

module.exports = handleWebSocket;
