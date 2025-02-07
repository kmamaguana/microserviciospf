import psycopg2

class Order:
    def __init__(self, order_id, customer_name, total_amount, status):
        self.order_id = order_id
        self.customer_name = customer_name
        self.total_amount = total_amount
        self.status = status

    def save(self, db_connection):
        try:
            # Crear un cursor desde la conexión
            cursor = db_connection.cursor()

            # Consulta de inserción
            query = """
            INSERT INTO orders (order_id, customer_name, total_amount, status) 
            VALUES (%s, %s, %s, %s)
            """

            # Ejecutar la consulta usando el cursor
            cursor.execute(query, (self.order_id, self.customer_name, self.total_amount, self.status))

            # Confirmar la transacción
            db_connection.commit()

            # Cerrar el cursor
            cursor.close()

        except Exception as e:
            print(f"Error saving order: {e}")
            # Puedes manejar mejor el error si es necesario

