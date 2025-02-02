# 📜 Purchase History API  

🚀 A simple API to retrieve purchase history from a **PostgreSQL** database, built with **Node.js** and **Express**. Includes **Swagger** documentation.  

## ✨ Features  

✅ Retrieve a list of all purchases.  
✅ API documentation with **Swagger UI**.  
✅ Uses **PostgreSQL** as the database.  
✅ Clean and modular architecture.  
✅ Docker support for easy deployment.  

## 📦 Installation  

### 🔧 Prerequisites  
- **Node.js** (version 14 or higher)  
- **PostgreSQL**  
- **Docker** (optional, for containerized deployment)  

### 🛠️ Steps to Run the Project  

1️⃣ **Clone the repository**  
```bash
git clone https://github.com/kmamaguana/microserviciospf.git
cd microserviciospf/history/purchaseHistoryService
```

2️⃣ **Install dependencies**  
```bash
npm install
```

3️⃣ **Set up the PostgreSQL database**  

- Create a new database:  
  ```sql
  CREATE DATABASE purchase_db;
  ```

- Run the following SQL script to create the `purchases` table and insert sample data:  
  ```sql
  CREATE TABLE purchases (
      id SERIAL PRIMARY KEY,
      user_id INT NOT NULL,
      product VARCHAR(255) NOT NULL,
      amount DECIMAL(10,2) NOT NULL,
      purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  );

  INSERT INTO purchases (user_id, product, amount, purchase_date) VALUES
  (101, 'T-shirt', 19.99, '2024-01-15 14:30:00'),
  (102, 'Sneakers', 79.99, '2024-01-16 10:15:00'),
  (103, 'Jeans', 49.50, '2024-01-17 18:45:00');
  ```

4️⃣ **Configure environment variables**  
Create a `.env` file in the root directory and add the following:  
```env
DB_USER=your_db_user
DB_HOST=localhost
DB_NAME=purchase_db
DB_PASSWORD=your_db_password
DB_PORT=5432
PORT=5000
```

5️⃣ **Run the project**  
```bash
npm start
```
The API will be available at **http://localhost:5000** 🚀  

---

## 🔗 API Endpoints  

- **GET** `/api/purchases` → Retrieve all purchases.  

## 📖 Swagger Documentation  

📌 After running the server, open:  
```
http://localhost:5000/api-docs
```

---

## 🐳 Docker Deployment (Optional)  

1️⃣ **Build the Docker image**  
```bash
docker build -t purchase-history-service .
```

2️⃣ **Run the container**  
```bash
docker run -d -p 5000:5000 --env-file .env purchase-history-service
```
Now, the API will be running inside a container! 🎉  

---

## 🔄 Development Mode  

To enable **auto-reload** during development, use **nodemon**:  
```bash
npm install -g nodemon
npm run dev
```

---

## 📝 License  

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.  
