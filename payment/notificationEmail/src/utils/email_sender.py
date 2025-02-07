import smtplib
from email.mime.text import MIMEText
from src.config.settings import settings

def send_email(to_email: str, message: str):
    msg = MIMEText(message)
    msg["Subject"] = "Notificación"
    msg["From"] = settings.SMTP_USER
    msg["To"] = to_email

    try:
        with smtplib.SMTP(settings.SMTP_SERVER, settings.SMTP_PORT) as server:
            server.starttls()
            server.login(settings.SMTP_USER, settings.SMTP_PASSWORD)
            server.sendmail(settings.SMTP_USER, to_email, msg.as_string())
    except Exception as e:
        print(f"Error enviando email: {e}")
