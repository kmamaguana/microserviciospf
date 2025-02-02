const express = require('express');
const router = express.Router();
const orderController = require('../controllers/orderController');

/**
 * @swagger
 * /api/order/{id}/status:
 *   get:
 *     summary: Retrieve the status of an order by ID
 *     description: Fetch the status of an order using its unique orderId
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         description: The unique identifier for the order
 *         schema:
 *           type: string
 *     responses:
 *       200:
 *         description: Order status returned
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 status:
 *                   type: string
 *                   example: Shipped
 *       404:
 *         description: Order not found
 */
router.get('/order/:id/status', orderController.getOrderStatus);

module.exports = router;
