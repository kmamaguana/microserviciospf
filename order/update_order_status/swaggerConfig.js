const swaggerJsdoc = require('swagger-jsdoc');
const swaggerUi = require('swagger-ui-express');

// Configuración básica para Swagger
const swaggerOptions = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'API de Pedidos',
      version: '1.0.0',
      description: 'API para gestionar pedidos',
    },
    servers: [
      {
        url: 'http://localhost:3010', // Cambia si usas otro puerto
        description: 'Servidor local',
      },
    ],
  },
  apis: ['./routes/*.js'], // Ruta de los archivos con documentación de endpoints
};

// Generar especificaciones de Swagger
const swaggerSpecs = swaggerJsdoc(swaggerOptions);

module.exports = { swaggerUi, swaggerSpecs };
