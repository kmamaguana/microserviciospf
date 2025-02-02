const OrderRepository = require('../repositories/orderRepository');

// Service layer to handle business logic
const OrderService = {
  getAllOrders: async () => {
    try {
      return await OrderRepository.findAllOrders();
    } catch (err) {
      throw new Error('Error while fetching orders');
    }
  },
};

module.exports = OrderService;