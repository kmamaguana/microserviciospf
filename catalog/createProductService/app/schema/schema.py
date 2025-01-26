from ariadne import gql

type_defs = gql("""
    type Query {
        # Define campos aquí si necesitas consultas en el futuro
        _empty: String
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
