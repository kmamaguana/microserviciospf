from fastapi import FastAPI
from src.controllers.notification_controller import router as notification_router

app = FastAPI()

app.include_router(notification_router, prefix="/api")
