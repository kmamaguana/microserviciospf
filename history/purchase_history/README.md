# Purchase History API

This project is a simple API for retrieving purchase history from a PostgreSQL database. It is built using **Node.js**, **Express**, and **PostgreSQL**. The API allows users to fetch a list of purchases and includes API documentation using **Swagger**.

## Features

- Retrieve a list of all purchases.
- API documentation with Swagger UI.
- PostgreSQL as the database to store purchase data.
- Clean and modular code structure using a layered architecture.

## Installation

### Prerequisites

- Node.js (version 14 or higher)
- PostgreSQL

### Steps to Run the Project

1. **Clone the repository**:

   ```bash
   git clone https://github.com/your-username/purchase-history-api.git
   cd purchase-history-api
   ```

2. **Install dependencies**:

   ```bash
   npm install
   ```

3. **Set up the PostgreSQL database**:
   
   - Create a database named `purchase_db`:
   
     ```sql
     CREATE DATABASE purchase_db;
     ```

   - Run the SQL script to create the table and insert sample data:
   
     ```sql
     -- Create table
     CREATE TABLE purchases (
         id SERIAL PRIMARY KEY,
         user_id INT NOT NULL,
         product VARCHAR(255) NOT NULL,
         amount DECIMAL(10,2) NOT NULL,
         purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
     );

     -- Insert sample data
     INSERT INTO purchases (user_id, product, amount, purchase_date) VALUES
     (101, 'T-shirt', 19.99, '2024-01-15 14:30:00'),
     (102, 'Sneakers', 79.99, '2024-01-16 10:15:00'),
     (103, 'Jeans', 49.50, '2024-01-17 18:45:00');
     ```

4. **Create a `.env` file** at the root of the project and add the following environment variables:

   ```env
   DB_USER=your_db_user
   DB_HOST=localhost
   DB_NAME=purchase_db
   DB_PASSWORD=your_db_password
   DB_PORT=5432
   PORT=5000
   ```

5. **Run the project**:

   ```bash
   npm start
   ```

6. The server will be running on `http://localhost:5000`.

## API Endpoints

- **GET** `/api/purchases`: Retrieve a list of all purchases.

## Swagger Documentation

Once the server is running, you can access the Swagger API documentation at:

```
http://localhost:5000/api-docs
```

## Development

To run the project in development mode with automatic reloading, use **nodemon**:

```bash
npm install -g nodemon
nodemon server.js
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```

---

Este README cubre la instalación, configuración, ejecución y documentación de la API, proporcionando una guía clara para cualquier desarrollador que quiera probar o contribuir al proyecto. 🚀