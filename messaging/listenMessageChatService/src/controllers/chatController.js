const Message = require('../models/messageModel');

exports.getMessages = async () => {
  try {
    // Obtenemos los mensajes en orden descendente por timestamp
    const messages = await Message.find().sort({ timestamp: -1 });
    return messages;
  } catch (err) {
    console.error('Error al obtener mensajes:', err);
    throw err;
  }
};
