from src.models.order_model import Order
import psycopg2
from src.config.config import Config
import logging

def get_db_connection():
    try:
        connection = psycopg2.connect(
            host=Config.DB_HOST,
            port=Config.DB_PORT,
            dbname=Config.DB_NAME,
            user=Config.DB_USER,
            password=Config.DB_PASSWORD
        )
        return connection
    except Exception as e:
        logging.error(f"Database connection failed: {e}")
        return None

def process_order_notification(data):
    try:
        order = Order(
            order_id=data['order_id'],
            customer_name=data['customer_name'],
            total_amount=data['total_amount'],
            status=data['status']
        )

        # Guardar el pedido en la base de datos
        db_connection = get_db_connection()
        if db_connection:
            order.save(db_connection)
            db_connection.commit()
            db_connection.close()
            return True
        else:
            return False
    except Exception as e:
        logging.error(f"Error processing order notification: {e}")
        return False
