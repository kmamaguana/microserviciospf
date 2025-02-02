# Order Management API 🚀

## 📖 Description ✨

**RESTful API** for managing orders with interactive Swagger documentation. Developed with:

- **Node.js** and **Express** for the backend.
- **PostgreSQL** as the database.
- **Swagger** for automatic documentation.

---

## ⚡ Main Features

- **CRUD Endpoints**:
  - `GET /api/orders`: Retrieve all orders.
- **Swagger UI Documentation**: Interact with the API directly from your browser.
- **Environment variables configuration**.
- **CORS Support**: For cross-origin requests.

---

## 🛠️ Technologies Used

| Technology       | Purpose                                     |
|------------------|---------------------------------------------|
| Node.js          | JavaScript runtime environment             |
| Express          | Framework for building the API             |
| PostgreSQL       | Persistent data storage                    |
| Swagger          | Interactive API documentation              |
| Docker           | Application containerization               |

---

## 🚀 Getting Started

### 📌 Prerequisites

1. **Node.js v18+**: [Download Node.js](https://nodejs.org/)
2. **PostgreSQL**: [Install PostgreSQL](https://www.postgresql.org/download/)
3. **Docker** (Optional): [Install Docker](https://docs.docker.com/get-docker/)

### 🔧 Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/kmamaguana/microserviciospf.git
   cd microserviciospf/order/readOrderDetailsService
   ```

2. **Install dependencies**:
   ```bash
   npm install
   ```

3. **Configure environment**:
   - Create a `.env` file:
     ```env
     DB_HOST=localhost
     DB_PORT=5432
     DB_USER=your_user
     DB_PASSWORD=your_password
     DB_NAME=orders_db
     PORT=4050  # Changed to 4050!
     ```

4. **Create table in PostgreSQL**:
   ```sql
   CREATE TABLE orders (
     id SERIAL PRIMARY KEY,
     customer VARCHAR(255) NOT NULL,
     product VARCHAR(255) NOT NULL,
     quantity INTEGER NOT NULL,
     status VARCHAR(50) NOT NULL
   );
   ```

5. **Start the server**:
   ```bash
   npm start
   ```
   **Server available at**: `http://localhost:4050`

---

## 📡 API Endpoints

### 📜 Get All Orders
- **Endpoint**: `GET /api/orders`
- **Response Example**:
  ```json
  [
    {
      "id": 1,
      "customer": "John Doe",
      "product": "Laptop",
      "quantity": 2,
      "status": "Pending"
    }
  ]
  ```

---

## 📚 Swagger Documentation

Access the **interactive real-time documentation**:
```
http://localhost:4050/api-docs
```

![Swagger UI Preview](https://miro.medium.com/v2/resize:fit:1400/1*F4m2YvJLgG0Y4Z6VbooVBw.png)

---

## 🐳 Run with Docker

1. **Build image**:
   ```bash
   docker build -t order-api .
   ```

2. **Run container**:
   ```bash
   docker run -p 4050:4050 --env-file .env order-api
   ```

---

## 🧪 Test the API

**Example with cURL**:
```bash
# Get all orders
curl http://localhost:4050/api/orders
```

---

## 🔍 Project Structure

```
📁 readOrderDetailsService
├── 📁 src
│   ├── app.js
│   ├── 📁 config
│   │   ├── database.js
│   │   └── swaggerConfig.js
│   ├── 📁 controllers
│   │   └── orderController.js
│   ├── 📁 models
│   │   └── orderModel.js
│   ├── 📁 repositories
│   │   └── orderRepository.js
│   ├── 📁 routes
│   │   └── orderRoutes.js
│   └── 📁 services
│       └── orderService.js
├── .env
├── .gitignore
├── Dockerfile
├── package.json
└── README.md
```

---

## 🛠️ Environment Variables

| Variable        | Description                        | Default Value   |
|-----------------|------------------------------------|-----------------|
| `DB_HOST`       | PostgreSQL host                   | `localhost`     |
| `DB_PORT`       | PostgreSQL port                   | `5432`          |
| `DB_USER`       | Database user                     | -               |
| `DB_PASSWORD`   | Database password                 | -               |
| `DB_NAME`       | Database name                     | `orders_db`     |
| `PORT`          | Server port                       | `4050`          |

---

## 📝 License

This project is licensed under the **MIT** license. See [LICENSE](LICENSE) for more details.
