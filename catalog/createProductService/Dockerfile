# Usa una imagen base ligera de Python
FROM python:3.9-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos necesarios para instalar dependencias
COPY requirements.txt ./

# Definir argumentos de construcción
ARG MONGO_URI
ARG DB_NAME
ARG SERVER_PORT
ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY
ARG S3_BUCKET_NAME

# Configurar variables de entorno a partir de los argumentos
ENV MONGO_URI=${MONGO_URI}
ENV DB_NAME=${DB_NAME}
ENV SERVER_PORT=${SERVER_PORT}
ENV AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID}
ENV AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY}
ENV S3_BUCKET_NAME=${S3_BUCKET_NAME}

# Instalar las dependencias del proyecto
RUN pip install --no-cache-dir -r requirements.txt

# Copiar el resto del código fuente de la aplicación
COPY . .

# Exponer el puerto que utilizará la aplicación
EXPOSE ${SERVER_PORT}

# Comando para ejecutar la aplicación
CMD ["python", "main.py"]
