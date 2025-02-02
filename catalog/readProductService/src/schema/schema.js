const { gql } = require("apollo-server");

/**
 * GraphQL type definitions.
 */
const typeDefs = gql`
  type Query {
    listProducts: [Product]
  }

  type Product {
    id: ID!
    name: String!
    description: String
    price: Float!
    stock: Int!
    color: String
    size: String
    material: String
    brand: String
    gender: String
    image_url: String  # Nuevo campo para la URL de la imagen
  }
`;

module.exports = typeDefs;
