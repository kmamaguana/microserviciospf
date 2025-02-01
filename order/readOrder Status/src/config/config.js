const mongoose = require('mongoose');
require('dotenv').config();  // Asegúrate de cargar las variables del archivo .env

// Generar la URI usando las variables de entorno
const URI = `mongodb://${process.env.DB_USER}:${process.env.DB_PASSWORD}@${process.env.DB_HOST}:${process.env.DB_PORT}/${process.env.DB_NAME}?authSource=${process.env.AUTH_SOURCE || 'admin'}`;

const connectDB = async () => {
  try {
    await mongoose.connect(URI, {
      useNewUrlParser: true,
      useUnifiedTopology: true,
    });
    console.log('MongoDB connected');
  } catch (error) {
    console.error('Error connecting to MongoDB: ', error.message);
    process.exit(1);
  }
};

module.exports = connectDB;
