from pydantic import BaseModel
from typing import Optional

class Product(BaseModel):
    id: str                             # Unique identifier of the product
    name: str                           # Name of the product
    description: Optional[str] = None   # Detailed description of the product (optional)
    price: float                        # Price of the product
    stock: int                          # Quantity available in stock
    color: Optional[str] = None         # Color of the product (optional)
    size: Optional[str] = None          # Size of the product (e.g., S, M, L, XL) (optional)
    material: Optional[str] = None      # Material of the product (e.g., cotton, polyester) (optional)
    brand: Optional[str] = None         # Brand of the product (optional)
    gender: Optional[str] = None        # Gender it is intended for (male, female, unisex) (optional)

    class Config:
        min_anystr_length = 1           # Ensures that string fields are not empty
        anystr_strip_whitespace = True  # Removes unnecessary whitespace in string fields
