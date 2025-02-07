import os
from dotenv import load_dotenv

load_dotenv()  # Cargar las variables del archivo .env

class Config:
    # Configuración de la base de datos
    DB_HOST = os.getenv('DB_HOST', 'localhost')
    DB_PORT = os.getenv('DB_PORT', 5435)
    DB_NAME = os.getenv('DB_NAME', 'notificationOrder_db')
    DB_USER = os.getenv('DB_USER', 'postgres')
    DB_PASSWORD = os.getenv('DB_PASSWORD', 'example')

    # Configuración del servidor SMTP
    SMTP_SERVER = os.getenv('SMTP_SERVER', 'smtp-isaac.alwaysdata.net')
    SMTP_PORT = os.getenv('SMTP_PORT', 587)
    SMTP_USER = os.getenv('SMTP_USER', 'isaac@alwaysdata.net')
    SMTP_PASSWORD = os.getenv('SMTP_PASSWORD', 'hZZUeZ3HGMXQpGt')

    # Configuración del Webhook
    WEBHOOK_SECRET = os.getenv('WEBHOOK_SECRET', 'f8c1d3b57d4a8f')
