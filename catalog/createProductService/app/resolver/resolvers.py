from uuid import uuid4

from ariadne import convert_kwargs_to_snake_case
from app.services.s3_service import S3Service
from app.config.database import get_db
from app.model.models import Product

s3_service = S3Service()


@convert_kwargs_to_snake_case
def resolve_create_product(obj, info, name, price, image_file=None, description=None, stock=0, color=None, size=None,
                           material=None,
                           brand=None, gender=None):
    """🚀 Resolver para crear un producto con imagen"""
    db = get_db()
    image_url = s3_service.upload_product_image(image_file, str(uuid4())) if image_file else None

    new_product = {
        "name": name,
        "price": price,
        "description": description,
        "stock": stock,
        "color": color,
        "size": size,
        "material": material,
        "brand": brand,
        "gender": gender,
        "image_url": image_url
    }
    result = db.products.insert_one(new_product)
    product = db.products.find_one({"_id": result.inserted_id})
    return Product(
        id=str(product["_id"]),
        name=product["name"],
        price=product["price"],
        description=product.get("description"),
        stock=product.get("stock"),
        color=product.get("color"),
        size=product.get("size"),
        material=product.get("material"),
        brand=product.get("brand"),
        gender=product.get("gender"),
        image_url=product.get("image_url")
    )