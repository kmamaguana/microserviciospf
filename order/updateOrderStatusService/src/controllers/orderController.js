const pedidoService = require('../services/orderService');

const pedidoController = {
  updateEstado: async (req, res) => {
    try {
      const pedido = await pedidoService.updatePedidoEstado(req.params.id, req.body.estado);
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
