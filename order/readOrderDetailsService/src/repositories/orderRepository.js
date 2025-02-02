const OrderModel = require('../models/orderModel');

// Repository to abstract the database logic
const OrderRepository = {
  findAllOrders: async () => {
    return await OrderModel.getAll();
  },
};

module.exports = OrderRepository;