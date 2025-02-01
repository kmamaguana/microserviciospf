const express = require('express');
const app = express();
const connectDB = require('./utils/db');
const orderRoutes = require('./routes/orderRoutes');

// Swagger Setup
const swaggerJsDoc = require('swagger-jsdoc');
const swaggerUi = require('swagger-ui-express');

// Conectar a la base de datos
connectDB();

// Configuración de Swagger
const swaggerOptions = {
  swaggerDefinition: {
    info: {
      title: 'Order Status API',
      description: 'API para obtener el estado de los pedidos',
      version: '1.0.0',
    },
    servers: [
      {
        url: 'http://localhost:5000',
      },
    ],
  },
  apis: ['./routes/*.js'], // Rutas que contienen las definiciones de los endpoints
};

const swaggerDocs = swaggerJsDoc(swaggerOptions);

// Middleware de Swagger
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerDocs));

// Middleware
app.use(express.json());

// Rutas
app.use('/api', orderRoutes);

// Iniciar el servidor
const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
  console.log(`Servidor corriendo en el puerto ${PORT}`);
  console.log(`Documentación Swagger disponible en http://localhost:${PORT}/api-docs`);
});
