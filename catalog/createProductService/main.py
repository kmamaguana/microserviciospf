import json
import os
from dotenv import load_dotenv
from flask import Flask, request, jsonify
from flask_cors import CORS
from flask_restx import Api
from ariadne import make_executable_schema, graphql_sync, QueryType, MutationType, upload_scalar
from ariadne.wsgi import GraphQLMiddleware

from app.schema.schema import type_defs
from app.resolver.resolvers import resolve_create_product

# Cargar variables de entorno
load_dotenv()

SERVER_PORT = int(os.getenv("SERVER_PORT", 3005))

app = Flask(__name__)
CORS(app)
api = Api(app, doc="/swagger-ui")

query = QueryType()
mutation = MutationType()
mutation.set_field("createProduct", resolve_create_product)

# 🚀 Agregar Upload Scalar para permitir subida de archivos
schema = make_executable_schema(type_defs, upload_scalar, query, mutation)
app.wsgi_app = GraphQLMiddleware(app.wsgi_app, schema)


@app.route("/graphql", methods=["POST"])
def graphql_server():
    """🚀 Endpoint de GraphQL con soporte para subida de archivos"""

    if "multipart/form-data" in request.content_type:
        data = request.form.to_dict()
        files = request.files.to_dict()

        # 🔍 Depuración: Ver qué datos llegaron
        print(f"🛠 Datos recibidos en request.form: {data}")
        print(f"🛠 Archivos recibidos: {files}")

        # ✅ Parsear `operations`
        try:
            data = json.loads(data.get("operations", "{}"))  # Convertir string JSON a diccionario
        except json.JSONDecodeError:
            return jsonify({"error": "Invalid JSON format in 'operations'"}), 400

        # ✅ Parsear `map`
        try:
            file_map = json.loads(request.form.get("map", "{}"))
        except json.JSONDecodeError:
            return jsonify({"error": "Invalid JSON format in 'map'"}), 400

        # ✅ Asegurar que 'variables' existe
        data.setdefault("variables", {})

        # 🚀 Asignar archivos correctamente
        for file_key, file_path in file_map.items():
            if file_key in files:
                for path in file_path:
                    keys = path.split(".")
                    ref = data
                    for key in keys[:-1]:
                        ref = ref.setdefault(key, {})
                    ref[keys[-1]] = files[file_key]

        # 🔍 Depuración después de asignar archivos
        print(f"📦 Variables finales: {data['variables']}")

    else:
        data = request.get_json() or {}

    if not isinstance(data.get("query"), str):
        return jsonify({"error": "The query must be a string."}), 400

    success, result = graphql_sync(schema, data, context_value=request, debug=True)
    return jsonify(result), 200 if success else 400


if __name__ == "__main__":
    print(f"🚀 Servidor corriendo en: http://0.0.0.0:{SERVER_PORT}/graphql")
    app.run(host='0.0.0.0', port=SERVER_PORT)