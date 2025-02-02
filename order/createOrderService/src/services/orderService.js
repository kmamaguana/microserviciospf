const OrderRepository = require('../repositories/orderRepository');

class OrderService {
  async createOrder(data) {
    if (!data.customer || !data.product || !data.quantity || !data.status) {
      throw new Error('All fields are required');
    }
    return await OrderRepository.create(data);
  }
}

module.exports = new OrderService();
