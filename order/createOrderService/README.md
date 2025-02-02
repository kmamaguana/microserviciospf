# 🛒 Order Management API

## 📌 Overview
Order Management API is a RESTful service for managing customer orders.  
It allows users to **create orders** with details like customer name, product, quantity, and status.

This project follows **Clean Code principles** and **SOLID architecture**, using:
- **Node.js + Express.js** for the server
- **PostgreSQL** for database management
- **Swagger** for API documentation
- **Docker** for containerization

---

## 🚀 Features
✅ **Create Orders**: Register new customer orders in the database.  
✅ **Swagger Documentation**: View API docs at `/api-docs`.  
✅ **Database Integration**: Uses PostgreSQL with a repository pattern.  
✅ **CORS Enabled**: Supports cross-origin requests.  
✅ **Docker Support**: Easily deploy using Docker.  
✅ **Unit & Integration Tests**: Uses Jest and Supertest for testing.

---

## 📦 Technologies Used
| Technology | Description |
|------------|------------|
| **Node.js** | JavaScript runtime for backend development |
| **Express.js** | Web framework for building APIs |
| **PostgreSQL** | Relational database system |
| **Swagger** | API documentation and testing |
| **Docker** | Containerization for easy deployment |
| **Jest + Supertest** | Testing framework for APIs |

---

## 📂 Project Structure
```
📁 createOrderService
│── 📁 src
│   ├── 📁 config          # Configuration files (Database, Swagger)
│   ├── 📁 controllers     # Handles HTTP requests
│   ├── 📁 models          # Defines data models
│   ├── 📁 repositories    # Handles database operations
│   ├── 📁 services        # Business logic layer
│   ├── 📁 routes          # API routes
│   ├── app.js             # Main server file
│── .env                   # Environment variables
│── .gitignore             # Git ignore file
│── Dockerfile             # Docker configuration
│── package.json           # Node.js dependencies
│── README.md              # Project documentation
```

---

## ⚙️ Installation & Setup

### 1️⃣ **Clone the Repository**
```bash
git clone https://github.com/kmamaguana/microserviciospf.git
cd microserviciospf/order/createOrderService
```

### 2️⃣ **Install Dependencies**
```bash
npm install
```

### 3️⃣ **Set Up Environment Variables**
Create a `.env` file in the root directory:
```
PORT=3000
DB_HOST=localhost
DB_PORT=5432
DB_USER=your_user
DB_PASSWORD=your_password
DB_NAME=your_database
```

### 4️⃣ **Run the Application**
```bash
npm start
```
The server will start on **`http://localhost:3000`**  
Swagger API docs will be available at **`http://localhost:3000/api-docs`** 📄

---

## 🛠️ API Endpoints

### 🔹 **Create a New Order**
**Endpoint:** `POST /api/orders`  
**Description:** Creates a new order with customer details.  
**Request Body:**
```json
{
  "customer": "John Doe",
  "product": "T-shirt",
  "quantity": 3,
  "status": "pending"
}
```
**Success Response (201):**
```json
{
  "id": 1,
  "customer": "John Doe",
  "product": "T-shirt",
  "quantity": 3,
  "status": "pending"
}
```
**Error Responses:**
- `400 Bad Request` → Missing required fields.
- `500 Internal Server Error` → Database or server issue.

📌 **For full API documentation, visit** [http://localhost:3000/api-docs](http://localhost:3000/api-docs) 📑

---

## 🐳 Running with Docker
### Build and Run the Docker Container
```bash
docker build -t order-management .
docker run -p 3000:3000 --env-file .env order-management
```

---

## ✅ Testing
### Run Unit & Integration Tests
```bash
npm test
```
The tests use **Jest** and **Supertest** to validate API functionality.

---

## 🔗 Useful Commands
| Command | Description |
|---------|-------------|
| `npm start` | Start the server |
| `npm run dev` | Start the server with **Nodemon** (auto-restart) |
| `npm run lint` | Run ESLint to check code quality |
| `npm test` | Run unit and integration tests |

---

## 📌 License
This project is **open-source** and available under the **MIT License**.

---

## 👨‍💻 Author
Developed by **Your Name** 🚀  
GitHub: [your-username](https://github.com/your-username)  
LinkedIn: [your-profile](https://linkedin.com/in/your-profile)  

---

### 🎯 Now your project has a **clear, structured, and professional README**! Let me know if you need modifications. 🚀
```