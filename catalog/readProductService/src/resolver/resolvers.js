const { getAllProducts } = require("../services/productService");

/**
 * GraphQL resolvers to handle queries.
 */
const resolvers = {
  Query: {
    listProducts: async () => {
      try {
        return await getAllProducts(); // Reuse service logic
      } catch (error) {
        throw new Error("Error fetching products: " + error.message);
      }
    },
  },
};

module.exports = resolvers;
