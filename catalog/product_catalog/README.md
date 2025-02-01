# HOLA MUNDO 4

# Product Catalog - GraphQL API

This project is a microservice for managing a product catalog, developed with Flask and Ariadne (GraphQL). It uses MongoDB as the database and follows a modular approach to facilitate maintenance and scalability. 📦

## Features ✨
- Create, read, update, and delete products.
- API schema defined with GraphQL.
- MongoDB integration for data persistence.
- Environment variable management with `dotenv`.
- CORS enabled for external clients. 🌍

## Setup 🚀

1. Clone this repository:
   ```bash
   git clone <REPOSITORY_URL>
   cd product_catalog
   ```

2. Install dependencies in a virtual environment:
   ```bash
   python -m venv .venv
   source .venv/bin/activate  # On Windows: .venv\Scripts\activate
   pip install -r requirements.txt
   ```

3. Configure environment variables in a `.env` file:
   ```env
   MONGO_URI='YOUR_DATABASE_URI'
   DB_NAME='YOUR_DB_NAME'
   SERVER_PORT='YOUR_PORT'
   ```

4. Run the application:
   ```bash
   python app/main.py
   ```

5. Access the GraphQL endpoint:
   ```
   http://localhost:5000/graphql
   ```

## GraphQL Schema 📑

### **Queries** 🔍
```graphql
type Query {
    getProduct(id: ID!): Product
    listProducts: [Product]
}
```

### **Mutations** 🔨
```graphql
type Mutation {
    createProduct(name: String!, price: Float!): Product
    updateProduct(id: ID!, name: String, price: Float): Product
    deleteProduct(id: ID!): Boolean
}
```

### **Types** 🏷️
```graphql
type Product {
    id: ID!
    name: String!
    price: Float!
}
```

## Usage Examples 🧑‍💻

### Create a Product
```graphql
mutation CreateProduct {
    createProduct(name: "Laptop", price: 999.99) {
        id
        name
        price
    }
}
```

### Get the List of Products
```graphql
query {
    listProducts {
        id
        name
        price
    }
}
```

### Update a Product
```graphql
mutation UpdateProduct {
    updateProduct(id: "12345", name: "Gaming Laptop", price: 1299.99) {
        id
        name
        price
    }
}
```

### Delete a Product
```graphql
mutation DeleteProduct {
    deleteProduct(id: "12345")
}
```

---

## License 📄

This project is licensed under the terms of the [MIT License](LICENSE).

