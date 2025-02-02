require('dotenv').config();
const express = require('express');
const cors = require('cors');
const purchaseHistoryRoutes = require('./routes/purchaseHistoryRoutes');
const setupSwagger = require('./config/swagger');

const app = express();

// Middleware
app.use(cors());
app.use(express.json());

// Routes
app.use('/api/purchases', purchaseHistoryRoutes);

// Swagger Docs
setupSwagger(app);

const PORT = process.env.PORT || 5000;

app.listen(PORT, () => {
  console.log('✅ Server is running!');
  console.log(`🌐 API URL: http://localhost:${PORT}/api/purchases`);
  console.log(`📄 Swagger Docs: http://localhost:${PORT}/api-docs`);
});
