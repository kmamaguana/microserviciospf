const pool = require('../config/database');

const Pedido = {
  // Solo operación de lectura (obtener todos los pedidos)
  getAll: async () => {
    const query = 'SELECT * FROM pedidos';
    const result = await pool.query(query);
    return result.rows;
  },
  
  // Obtener un pedido por su ID
  getById: async (id) => {
    const query = 'SELECT * FROM pedidos WHERE id = $1';
    const result = await pool.query(query, [id]);
    return result.rows[0];
  }
};

module.exports = Pedido;
