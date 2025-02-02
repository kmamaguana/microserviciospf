const express = require('express');
const PurchaseHistoryController = require('../controllers/purchaseHistoryController');

const router = express.Router();

/**
 * @swagger
 * /api/purchases:
 *   get:
 *     summary: Get purchase history
 *     description: Retrieve a list of all purchases.
 *     responses:
 *       200:
 *         description: A list of purchases.
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 type: object
 *                 properties:
 *                   id:
 *                     type: integer
 *                     example: 1
 *                   user_id:
 *                     type: integer
 *                     example: 101
 *                   product:
 *                     type: string
 *                     example: "T-shirt"
 *                   amount:
 *                     type: number
 *                     format: float
 *                     example: 19.99
 *                   purchase_date:
 *                     type: string
 *                     format: date-time
 *                     example: "2024-01-15T14:30:00Z"
 *       500:
 *         description: Internal server error
 */
router.get('/', PurchaseHistoryController.getPurchaseHistory);

module.exports = router;
