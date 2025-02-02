# 🎉 **Real-Time Chat Service** 💬

Welcome to the **Real-Time Chat Service**! 🚀🎤 This service powers **real-time communication** for your messaging app! 🗨️💥 With WebSockets at its core, it broadcasts messages to all connected clients in **real-time**, creating an engaging and dynamic experience! 🔥

> Whether you're chatting with friends or managing team discussions, this service ensures that every message is delivered instantly, no matter where you are. 🌎💬

## 🌐 **Service Overview** 🌟

- **Port**: `3019` 🔌
- **WebSocket**: `ws://localhost:3019` 🌐
- **Database**: MongoDB 🗄️

## 🚀 **Features** 🚀

- **Real-time messaging** using WebSocket! 💨
- **Instant delivery** of new messages to all connected clients. 📡
- **MongoDB integration** for storing messages. 📝
- **Supports multiple clients** chatting simultaneously. 🎮

## 🎯 **How It Works** 💡

1. Clients connect via **WebSocket** to the server. 🌐
2. Messages are **broadcasted in real-time** to all active connections. 💌
3. **MongoDB** is used to store the messages for persistence and history. 📚

## 🛠 **Installation** 🧰

Let's get your **Real-Time Chat Service** up and running! 🚀

### Step 1: Clone the repository

```bash
git clone https://github.com/kmamaguana/microserviciospf.git
```

### Step 2: Install dependencies

Navigate to the service folder and install the required dependencies with npm.

```bash
cd messaging/listenMessageChatService
npm install
```

### Step 3: Configure your environment

Create a `.env` file and define the following environment variables:

```bash
DB_USER=yourMongoDBUser
DB_PASSWORD=yourMongoDBPassword
DB_HOST=localhost
DB_PORT=27017
DB_NAME=yourDatabaseName
AUTH_SOURCE=admin
```

### Step 4: Start the service

To start the service and get everything running, simply use the command below:

```bash
npm start
```

Your service will now be available at: `http://localhost:3019`.

## 💻 **How to Test** 📲

### 1. Connect with WebSocket 🔗

To test your service, connect to the WebSocket server:

```bash
ws://localhost:3019
```

Send a message using the structure below:

```json
{
  "action": "sendMessage",
  "payload": {
    "username": "JaneDoe",
    "content": "Hello, World! 🌎"
  }
}
```

All connected clients should **receive the message** instantly! 🚀✨

### 2. Testing with Postman 📬

For Postman, use a WebSocket client extension or an external WebSocket client like [WebSocket King Client](https://chrome.google.com/webstore/detail/websocket-king-client) to interact with the WebSocket server. Use `ws://localhost:3019`.

## 🔧 **File Structure** 📂

Here’s the breakdown of the **Real-Time Chat Service** file structure:

```plaintext
📁listenMessageChatService
│
├── .env                 # Environment configuration
├── .gitignore           # Ignore files/folders (node_modules, etc.)
├── package-lock.json    # Lock file for npm dependencies
├── package.json         # Project dependencies and scripts
├── README.md            # Documentation (you are here!)
│
└── 📁src
    ├── app.js           # Main entry point for the application
    ├── 📁config
    │   └── db.js        # MongoDB connection setup
    ├── 📁controllers
    │   └── chatController.js   # Controller for handling chat logic
    ├── 📁models
    │   └── messageModel.js     # MongoDB schema for messages
    └── 📁routes
        └── websocket.js       # WebSocket setup and message handling
```

## 🔥 **How the Service Works** 🚀

1. **WebSocket Connection** 🌐: Clients establish a WebSocket connection to the server.
2. **Send a Message** ✉️: A message is sent by the client via the WebSocket.
3. **Broadcast to All Clients** 🌍: The message is broadcasted to all active WebSocket clients in real-time.
4. **Store in MongoDB** 🗃️: The message is saved to MongoDB for history.

## 📜 **License** 🎉

This project is licensed under the **MIT License**. 📝 Feel free to contribute, improve, and create something amazing! ✨

---

## 🎉 Let's Talk in Real-Time! 💬

Get your service up and running, start chatting in real-time, and never miss a message again! 🚀 If you have any issues or need help, don’t hesitate to reach out. Let’s create awesome chats together! 🥳
