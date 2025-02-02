const pool = require('../config/database');

const Pedido = {
  create: async (data) => {
    const query = `
      INSERT INTO pedidos (cliente, producto, cantidad, estado)
      VALUES ($1, $2, $3, $4) RETURNING *`;
    const values = [data.cliente, data.producto, data.cantidad, data.estado];
    const result = await pool.query(query, values);
    return result.rows[0];
  },
};

module.exports = Pedido;
