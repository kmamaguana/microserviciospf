const swaggerJsDoc = require('swagger-jsdoc');

const swaggerOptions = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'Order Management API',
      version: '1.0.0',
      description: 'API for managing orders.',
    },
    servers: [{ url: 'http://localhost:3000', description: 'Local Server' }],
    components: {
      schemas: {
        Order: {
          type: 'object',
          required: ['customer', 'product', 'quantity', 'status'],
          properties: {
            id: { type: 'integer', example: 1 },
            customer: { type: 'string', example: 'John Doe' },
            product: { type: 'string', example: 'T-shirt' },
            quantity: { type: 'integer', example: 3 },
            status: { type: 'string', example: 'pending' },
          },
        },
      },
      responses: {
        OrderCreated: {
          description: 'Order successfully created',
          content: { 'application/json': { schema: { $ref: '#/components/schemas/Order' } } },
        },
      },
    },
  },
  apis: ['./src/routes/*.js'],
};

module.exports = swaggerJsDoc(swaggerOptions);
