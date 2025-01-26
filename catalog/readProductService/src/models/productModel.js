const mongoose = require("mongoose");

/**
 * Schema definition for the Product model.
 */
const productSchema = new mongoose.Schema({
  name: { type: String, required: true },
  description: { type: String },
  price: { type: Number, required: true },
  stock: { type: Number, required: true },
  color: { type: String },
  size: { type: String },
  material: { type: String },
  brand: { type: String },
  gender: { type: String },
});

module.exports = mongoose.model("Product", productSchema);
