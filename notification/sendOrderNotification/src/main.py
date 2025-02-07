import sys
import os
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
from flask import Flask
from src.controllers.notification import notification_bp
from src.utils.logger import setup_logger

app = Flask(__name__)
app.register_blueprint(notification_bp, url_prefix='/api')

# Configurar el logger
logger = setup_logger()

if __name__ == '__main__':
    app.run(debug=True)
