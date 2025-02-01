const Order = require('../models/orderModel');

const getOrderStatus = async (orderId) => {
  try {
    const order = await Order.findOne({ orderId });
    return order ? order.status : null;
  } catch (error) {
    throw new Error('Error fetching order status: ' + error.message);
  }
};

module.exports = { getOrderStatus };
