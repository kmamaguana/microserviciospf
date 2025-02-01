const express = require('express');
const purchaseHistoryRoutes = require('./routes/purchaseHistoryRoutes');
const setupSwagger = require('./config/swagger');

const app = express();

// Middleware
app.use(express.json());

// Routes
app.use('/api/purchases', purchaseHistoryRoutes);

// Swagger Docs
setupSwagger(app);

module.exports = app;
