const express = require('express');
const router = express.Router();
const orderController = require('../controllers/orderController');

/**
 * @swagger
 * /api/order/{id}/status:
 *   get:
 *     summary: Obtener el estado de un pedido por su ID
 *     description: Devuelve el estado de un pedido dado su ID único
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         description: El ID del pedido
 *         schema:
 *           type: string
 *     responses:
 *       200:
 *         description: El estado del pedido
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 status:
 *                   type: string
 *                   example: Shipped
 *       404:
 *         description: Pedido no encontrado
 *       500:
 *         description: Error en el servidor
 */
router.get('/order/:id/status', orderController.getOrderStatus);

module.exports = router;
