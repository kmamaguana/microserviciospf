const express = require('express');
const pedidoController = require('../controllers/pedidoController');

const router = express.Router();

/**
 * @swagger
 * components:
 *   schemas:
 *     Pedido:
 *       type: object
 *       required:
 *         - cliente
 *         - producto
 *         - cantidad
 *         - estado
 *       properties:
 *         id:
 *           type: integer
 *           description: ID autogenerado del pedido
 *         cliente:
 *           type: string
 *           description: Nombre del cliente
 *         producto:
 *           type: string
 *           description: Nombre del producto
 *         cantidad:
 *           type: integer
 *           description: Cantidad solicitada
 *         estado:
 *           type: string
 *           description: Estado del pedido
 *       example:
 *         id: 1
 *         cliente: "Juan Pérez"
 *         producto: "Camisa"
 *         cantidad: 2
 *         estado: "pendiente"
 * 
 *   responses:
 *     PedidoCreado:
 *       description: Respuesta exitosa con los detalles del pedido creado
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/Pedido'
 *           example:
 *             id: 1
 *             cliente: "Juan Pérez"
 *             producto: "Camisa"
 *             cantidad: 2
 *             estado: "pendiente"
 *     Error400:
 *       description: Error por datos faltantes o inválidos
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               error:
 *                 type: string
 *                 example: "Todos los campos son obligatorios"
 *     Error500:
 *       description: Error interno del servidor
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               error:
 *                 type: string
 *                 example: "Error al crear el pedido"
 */

/**
 * @swagger
 * /api/pedidos:
 *   post:
 *     summary: Crea un nuevo pedido
 *     tags: [Pedidos]
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/Pedido'
 *     responses:
 *       201:
 *         $ref: '#/components/responses/PedidoCreado'
 *       400:
 *         $ref: '#/components/responses/Error400'
 *       500:
 *         $ref: '#/components/responses/Error500'
 */
router.post('/', pedidoController.create);

module.exports = router;
