class Order {
  constructor({ id, customer, product, quantity, status }) {
    this.id = id;
    this.customer = customer;
    this.product = product;
    this.quantity = quantity;
    this.status = status;
  }
}

module.exports = Order;
