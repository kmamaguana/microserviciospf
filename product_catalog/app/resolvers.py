from app.database import get_db

# Resolver para obtener un producto por su ID
def resolve_get_product(obj, info, id):
    db = get_db()
    product = db.products.find_one({"_id": id})  # Asumiendo que `id` es el campo _id
    if product:
        return {
            "id": str(product["_id"]),
            "name": product["name"],
            "price": product["price"]
        }
    return None

# Resolver para obtener la lista de todos los productos
def resolve_list_products(obj, info):
    db = get_db()
    products = db.products.find()
    return [
        {
            "id": str(product["_id"]),
            "name": product["name"],
            "price": product["price"]
        }
        for product in products
    ]

# Resolver para crear un nuevo producto
def resolve_create_product(obj, info, name, price):
    db = get_db()
    new_product = {
        "name": name,
        "price": price
    }
    result = db.products.insert_one(new_product)
    product = db.products.find_one({"_id": result.inserted_id})
    return {
        "id": str(product["_id"]),
        "name": product["name"],
        "price": product["price"]
    }

# Resolver para actualizar un producto existente
def resolve_update_product(obj, info, id, name, price):
    db = get_db()
    updated_product = {
        "name": name,
        "price": price
    }
    result = db.products.update_one({"_id": id}, {"$set": updated_product})
    if result.modified_count == 0:
        return None  # Si no se modificó ningún documento
    product = db.products.find_one({"_id": id})
    return {
        "id": str(product["_id"]),
        "name": product["name"],
        "price": product["price"]
    }

# Resolver para eliminar un producto
def resolve_delete_product(obj, info, id):
    db = get_db()
    result = db.products.delete_one({"_id": id})
    return result.deleted_count > 0  # Retorna True si se eliminó un producto
