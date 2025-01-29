# HOLA MUNDO 2

# ReadProductService 🚀

ReadProductService is a GraphQL-based API built with Node.js, Express, and Apollo Server. It allows users to interact with a MongoDB database to fetch product details. This service also includes Swagger-based API documentation for easy exploration of endpoints. 📦

## Features ✨

- **GraphQL API**: Query the product database using GraphQL. 🔍
- **MongoDB Integration**: Fetch products from a MongoDB database. 🗄️
- **Swagger UI**: API documentation available via Swagger UI at `/api-docs`. 📖
- **CORS Support**: Configured to handle cross-origin requests from allowed origins. 🌍

## Prerequisites ⚙️

Before you start, ensure you have the following installed:

- Node.js (v14 or later) 🖥️
- MongoDB (or MongoDB Atlas for cloud database) 🛠️
- An `.env` file for environment variable configuration (example provided below) 🔑

## Installation 🛠️

### 1. Clone the repository:

```bash
git clone https://github.com/yourusername/readproductservice.git
cd readproductservice
```

### 2. Install dependencies:

```bash
npm install
```

### 3. Set up environment variables 🌱

Create a `.env` file in the root directory of the project and add the following variables:

```env
MONGO_URI=mongodb://localhost:27017/products
DB_NAME=yourDatabaseName
SERVER_PORT=3006
ALLOWED_ORIGINS=http://localhost:3006,http://your-other-allowed-origin.com
```

- `MONGO_URI`: The MongoDB connection URI. 🔗
- `DB_NAME`: The name of the MongoDB database. 🗃️
- `SERVER_PORT`: The port on which the server will run. 🌐
- `ALLOWED_ORIGINS`: Comma-separated list of allowed origins for CORS. 🚪

### 4. Run the application 🎬

```bash
npm start
```

The server will start, and you can access:

- **GraphQL endpoint**: `http://localhost:3006/graphql` 📡
- **Swagger API documentation**: `http://localhost:3006/api-docs` 📄

## Docker 🐳

You can run the application inside a Docker container for easy deployment.

### 1. Build the Docker image:

```bash
docker build -t readproductservice .
```

### 2. Run the Docker container:

```bash
docker run -p 3006:3006 --env-file .env readproductservice
```

This will start the application inside a container and map port 3006.

- **GraphQL endpoint**: `http://localhost:3006/graphql` 📡
- **Swagger API documentation**: `http://localhost:3006/api-docs` 📄

## GraphQL Queries 💬

### List Products

```graphql
query {
  listProducts {
    id
    name
    price
  }
}
```

This query will return a list of products with `id`, `name`, and `price`. 💵

## License 📝

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT). 📜