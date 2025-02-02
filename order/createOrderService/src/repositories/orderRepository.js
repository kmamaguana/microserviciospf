const db = require('../config/database');

class OrderRepository {
  async create({ customer, product, quantity, status }) {
    const query = `
      INSERT INTO orders (customer, product, quantity, status)
      VALUES ($1, $2, $3, $4) RETURNING *`;
    const result = await db.query(query, [customer, product, quantity, status]);
    return result[0];
  }
}

module.exports = new OrderRepository();
