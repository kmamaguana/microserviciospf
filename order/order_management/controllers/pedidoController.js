const Pedido = require('../models/pedidoModel');

const pedidoController = {
  create: async (req, res) => {
    try {
      const { cliente, producto, cantidad, estado } = req.body;

      // Validar los campos requeridos
      if (!cliente || !producto || !cantidad || !estado) {
        return res.status(400).json({ error: 'Todos los campos son obligatorios' });
      }

      const pedido = await Pedido.create(req.body);
      res.status(201).json(pedido);
    } catch (err) {
      res.status(500).json({ error: err.message });
    }
  },
};

module.exports = pedidoController;
