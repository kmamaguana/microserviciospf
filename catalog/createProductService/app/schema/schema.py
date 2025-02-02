from ariadne import gql

type_defs = gql("""
    scalar Upload  # 🚀 Esto permite subir archivos

    type Query {
        _empty: String
    }

    type Mutation {
        createProduct(
            name: String!,
            price: Float!,
            description: String,
            stock: Int!,
            color: String,
            size: String,
            material: String,
            brand: String,
            gender: String,
            imageFile: Upload
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
        image_url: String
    }
""")