# HOLA MUNDO 2

# 🚀 Products API

This project is a **microservice** for managing products, designed using **Flask**, **GraphQL**, and **Swagger**. It allows operations related to products, such as **creating products**, using a flexible schema connected to a MongoDB database.

## 🛠️ Requirements

- 🐍 **Python** 3.10 or higher
- 🐳 **Docker** (optional for containerized execution)

## ⚙️ Configuration

### 🌐 Environment Variables

Create a `.env` file with the following variables:

```plaintext
MONGO_URI=mongodb://<username>:<password>@<host>:<port>/
DB_NAME=<database_name>
SERVER_PORT=3005
```

Make sure to replace `<username>`, `<password>`, `<host>`, `<port>`, and `<database_name>` with the appropriate values.

## 📥 Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/kmamaguana/microserviciospf.git
   cd catalog/createProductService
   ```
   📁 **Project location**: Make sure you're in the correct folder before proceeding.

2. Install the dependencies:
   ```bash
   pip install -r requirements.txt
   ```
   ✅ **Dependencies installed**: Flask, GraphQL, Swagger, and more.

3. Run the application:
   ```bash
   python main.py
   ```
   🚀 **Application running**: The API will be available on the configured ports.

## 🔍 What does this microservice do?

This microservice provides an API to **create products**. Each product includes attributes such as name, price, description, stock, color, size, material, brand, and gender.  
It allows you to quickly integrate a product management system into larger applications, with support for flexible queries via **GraphQL**.

---

## 📘 GraphQL Schema

### 🎯 **Mutation: Create Product**

The `createProduct` mutation allows you to create a new product. Below is the schema and an example usage:

```graphql
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
```

**Mutation Example:**

```graphql
mutation CreateProduct {
    createProduct(
        name: "Sports T-shirt",
        price: 19.99,
        description: "100% cotton T-shirt",
        stock: 100,
        color: "Red",
        size: "M",
        material: "Cotton",
        brand: "Nike",
        gender: "Unisex"
    ) {
        id
        name
        description
        price
        stock
        color
        size
        material
        brand
        gender
    }
}
```

**Expected Response:**

```json
{
  "data": {
    "createProduct": {
      "id": "63f1e2a0d8e2e3b2a0f1c5e8",
      "name": "Sports T-shirt",
      "description": "100% cotton T-shirt",
      "price": 19.99,
      "stock": 100,
      "color": "Red",
      "size": "M",
      "material": "Cotton",
      "brand": "Nike",
      "gender": "Unisex"
    }
  }
}
```

---

## 🌐 Available Endpoints

- **Swagger UI**: [http://localhost:3005/swagger-ui](http://localhost:3005/swagger-ui)
- **GraphQL Playground**: [http://localhost:3005/graphql](http://localhost:3005/graphql)

---

## 🐳 Docker

To run the application in Docker:

1. Build the image:
   ```bash
   docker build -t products-api .
   ```
   🏗️ **Image built**: Docker will create an image named `products-api`.

2. Run the container:
   ```bash
   docker run -p 3005:3005 --env-file .env products-api
   ```
   🛳️ **Container running**: The API will be available at [http://localhost:3005](http://localhost:3005).

---

## 📜 License

This project is licensed under the **Apache 2.0** license.
