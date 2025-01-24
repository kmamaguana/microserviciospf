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

# Load environment variables
load_dotenv()
SERVER_PORT = int(os.getenv("SERVER_PORT", 5000))

# Flask app configuration
app = Flask(__name__)
CORS(app)  # Enable CORS for cross-origin requests

# Create QueryType and MutationType objects for handling GraphQL queries and mutations
query = QueryType()
mutation = MutationType()

# Assign resolvers to queries and mutations
query.set_field("getProduct", resolve_get_product)
query.set_field("listProducts", resolve_list_products)
mutation.set_field("createProduct", resolve_create_product)
mutation.set_field("updateProduct", resolve_update_product)
mutation.set_field("deleteProduct", resolve_delete_product)

# Create the executable schema using the type definitions and resolvers
schema = make_executable_schema(type_defs, query, mutation)

# Define the GraphQL endpoint
@app.route("/graphql", methods=["POST"])
def graphql_server():
    # Parse the incoming request
    data = request.get_json()
    # Execute the GraphQL query/mutation
    success, result = graphql_sync(schema, data, context_value=request, debug=True)
    # Determine the status code based on success or failure
    status_code = 200 if success else 400
    return jsonify(result), status_code

if __name__ == "__main__":
    # Print the server URL for easier access
    print(f"Server running at: http://localhost:{SERVER_PORT}/graphql")
    # Run the Flask application
    app.run(port=SERVER_PORT)
