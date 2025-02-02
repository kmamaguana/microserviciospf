const orderService = require('../services/orderService');

const getOrderStatus = async (req, res, next) => {
  const { id } = req.params;
  
  try {
    const orderStatus = await orderService.getOrderStatus(id);
    if (orderStatus) {
      res.status(200).json({ status: orderStatus });
    } else {
      res.status(404).json({ message: 'Order not found' });
    }
  } catch (error) {
    next(error);
  }
};

module.exports = { getOrderStatus };
