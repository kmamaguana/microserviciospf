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
  /**
   * Actualiza solo el estado de un pedido.
   */
  updateEstado: async (id, estado) => {
    const query = `
      UPDATE pedidos SET estado = $1
      WHERE id = $2 RETURNING *`;
    const values = [estado, id];
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
