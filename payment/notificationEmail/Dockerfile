# Usa una imagen base de Python
FROM python:3.9-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo de requerimientos y instala las dependencias
COPY requirements.txt /app/
RUN pip install --no-cache-dir -r requirements.txt

# Copia el código del proyecto al contenedor
COPY . /app/

# Establece la variable de entorno para evitar problemas con buffering de logs
ENV PYTHONUNBUFFERED=1

# Expone el puerto que usará la aplicación
EXPOSE 8000

# Ejecuta el servidor usando Uvicorn
CMD ["uvicorn", "src.main:app", "--host", "0.0.0.0", "--port", "8000"]
