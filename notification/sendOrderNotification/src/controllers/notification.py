from flask import Blueprint, request, jsonify
from src.services.order_service import process_order_notification
import logging

notification_bp = Blueprint('notification', __name__)


@notification_bp.route('/order', methods=['POST'])
def order_notification():
    try:
        data = request.get_json()
        if not data:
            return jsonify({'error': 'No data provided'}), 400

        success = process_order_notification(data)

        if success:
            return jsonify({'message': 'Order processed successfully'}), 200
        else:
            return jsonify({'error': 'Failed to process order'}), 500
    except Exception as e:
        logging.error(f"Error processing notification: {e}")
        return jsonify({'error': str(e)}), 500
