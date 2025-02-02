const pool = require('../config/database');

const getPurchaseHistory = async () => {
  const result = await pool.query('SELECT * FROM purchases');
  return result.rows;
};

module.exports = {
  getPurchaseHistory,
};
