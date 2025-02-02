const Pedido = require('../models/pedidoModel');

const pedidoController = {
  // Obtener todos los pedidos
  getAll: async (req, res) => {
    try {
      const pedidos = await Pedido.getAll();
      res.json(pedidos);
    } catch (err) {
      res.status(500).json({ error: err.message });
    }
  },

  // Obtener un pedido por ID
  getById: async (req, res) => {
    try {
      const pedido = await Pedido.getById(req.params.id);
      if (pedido) {
        res.json(pedido);
      } else {
        res.status(404).json({ error: 'Pedido no encontrado' });
      }
    } catch (err) {
      res.status(500).json({ error: err.message });
    }
  }
};

module.exports = pedidoController;
