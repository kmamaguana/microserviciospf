const express = require('express');
const pedidoController = require('../controllers/pedidoController');

const router = express.Router();

router.post('/', pedidoController.create);
router.get('/', pedidoController.getAll);
router.get('/:id', pedidoController.getById);
router.put('/:id', pedidoController.update);
router.delete('/:id', pedidoController.delete);

module.exports = router;
