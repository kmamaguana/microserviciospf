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
  }
`;

module.exports = typeDefs;
