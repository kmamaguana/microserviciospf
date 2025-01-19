const pool = require('../config/database');

const Pedido = {
  create: async (data) => {
    const query = 'INSERT INTO pedidos (cliente, producto, cantidad, estado) VALUES ($1, $2, $3, $4) RETURNING *';
    const values = [data.cliente, data.producto, data.cantidad, data.estado];
    const result = await pool.query(query, values);
    return result.rows[0];
  },
  getAll: async () => {
    const query = 'SELECT * FROM pedidos';
    const result = await pool.query(query);
    return result.rows;
  },
  getById: async (id) => {
    const query = 'SELECT * FROM pedidos WHERE id = $1';
    const result = await pool.query(query, [id]);
    return result.rows[0];
  },
  update: async (id, data) => {
    const query = `
      UPDATE pedidos SET cliente = $1, producto = $2, cantidad = $3, estado = $4
      WHERE id = $5 RETURNING *`;
    const values = [data.cliente, data.producto, data.cantidad, data.estado, id];
    const result = await pool.query(query, values);
    return result.rows[0];
  },
  delete: async (id) => {
    const query = 'DELETE FROM pedidos WHERE id = $1 RETURNING *';
    const result = await pool.query(query, [id]);
    return result.rows[0];
  },
};

module.exports = Pedido;
