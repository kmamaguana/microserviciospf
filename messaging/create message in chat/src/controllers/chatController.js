const Message = require('../models/messageModel');

// Crear un nuevo mensaje
const createMessage = async (username, content) => {
    try {
        const message = new Message({ username, content });
        await message.save();
        return message;
    } catch (err) {
        console.error('Error al crear mensaje:', err.message);
        throw new Error('No se pudo crear el mensaje');
    }
};

// Leer todos los mensajes
const getMessages = async () => {
    try {
        return await Message.find().sort({ timestamp: -1 });
    } catch (err) {
        console.error('Error al obtener mensajes:', err.message);
        throw new Error('No se pudieron obtener los mensajes');
    }
};

module.exports = { createMessage, getMessages };
