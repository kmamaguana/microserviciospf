const pool = require('../config/database');

class PurchaseHistoryService {
  static async getAll() {
    try {
      const result = await pool.query('SELECT * FROM purchases');
      return result.rows;
    } catch (error) {
      console.error('Database query error:', error);
      throw new Error('Error retrieving purchase history');
    }
  }
}

module.exports = PurchaseHistoryService;
