require('dotenv').config();
const mongoose = require('mongoose');

// MongoDB configuration
const dbConfig = {
  dbURI: `mongodb://${process.env.DB_USER}:${process.env.DB_PASSWORD}@${process.env.DB_HOST}:${process.env.DB_PORT}/${process.env.DB_NAME}?authSource=${process.env.AUTH_SOURCE || 'admin'}`,
};

// Función para conectar a la base de datos
const connectDB = async () => {
  try {
    await mongoose.connect(dbConfig.dbURI, { useNewUrlParser: true, useUnifiedTopology: true });
    console.log('MongoDB Connected');
  } catch (error) {
    console.error('Error connecting to MongoDB:', error);
    process.exit(1); // Termina el proceso si no se puede conectar a la base de datos
  }
};

module.exports = { connectDB, dbConfig };
