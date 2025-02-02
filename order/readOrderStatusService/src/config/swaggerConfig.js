const swaggerJsdoc = require('swagger-jsdoc');

const swaggerConfig = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'Order Status WebSocket Service API',
      version: '1.0.0',
      description: 'API for checking the status of orders in real-time via WebSocket',
    },
    servers: [
      {
        url: `http://localhost:${process.env.PORT}`,
      },
    ],
    components: {
      schemas: {
        OrderStatus: {
          type: 'object',
          properties: {
            orderId: {
              type: 'string',
            },
            status: {
              type: 'string',
            },
          },
        },
      },
    },
  },
  apis: ['./src/routes/orderRoutes.js'], // Ruta a tus archivos de rutas para la documentación de la API
};

const swaggerDocs = swaggerJsdoc(swaggerConfig);

module.exports = swaggerDocs;
