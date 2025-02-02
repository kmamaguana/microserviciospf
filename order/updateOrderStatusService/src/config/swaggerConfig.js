const swaggerJsdoc = require('swagger-jsdoc');
const swaggerUi = require('swagger-ui-express');

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
        url: 'http://localhost:3010',
        description: 'Servidor local',
      },
    ],
  },
  apis: ['./src/routes/*.js'],
};

const swaggerSpecs = swaggerJsdoc(swaggerOptions);

module.exports = { swaggerUi, swaggerSpecs };
