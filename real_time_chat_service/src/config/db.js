require('dotenv').config();
const mongoose = require('mongoose');

const URI = `mongodb://${process.env.DB_USER}:${process.env.DB_PASSWORD}@${process.env.DB_HOST}:${process.env.DB_PORT}/${process.env.DB_NAME}?authSource=${process.env.AUTH_SOURCE || 'admin'}`;

const connectDB = async () => {
    try {
        await mongoose.connect(URI, {
            useNewUrlParser: true,
            useUnifiedTopology: true,
        });
        console.log('Conexión exitosa a MongoDB');
    } catch (err) {
        console.error('Error al conectar a MongoDB:', err.message);
        process.exit(1);
    }
};

module.exports = connectDB;
