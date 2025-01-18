import redis
import json
from app.models.carrito import Carrito
from app.utils.redis_client import obtener_conexion_redis

class CarritoService:
    def __init__(self):
        self.redis = obtener_conexion_redis()

    def obtener_carrito(self, carrito_id):
        carrito_json = self.redis.get(carrito_id)
        if carrito_json:
            return json.loads(carrito_json)
        return None

    def crear_carrito(self, data):
        carrito_id = data['carrito_id']
        carrito = Carrito(carrito_id, data['productos'])
        self.redis.set(carrito_id, json.dumps(carrito.__dict__))
        return carrito_id

    def actualizar_carrito(self, carrito_id, data):
        carrito_json = self.redis.get(carrito_id)
        if carrito_json:
            carrito = Carrito(**json.loads(carrito_json))
            if 'productos' in data:
                carrito.productos = data['productos']
            self.redis.set(carrito_id, json.dumps(carrito.__dict__))
            return True
        return False

    def eliminar_carrito(self, carrito_id):
        if self.redis.exists(carrito_id):
            self.redis.delete(carrito_id)
            return True
        return False
