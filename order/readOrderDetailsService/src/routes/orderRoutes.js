const express = require('express');
const orderController = require('../controllers/orderController');

const router = express.Router();

/**
 * @swagger
 * components:
 *   schemas:
 *     Order:
 *       type: object
 *       properties:
 *         id:
 *           type: integer
 *           description: ID del pedido
 *         customer:
 *           type: string
 *           description: Nombre del cliente
 *         product:
 *           type: string
 *           description: Producto solicitado
 *         quantity:
 *           type: integer
 *           description: Cantidad del producto
 *         status:
 *           type: string
 *           description: Estado del pedido
 */

/**
 * @swagger
 * /api/orders:
 *   get:
 *     summary: Obtiene todos los pedidos
 *     tags: [Pedidos]
 *     responses:
 *       200:
 *         description: Lista de pedidos
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Order'
 *       500:
 *         description: Error del servidor
 */
router.get('/', orderController.getAll);

module.exports = router;