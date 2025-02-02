const swaggerJsDoc = require('swagger-jsdoc');

// Configuración de Swagger
const swaggerOptions = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'API de Pedidos',
      version: '1.0.0',
      description: 'API para la gestión de pedidos. Solo permite crear pedidos.',
    },
    servers: [
      {
        url: 'http://localhost:3000',
        description: 'Servidor local',
      },
    ],
  },
  apis: ['./routes/*.js'], // Ruta a los archivos donde se definen las rutas
};

const swaggerDocs = swaggerJsDoc(swaggerOptions);

module.exports = swaggerDocs;
