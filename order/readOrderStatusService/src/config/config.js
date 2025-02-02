module.exports = {
  dbURI: `mongodb://${process.env.DB_USER}:${process.env.DB_PASSWORD}@${process.env.DB_HOST}:${process.env.DB_PORT}/${process.env.DB_NAME}?authSource=${process.env.AUTH_SOURCE || 'admin'}`,
  port: process.env.PORT || 3014
};
