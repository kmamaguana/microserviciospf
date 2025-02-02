# Use an official Node.js runtime as a parent image
FROM node:18

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application files
COPY . .

# Expose the port the app will run on
EXPOSE 3014

# Define environment variables (optional)
ENV NODE_ENV=production
ENV PORT=3014

# Start the server
CMD ["npm", "start"]
