const WebSocket = require('ws');
const { handleAction } = require('../controllers/chatController');

const handleWebSocket = (server) => {
    const wss = new WebSocket.Server({ server });

    wss.on('connection', (ws) => {
        console.log('Cliente conectado');

        ws.on('message', async (data) => {
            try {
                const { action, payload } = JSON.parse(data);
                await handleAction(action, payload, ws);
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
