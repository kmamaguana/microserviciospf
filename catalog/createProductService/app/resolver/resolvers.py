from app.config.database import get_db

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
