const mongoose = require("mongoose");
require("dotenv").config();

/**
 * Connect to MongoDB.
 */
const connectDB = async () => {
  try {
    await mongoose.connect(process.env.MONGO_URI, {
      dbName: process.env.DB_NAME, // Specify the database
    });
    console.log(`✅ Connected to database: ${process.env.DB_NAME}`);
  } catch (error) {
    console.error("❌ Error connecting to MongoDB:", error.message);
    process.exit(1); // Exit if connection fails
  }
};

module.exports = connectDB;
