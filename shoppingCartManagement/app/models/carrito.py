class Carrito:
    def __init__(self, carrito_id, productos):
        self.carrito_id = carrito_id
        self.productos = productos

    def agregar_producto(self, producto):
        self.productos.append(producto)

    def eliminar_producto(self, producto_id):
        self.productos = [p for p in self.productos if p['id'] != producto_id]

    def actualizar_producto(self, producto_id, nuevos_datos):
        for producto in self.productos:
            if producto['id'] == producto_id:
                producto.update(nuevos_datos)
                return True
        return False
