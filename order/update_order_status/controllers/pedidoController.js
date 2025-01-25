const Pedido = require('../models/pedidoModel');

// Estados válidos
const ESTADOS_VALIDOS = ['pendiente', 'enviado', 'entregado', 'cancelado'];

const pedidoController = {
  create: async (req, res) => {
    try {
      const pedido = await Pedido.create(req.body);
      res.status(201).json(pedido);
    } catch (err) {
      res.status(500).json({ error: err.message });
    }
  },
  getAll: async (req, res) => {
    try {
      const pedidos = await Pedido.getAll();
      res.json(pedidos);
    } catch (err) {
      res.status(500).json({ error: err.message });
    }
  },
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
  },
  /**
   * Actualiza el estado de un pedido.
   */
  updateEstado: async (req, res) => {
    const { id } = req.params;
    const { estado } = req.body;

    try {
      // Validar que el estado sea válido
      if (!ESTADOS_VALIDOS.includes(estado)) {
        return res.status(400).json({ error: 'Estado inválido' });
      }

      const pedido = await Pedido.updateEstado(id, estado);

      if (pedido) {
        res.json(pedido);
      } else {
        res.status(404).json({ error: 'Pedido no encontrado' });
      }
    } catch (err) {
      res.status(500).json({ error: err.message });
    }
  },
  delete: async (req, res) => {
    try {
      const pedido = await Pedido.delete(req.params.id);
      if (pedido) {
        res.json(pedido);
      } else {
        res.status(404).json({ error: 'Pedido no encontrado' });
      }
    } catch (err) {
      res.status(500).json({ error: err.message });
    }
  },
};

module.exports = pedidoController;
