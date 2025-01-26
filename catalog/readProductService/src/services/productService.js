const Product = require("../models/productModel");

/**
 * Fetches all products from the database.
 * @returns {Promise<Array>} List of products.
 */
const getAllProducts = async () => {
  return await Product.find();
};

module.exports = { getAllProducts };
