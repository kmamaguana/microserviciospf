from ariadne import gql

type_defs = gql("""
    type Query {
        getProduct(id: ID!): Product
        listProducts: [Product]
    }

    type Mutation {
        createProduct(name: String!, price: Float!): Product
        updateProduct(id: ID!, name: String, price: Float): Product
        deleteProduct(id: ID!): Boolean
    }

    type Product {
        id: ID!
        name: String!
        price: Float!
    }
""")
