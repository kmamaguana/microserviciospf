import os
from dotenv import load_dotenv

load_dotenv()

class Settings:
    SMTP_SERVER = os.getenv("SMTP_SERVER")
    SMTP_PORT = int(os.getenv("SMTP_PORT"))
    SMTP_USER = os.getenv("SMTP_USER")
    SMTP_PASSWORD = os.getenv("SMTP_PASSWORD")
    WEBHOOK_SECRET = os.getenv("WEBHOOK_SECRET")

settings = Settings()
