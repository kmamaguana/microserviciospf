const orderService = require('../services/orderService');

const getOrderStatus = async (req, res) => {
  try {
    const orderId = req.params.id;
    const orderStatus = await orderService.getOrderStatus(orderId);
    if (orderStatus) {
      res.status(200).json({ status: orderStatus });
    } else {
      res.status(404).json({ message: 'Order not found' });
    }
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

module.exports = { getOrderStatus };
