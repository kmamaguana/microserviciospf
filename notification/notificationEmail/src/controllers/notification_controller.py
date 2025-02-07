from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from src.config.database import SessionLocal
from src.services.notification_service import process_notification

router = APIRouter()


def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()


@router.post("/webhook/notify")
def webhook_notify(payload: dict, db: Session = Depends(get_db)):
    email = payload.get("email")
    message = payload.get("message")

    if not email or not message:
        raise HTTPException(status_code=400, detail="Email y mensaje son requeridos")

    notification = process_notification(db, email, message)
    return {"message": "Notificación enviada", "data": notification}
