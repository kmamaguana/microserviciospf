const express = require('express');
const pedidoController = require('../controllers/pedidoController');

const router = express.Router();

/**
 * @swagger
 * /api/pedidos:
 *   post:
 *     summary: Crear un nuevo pedido
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               cliente:
 *                 type: string
 *                 example: Juan Pérez
 *               producto:
 *                 type: string
 *                 example: Laptop
 *               cantidad:
 *                 type: integer
 *                 example: 1
 *               estado:
 *                 type: string
 *                 example: pendiente
 *     responses:
 *       201:
 *         description: Pedido creado exitosamente
 *       500:
 *         description: Error interno del servidor
 */
router.post('/', pedidoController.create);

/**
 * @swagger
 * /api/pedidos:
 *   get:
 *     summary: Obtener todos los pedidos
 *     responses:
 *       200:
 *         description: Lista de pedidos
 *       500:
 *         description: Error interno del servidor
 */
router.get('/', pedidoController.getAll);

/**
 * @swagger
 * /api/pedidos/{id}:
 *   get:
 *     summary: Obtener un pedido por su ID
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description: ID del pedido
 *     responses:
 *       200:
 *         description: Pedido encontrado
 *       404:
 *         description: Pedido no encontrado
 *       500:
 *         description: Error interno del servidor
 */
router.get('/:id', pedidoController.getById);

/**
 * @swagger
 * /api/pedidos/{id}/estado:
 *   put:
 *     summary: Actualizar el estado de un pedido
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description: ID del pedido
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               estado:
 *                 type: string
 *                 example: enviado
 *     responses:
 *       200:
 *         description: Pedido actualizado exitosamente
 *       400:
 *         description: Estado inválido
 *       404:
 *         description: Pedido no encontrado
 *       500:
 *         description: Error interno del servidor
 */
router.put('/:id/estado', pedidoController.updateEstado);

/**
 * @swagger
 * /api/pedidos/{id}:
 *   delete:
 *     summary: Eliminar un pedido
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description: ID del pedido
 *     responses:
 *       200:
 *         description: Pedido eliminado
 *       404:
 *         description: Pedido no encontrado
 *       500:
 *         description: Error interno del servidor
 */
router.delete('/:id', pedidoController.delete);

module.exports = router;
