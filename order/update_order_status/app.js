const express = require('express');
const pedidoRoutes = require('./routes/pedidoRoutes');
const { swaggerUi, swaggerSpecs } = require('./swaggerConfig');
require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 3010;

app.use(express.json());
app.use('/api/pedidos', pedidoRoutes);

// Rutas de Swagger
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerSpecs));

app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://localhost:${PORT}`);
  console.log(`Documentación disponible en http://localhost:${PORT}/api-docs`);
});
