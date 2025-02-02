require('dotenv').config();
const app = require('./src/app');

const port = process.env.PORT || 5000;

app.listen(port, () => {
  console.log('✅ Server is running!');
  console.log(`🌐 API URL: http://localhost:${port}/api/purchases`);
  console.log(`📄 Swagger Docs: http://localhost:${port}/api-docs`);
});
