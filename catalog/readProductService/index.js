const express = require("express");
const { ApolloServer } = require("apollo-server-express");
const cors = require("cors");
const swaggerUi = require("swagger-ui-express");
const swaggerDocument = require("./src/swagger/swagger.json");
const connectDB = require("./src/config/index");
const typeDefs = require("./src/schema/schema");
const resolvers = require("./src/resolver/resolvers");
require("dotenv").config();

/**
 * Parse allowed origins from the environment variable.
 * Allows multiple origins, split by commas.
 */
const parseAllowedOrigins = (origins) => {
  if (!origins) return [];
  return origins.split(",").map((origin) => origin.trim());
};

// Get allowed origins from .env
const allowedOrigins = parseAllowedOrigins(process.env.ALLOWED_ORIGINS);

(async () => {
  try {
    // Connect to the database
    await connectDB();

    // Create Express app
    const app = express();

    // Configure CORS middleware
    app.use(
      cors({
        origin: (origin, callback) => {
          if (!origin || allowedOrigins.includes(origin)) {
            callback(null, true);
          } else {
            callback(new Error("Not allowed by CORS"));
          }
        },
        credentials: true, // Allow cookies and credentials
      })
    );

    // Serve Swagger documentation
    app.use("/api-docs", swaggerUi.serve, swaggerUi.setup(swaggerDocument));

    // Create Apollo Server
    const server = new ApolloServer({
      typeDefs,
      resolvers,
    });

    // Start Apollo Server
    await server.start();

    // Apply Apollo middleware to the Express app
    server.applyMiddleware({ app, path: "/graphql" });

    // Start the Express app
    app.listen(process.env.SERVER_PORT, () => {
      console.log(`🚀 GraphQL running at http://localhost:${process.env.SERVER_PORT}/graphql`);
      console.log(`📄 Swagger docs available at http://localhost:${process.env.SERVER_PORT}/api-docs`);
    });
  } catch (error) {
    console.error("❌ Error starting the server:", error.message);
  }
})();
