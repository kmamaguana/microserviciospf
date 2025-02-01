import os
from dotenv import load_dotenv
from flask import Flask, request, jsonify
from flask_cors import CORS
from swagger_ui import api_doc
from ariadne import make_executable_schema, graphql_sync, QueryType, MutationType
from app.schema.schema import type_defs
from app.resolver.resolvers import resolve_create_product

# Cargar variables de entorno
load_dotenv()
SERVER_PORT = int(os.getenv("SERVER_PORT", 3005))

# Configuración de la aplicación Flask
app = Flask(__name__)
CORS(app)

# Configuración de Swagger
api_doc(app, config_path="swagger.json", url_prefix="/swagger-ui")

# Configuración de GraphQL
query = QueryType()
mutation = MutationType()

# Asignar resolvers a las mutaciones
mutation.set_field("createProduct", resolve_create_product)

# Crear el esquema ejecutable para GraphQL
schema = make_executable_schema(type_defs, query, mutation)

# Endpoint de GraphQL
@app.route("/graphql", methods=["POST"])
def graphql_server():
    data = request.get_json()
    success, result = graphql_sync(schema, data, context_value=request, debug=True)
    status_code = 200 if success else 400
    return jsonify(result), status_code

if __name__ == "__main__":
    print(f"GraphQL Server running at: http://localhost:{SERVER_PORT}/graphql")
    print(f"Swagger UI available at: http://localhost:{SERVER_PORT}/swagger-ui")
    app.run(host='0.0.0.0', port=SERVER_PORT)
