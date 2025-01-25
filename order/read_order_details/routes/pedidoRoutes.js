const express = require('express');
const pedidoController = require('../controllers/pedidoController');

const router = express.Router();

/**
 * @swagger
 * tags:
 *   name: Pedidos
 *   description: Operaciones relacionadas con los pedidos
 */

/**
 * @swagger
 * path:
 *  /api/pedidos:
 *    get:
 *      summary: Obtener todos los pedidos
 *      tags: [Pedidos]
 *      responses:
 *        200:
 *          description: Lista de pedidos
 *          content:
 *            application/json:
 *              schema:
 *                type: array
 *                items:
 *                  type: object
 *                  properties:
 *                    id:
 *                      type: integer
 *                      example: 1
 *                    cliente:
 *                      type: string
 *                      example: "Juan Pérez"
 *                    producto:
 *                      type: string
 *                      example: "Camiseta"
 *                    cantidad:
 *                      type: integer
 *                      example: 2
 *                    estado:
 *                      type: string
 *                      example: "Pendiente"
 *        500:
 *          description: Error del servidor
 */

/**
 * @swagger
 * path:
 *  /api/pedidos/{id}:
 *    get:
 *      summary: Obtener un pedido por ID
 *      tags: [Pedidos]
 *      parameters:
 *        - name: id
 *          in: path
 *          description: ID del pedido a obtener
 *          required: true
 *          schema:
 *            type: integer
 *      responses:
 *        200:
 *          description: Detalles del pedido
 *          content:
 *            application/json:
 *              schema:
 *                type: object
 *                properties:
 *                  id:
 *                    type: integer
 *                    example: 1
 *                  cliente:
 *                    type: string
 *                    example: "Juan Pérez"
 *                  producto:
 *                    type: string
 *                    example: "Camiseta"
 *                  cantidad:
 *                    type: integer
 *                    example: 2
 *                  estado:
 *                    type: string
 *                    example: "Pendiente"
 *        404:
 *          description: Pedido no encontrado
 *        500:
 *          description: Error del servidor
 */

router.get('/', pedidoController.getAll);
router.get('/:id', pedidoController.getById);

module.exports = router;
