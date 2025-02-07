# Imagen base de Python
FROM python:3.10-slim

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo de dependencias (requirements.txt) al contenedor
COPY requirements.txt .

# Instalar las dependencias de Python
RUN pip install --no-cache-dir -r requirements.txt

# Copiar el contenido del proyecto al contenedor
COPY . .

# Exponer el puerto 5000 para acceder a la aplicación
EXPOSE 5000

# Comando para ejecutar la aplicación
CMD ["python", "src/main.py"]
