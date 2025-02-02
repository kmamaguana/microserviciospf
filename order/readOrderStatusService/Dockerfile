# Usa una imagen base oficial de Node.js
FROM node:18

# Configura el directorio de trabajo dentro del contenedor
WORKDIR /usr/src/app

# Copia el archivo `package.json` y `package-lock.json` al contenedor
COPY package*.json ./

# Instala las dependencias del proyecto
RUN npm install

# Copia el código de la aplicación al contenedor
COPY . .

# Expone el puerto en el que el servidor estará corriendo
EXPOSE 3014

# Instala `dotenv` para cargar el archivo .env
RUN npm install dotenv

# Ejecuta el servidor
CMD ["npm", "start"]
