# 📝 **Create Message in Chat Service** 🚀

Welcome to the **Create Message in Chat Service**! 🗨️✨ This is where we create and manage messages in our chat system. It's part of a **real-time** chat service running on microservices architecture. 💬🔥

> This service is a key component in our messaging system that allows users to send messages, which are then relayed to all connected clients through **WebSockets**. 🎉

## 🌐 **Service Overview** 🔥

- **Port**: `3018`
- **WebSocket**: `ws://localhost:3018`
- **Database**: MongoDB 🗄️

## 🚀 **Features** 💥

- Create messages and save them to MongoDB. 📥
- Automatically relay new messages to all WebSocket clients. 🌐
- WebSocket connection for real-time messaging. 🕹️

## 🎯 **How It Works** 🤩

1. A client sends a **message** (with `username` and `content`).
2. The service **creates** a new message and stores it in MongoDB.
3. The new message is broadcasted to **all clients** connected through WebSockets. 📡

## 🛠 **Installation** 🧰

To get started, follow these steps:

### Step 1: Clone the repository

```bash
git clone https://github.com/kmamaguana/microserviciospf.git
```

### Step 2: Install dependencies

Navigate to the service folder and install the required dependencies using npm.

```bash
cd messaging/createMessageChatService
npm install
```

### Step 3: Configure your environment

Create a `.env` file in the root folder and set up the following variables:

```bash
DB_USER=yourMongoDBUser
DB_PASSWORD=yourMongoDBPassword
DB_HOST=localhost
DB_PORT=27017
DB_NAME=yourDatabaseName
AUTH_SOURCE=admin
PORT=3018
WS_URL=ws://localhost:3019
```

### Step 4: Start the service

Run the following command to start the service:

```bash
npm start
```

Your service will be running at: `http://localhost:3018`.

## 💻 **How to Test** 📲

### 1. Using WebSocket Client 🔌

Connect to the WebSocket server:

```bash
ws://localhost:3018
```

Send a message with the following structure:

```json
{
  "action": "sendMessage",
  "payload": {
    "username": "JohnDoe",
    "content": "Hello, World! 🌍"
  }
}
```

You should receive the message back in real time across all connected clients! ✨

### 2. Use Postman to Test WebSocket Connection 🔥

Although Postman doesn’t natively support WebSockets, you can use a WebSocket client like [WebSocket King Client](https://chrome.google.com/webstore/detail/websocket-king-client) to test your connection. Just connect to `ws://localhost:3018`.

## 🔧 **File Structure** 📂

Here’s a breakdown of the files in the `createMessageChatService` service:

```plaintext
📁createMessageChatService
│
├── .env                 # Environment configuration
├── .gitignore           # Ignore files/folders (node_modules, etc.)
├── package-lock.json    # Lock file for npm dependencies
├── package.json         # Project dependencies and scripts
├── README.md            # Documentation (you are here!)
│
└── 📁src
    ├── app.js           # Entry point of the app
    ├── 📁config
    │   └── db.js        # MongoDB connection setup
    ├── 📁controllers
    │   └── chatController.js   # Controller to handle message creation
    ├── 📁models
    │   └── messageModel.js     # MongoDB schema for messages
    └── 📁routes
        └── websocket.js       # WebSocket server setup
```

## 🔥 **Real-time Message Flow** 🌍

1. **Client sends a message** through WebSocket.
2. **Message is created** in MongoDB.
3. **Message is broadcasted** to all connected clients.

## 📜 **License** 🎉

This project is licensed under the **MIT License**. 🎉 Feel free to contribute and improve!

---

## 🎉 Let's Get Chatting! 🚀

Ready to send some messages in **real-time**? 🚀 Just follow the steps, and you'll be chatting in no time! If you run into any issues or have any questions, feel free to reach out! 😄
