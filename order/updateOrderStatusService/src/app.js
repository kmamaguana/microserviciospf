const express = require('express');
const cors = require('cors');  // Import the cors package
const pedidoRoutes = require('./routes/orderRoutes');
const { swaggerUi, swaggerSpecs } = require('./config/swaggerConfig');
require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 3010;

// Enable CORS for all routes
app.use(cors()); // Allows requests from any origin

// If you want to restrict origins, you can do something like:
 /*
app.use(cors({
  origin: ['http://example.com', 'http://localhost:3000'] // Change this to the origins you need
}));
*/

app.use(express.json());

// API routes
app.use('/api/pedidos', pedidoRoutes);

// Swagger routes
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerSpecs));

app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
  console.log(`Documentation available at http://localhost:${PORT}/api-docs`);
});
