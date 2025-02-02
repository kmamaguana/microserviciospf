const pool = require('../config/database');

// Model to interact directly with the database
const OrderModel = {
  getAll: async () => {
    const query = 'SELECT * FROM orders';
    try {
      const result = await pool.query(query);
      return result.rows;
    } catch (err) {
      throw new Error('Database query failed');
    }
  },
};

module.exports = OrderModel;