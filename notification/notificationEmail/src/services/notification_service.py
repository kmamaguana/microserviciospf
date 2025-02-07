from sqlalchemy.orm import Session
from src.repositories.notification_repository import save_notification
from src.utils.email_sender import send_email

def process_notification(db: Session, email: str, message: str):
    send_email(email, message)
    return save_notification(db, email, message)
