const express = require('express');
const pedidoRoutes = require('./routes/pedidoRoutes');
const swaggerUi = require('swagger-ui-express');
const swaggerDocs = require('./config/swaggerConfig');
require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 3000;

app.use(express.json());

// Documentación con Swagger
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerDocs));

// Rutas de pedidos
app.use('/api/pedidos', pedidoRoutes);

// Iniciar el servidor
app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://localhost:${PORT}`);
  console.log(`Documentación disponible en http://localhost:${PORT}/api-docs`);
});
