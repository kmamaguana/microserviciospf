from ariadne import gql

type_defs = gql("""
    type Query {
        getProduct(id: ID!): Product
        listProducts: [Product]
    }

    type Mutation {
        createProduct(
            name: String!,
            price: Float!,
            description: String,
            color: String,
            size: String,
            material: String,
            brand: String,
            gender: String
        ): Product

        updateProduct(
            id: ID!,
            name: String,
            price: Float,
            description: String,
            color: String,
            size: String,
            material: String,
            brand: String,
            gender: String
        ): Product

        deleteProduct(id: ID!): Boolean
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
""")
