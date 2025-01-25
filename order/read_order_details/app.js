const express = require('express');
const pedidoRoutes = require('./routes/pedidoRoutes');
const swaggerUi = require('swagger-ui-express');
const swaggerJsdoc = require('swagger-jsdoc');
require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 3009;

// Configurar opciones de Swagger
const swaggerOptions = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'API de Pedidos',
      version: '1.0.0',
      description: 'API para la gestión de pedidos',
    },
    servers: [
      {
        url: `http://localhost:${PORT}`,
      },
    ],
  },
  // Path para los archivos que contienen la documentación de las rutas
  apis: ['./routes/pedidoRoutes.js'],
};

// Generar documentación Swagger
const swaggerDocs = swaggerJsdoc(swaggerOptions);

// Rutas de Swagger (documentación visual)
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerDocs));

app.use(express.json());
app.use('/api/pedidos', pedidoRoutes);

app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://localhost:${PORT}`);
  console.log(`Documentación de la API disponible en http://localhost:${PORT}/api-docs`);
});
