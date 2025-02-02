const express = require('express');
const OrderController = require('../controllers/orderController');

const router = express.Router();

/**
 * @swagger
 * /api/orders:
 *   post:
 *     summary: Create a new order
 *     tags: [Orders]
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/Order'
 *     responses:
 *       201:
 *         $ref: '#/components/responses/OrderCreated'
 */
router.post('/', OrderController.create);

module.exports = router;
