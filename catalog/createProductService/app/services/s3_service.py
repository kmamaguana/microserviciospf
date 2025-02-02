import os
import boto3
import mimetypes
import logging
from uuid import uuid4
from dotenv import load_dotenv
from typing import Optional, Dict, Any
from datetime import datetime

# Configuración del logging
logger = logging.getLogger('S3Service')


def create_formatter():
    """Crea un formateador personalizado para los logs"""
    return logging.Formatter('%(asctime)s - 🔍 %(levelname)s - %(message)s')


# Configurar handler para consola con emojis
console_handler = logging.StreamHandler()
console_handler.setFormatter(create_formatter())
logger.addHandler(console_handler)


class S3Service:
    def __init__(self):
        logger.info("🚀 Inicializando S3Service...")

        # Cargar variables de entorno
        load_dotenv()

        self.aws_access_key_id = os.getenv("AWS_ACCESS_KEY_ID")
        self.aws_secret_access_key = os.getenv("AWS_SECRET_ACCESS_KEY")
        self.bucket_name = os.getenv("S3_BUCKET_NAME")
        self.region = os.getenv("AWS_REGION", "us-east-1")

        # Validar configuración
        if not all([self.aws_access_key_id, self.aws_secret_access_key, self.bucket_name]):
            logger.error("❌ Faltan variables de entorno necesarias")
            raise ValueError("Configuración incompleta de AWS")

        logger.info(f"📦 Bucket configurado: {self.bucket_name}")
        logger.info(f"🌎 Región AWS: {self.region}")

        try:
            # Inicializar cliente S3
            self.s3_client = boto3.client(
                's3',
                aws_access_key_id=self.aws_access_key_id,
                aws_secret_access_key=self.aws_secret_access_key,
                region_name=self.region
            )
            logger.info("✅ Cliente S3 inicializado correctamente")
        except Exception as e:
            logger.error(f"❌ Error al inicializar cliente S3: {str(e)}")
            raise

    def generate_file_name(self, original_filename: str, product_name: str) -> str:
        """Genera un nombre de archivo único basado en el nombre del producto"""
        logger.debug(f"🏷️ Generando nombre para archivo: {original_filename}")

        file_extension = original_filename.split('.')[-1].lower()  # Obtener la extensión del archivo
        unique_id = str(uuid4())[:8]  # Generar un identificador único
        timestamp = datetime.now().strftime('%Y%m%d_%H%M%S')  # Obtener la fecha y hora actual

        # Generamos el nombre de archivo con el nombre del producto
        file_name = f"products/{timestamp}_{product_name}_{unique_id}.{file_extension}"

        logger.debug(f"📝 Nombre generado: {file_name}")
        return file_name

    def get_file_metadata(self, file_obj: Any) -> Dict[str, str]:
        """Genera los metadatos apropiados para el archivo"""
        logger.debug(f"📋 Generando metadatos para: {file_obj.filename}")

        content_type = mimetypes.guess_type(file_obj.filename)[0] or "image/jpeg"
        metadata = {
            'ContentType': content_type,
            'ContentDisposition': 'inline',  # Mostrar en el navegador
            'CacheControl': 'public, max-age=31536000',
            'ACL': 'public-read',
            'Metadata': {
                'purpose': 'product-catalog',
                'original-name': file_obj.filename,
                'upload-date': datetime.now().isoformat()
            }
        }

        logger.debug(f"📄 Metadatos generados: {metadata}")
        return metadata

    def upload_product_image(self, image_file: Any, product_id: str) -> Optional[str]:
        """Sube una imagen de producto a S3 y retorna una URL permanente"""
        logger.info(f"⬆️ Iniciando subida de imagen para producto: {product_id}")

        if not image_file:
            logger.error("❌ No se proporcionó archivo de imagen")
            return None

        try:
            # Verificar el archivo
            logger.debug(f"📁 Archivo recibido: {image_file.filename}")
            logger.debug(f"📊 Tipo MIME: {mimetypes.guess_type(image_file.filename)[0]}")

            # Generar nombre de archivo
            file_key = self.generate_file_name(image_file.filename, product_id)
            logger.info(f"🔑 Key generada: {file_key}")

            # Obtener metadatos
            extra_args = self.get_file_metadata(image_file)
            logger.debug("📎 Metadatos preparados para subida")

            # Subir archivo
            logger.info("📤 Iniciando subida a S3...")
            self.s3_client.upload_fileobj(
                image_file,
                self.bucket_name,
                file_key,
                ExtraArgs=extra_args
            )
            logger.info("✅ Archivo subido exitosamente")

            # Generar URL
            if self.region == 'us-east-1':
                url = f"https://{self.bucket_name}.s3.amazonaws.com/{file_key}"
            else:
                url = f"https://{self.bucket_name}.s3.{self.region}.amazonaws.com/{file_key}"

            logger.info(f"🔗 URL generada: {url}")
            return url

        except Exception as e:
            logger.error(f"❌ Error durante la subida: {str(e)}", exc_info=True)
            return None