const Pedido = require('../models/orderModel');
const { ESTADOS_VALIDOS } = require('../utils/constants');

const pedidoService = {
  
  updatePedidoEstado: async (id, estado) => {
    if (!ESTADOS_VALIDOS.includes(estado)) {
      throw new Error('Estado inválido');
    }
    return await Pedido.updateEstado(id, estado);
  }
  
};

module.exports = pedidoService;
