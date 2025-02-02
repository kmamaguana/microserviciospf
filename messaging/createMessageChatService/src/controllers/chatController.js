const WebSocket = require('ws');
const Message = require('../models/messageModel');

// Use the WebSocket URL from the environment variable
const wsClient = new WebSocket(process.env.WS_URL);

const createMessage = async (username, content) => {
    try {

        const newMessage = new Message({ username, content });
        await newMessage.save();

        if (wsClient.readyState === WebSocket.OPEN) {
            wsClient.send(JSON.stringify({
                action: 'newMessage',
                payload: newMessage
            }));
        }

        return newMessage;
    } catch (err) {
        console.error('Error al crear el mensaje:', err);
        throw err;
    }
};

module.exports = { createMessage };
