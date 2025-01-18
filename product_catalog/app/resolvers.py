from app.database import get_db


# Resolver to get a product by its ID
def resolve_get_product(obj, info, id):
    db = get_db()
    product = db.products.find_one({"_id": id})  # Assuming 'id' is the '_id' field
    if product:
        return {
            "id": str(product["_id"]),
            "name": product["name"],
            "price": product["price"],
            "description": product.get("description"),
            "stock": product.get("stock"),
            "color": product.get("color"),
            "size": product.get("size"),
            "material": product.get("material"),
            "brand": product.get("brand"),
            "gender": product.get("gender")
        }
    return None


# Resolver to get the list of all products
def resolve_list_products(obj, info):
    db = get_db()
    products = db.products.find()
    return [
        {
            "id": str(product["_id"]),
            "name": product["name"],
            "price": product["price"],
            "description": product.get("description"),
            "stock": product.get("stock"),
            "color": product.get("color"),
            "size": product.get("size"),
            "material": product.get("material"),
            "brand": product.get("brand"),
            "gender": product.get("gender")
        }
        for product in products
    ]


# Resolver to create a new product
def resolve_create_product(obj, info, name, price, description=None, stock=0, color=None, size=None, material=None,
                           brand=None, gender=None):
    db = get_db()
    new_product = {
        "name": name,
        "price": price,
        "description": description,
        "stock": stock,
        "color": color,
        "size": size,
        "material": material,
        "brand": brand,
        "gender": gender
    }
    result = db.products.insert_one(new_product)
    product = db.products.find_one({"_id": result.inserted_id})
    return {
        "id": str(product["_id"]),
        "name": product["name"],
        "price": product["price"],
        "description": product.get("description"),
        "stock": product.get("stock"),
        "color": product.get("color"),
        "size": product.get("size"),
        "material": product.get("material"),
        "brand": product.get("brand"),
        "gender": product.get("gender")
    }


# Resolver to update an existing product
def resolve_update_product(obj, info, id, name=None, price=None, description=None, stock=None, color=None, size=None,
                           material=None, brand=None, gender=None):
    db = get_db()
    updated_product = {}
    if name is not None:
        updated_product["name"] = name
    if price is not None:
        updated_product["price"] = price
    if description is not None:
        updated_product["description"] = description
    if stock is not None:
        updated_product["stock"] = stock
    if color is not None:
        updated_product["color"] = color
    if size is not None:
        updated_product["size"] = size
    if material is not None:
        updated_product["material"] = material
    if brand is not None:
        updated_product["brand"] = brand
    if gender is not None:
        updated_product["gender"] = gender

    result = db.products.update_one({"_id": id}, {"$set": updated_product})
    if result.modified_count == 0:
        return None  # If no document was modified
    product = db.products.find_one({"_id": id})
    return {
        "id": str(product["_id"]),
        "name": product["name"],
        "price": product["price"],
        "description": product.get("description"),
        "stock": product.get("stock"),
        "color": product.get("color"),
        "size": product.get("size"),
        "material": product.get("material"),
        "brand": product.get("brand"),
        "gender": product.get("gender")
    }


# Resolver to delete a product
def resolve_delete_product(obj, info, id):
    db = get_db()
    result = db.products.delete_one({"_id": id})
    return result.deleted_count > 0  # Returns True if a product was deleted
