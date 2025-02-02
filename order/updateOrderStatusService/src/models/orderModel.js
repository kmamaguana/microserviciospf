// src/models/pedidoModel.js
const pool = require('../config/database');

const Pedido = {

  updateEstado: async (id, estado) => {
    const query = 'UPDATE pedidos SET estado = $1 WHERE id = $2 RETURNING *';
    const values = [estado, id];
    const result = await pool.query(query, values);
    return result.rows[0];
  }

};

module.exports = Pedido;
