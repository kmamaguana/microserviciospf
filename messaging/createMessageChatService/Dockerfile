# Use Node.js official image as the base for building the application
FROM node:16 AS build

# Set working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to install dependencies
COPY package*.json ./

# Install dependencies
RUN npm install --production

# Copy the rest of the application files
COPY . .

# Stage 2: Create the final image
FROM node:16

# Set working directory inside the container
WORKDIR /app

# Copy only the necessary files from the build stage
COPY --from=build /app /app

# Expose the port your service will run on
EXPOSE 3018

# Set environment variables
ENV DB_USER=${DB_USER}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV DB_HOST=${DB_HOST}
ENV DB_PORT=${DB_PORT}
ENV DB_NAME=${DB_NAME}
ENV AUTH_SOURCE=${AUTH_SOURCE}
ENV WS_URL=${WS_URL}

# Command to run your application
CMD ["npm", "start"]
