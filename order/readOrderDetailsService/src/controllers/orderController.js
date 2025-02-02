const OrderService = require('../services/orderService');

const orderController = {
  getAll: async (req, res) => {
    try {
      const orders = await OrderService.getAllOrders();
      res.json(orders);
    } catch (err) {
      res.status(500).json({ error: 'Failed to retrieve orders', details: err.message });
    }
  },
};

module.exports = orderController;