from flask import Flask, request, jsonify
from app.services.carrito_service import CarritoService

app = Flask(__name__)
carrito_service = CarritoService()

@app.route('/carrito/<carrito_id>', methods=['GET'])
def obtener_carrito(carrito_id):
    carrito = carrito_service.obtener_carrito(carrito_id)
    if carrito:
        return jsonify(carrito), 200
    return jsonify({'error': 'Carrito no encontrado'}), 404

@app.route('/carrito', methods=['POST'])
def crear_carrito():
    data = request.json
    carrito_id = carrito_service.crear_carrito(data)
    return jsonify({'carrito_id': carrito_id}), 201

@app.route('/carrito/<carrito_id>', methods=['PUT'])
def actualizar_carrito(carrito_id):
    data = request.json
    if carrito_service.actualizar_carrito(carrito_id, data):
        return jsonify({'message': 'Carrito actualizado'}), 200
    return jsonify({'error': 'Carrito no encontrado'}), 404

@app.route('/carrito/<carrito_id>', methods=['DELETE'])
def eliminar_carrito(carrito_id):
    if carrito_service.eliminar_carrito(carrito_id):
        return jsonify({'message': 'Carrito eliminado'}), 200
    return jsonify({'error': 'Carrito no encontrado'}), 404

if __name__ == '__main__':
    app.run(debug=True)
