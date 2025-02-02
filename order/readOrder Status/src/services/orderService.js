const Order = require('../models/orderModel');

const getOrderStatus = async (orderId) => {
  try {
    const order = await Order.findOne({ orderId });
    return order ? order.status : null;
  } catch (error) {
    throw new Error('Error al obtener el estado del pedido');
  }
};

module.exports = { getOrderStatus };
