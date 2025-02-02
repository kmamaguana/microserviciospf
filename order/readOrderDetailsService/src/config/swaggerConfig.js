const swaggerJsdoc = require('swagger-jsdoc');
const path = require('path');

const routesPath = path.join(__dirname, '../routes/*.js');

const swaggerOptions = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'Order Management API',
      version: '1.0.0',
      description: 'API para gestión de pedidos',
    },
    servers: [
      {
        url: `http://localhost:${process.env.PORT || 4050}/api`,
        description: 'Servidor local',
      },
    ],
  },
  apis: [routesPath],
};

const swaggerDocs = swaggerJsdoc(swaggerOptions);

module.exports = swaggerDocs;