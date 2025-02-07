from sqlalchemy.orm import Session
from src.models.notification import Notification

def save_notification(db: Session, email: str, message: str):
    notification = Notification(email=email, message=message)
    db.add(notification)
    db.commit()
    db.refresh(notification)
    return notification
