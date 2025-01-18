import os
from dotenv import load_dotenv
from flask import Flask, request, jsonify
from flask_cors import CORS
from ariadne import make_executable_schema, graphql_sync, QueryType, MutationType
from ariadne.wsgi import GraphQL
from app.schema import type_defs
from app.resolvers import (
    resolve_get_product,
    resolve_list_products,
    resolve_create_product,
    resolve_update_product,
    resolve_delete_product,
)

# Cargar variables de entorno
load_dotenv()
SERVER_PORT = int(os.getenv("SERVER_PORT", 5000))

# Configuración de la aplicación Flask
app = Flask(__name__)
CORS(app)  # Habilitar CORS

# Crear objetos QueryType y MutationType
query = QueryType()
mutation = MutationType()

# Asignar resolvers a las consultas y mutaciones
query.set_field("getProduct", resolve_get_product)
query.set_field("listProducts", resolve_list_products)
mutation.set_field("createProduct", resolve_create_product)
mutation.set_field("updateProduct", resolve_update_product)
mutation.set_field("deleteProduct", resolve_delete_product)

# Crear el esquema ejecutable
schema = make_executable_schema(type_defs, query, mutation)

@app.route("/graphql", methods=["POST"])
def graphql_server():
    data = request.get_json()
    success, result = graphql_sync(schema, data, context_value=request, debug=True)
    status_code = 200 if success else 400
    return jsonify(result), status_code

if __name__ == "__main__":
    print(f"Servidor ejecutándose en: http://localhost:{SERVER_PORT}/graphql")
    app.run(port=SERVER_PORT)
