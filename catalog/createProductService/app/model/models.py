from pydantic import BaseModel
from typing import Optional

class Product(BaseModel):
    id: str
    name: str
    description: Optional[str] = None
    price: float
    stock: int
    color: Optional[str] = None
    size: Optional[str] = None
    material: Optional[str] = None
    brand: Optional[str] = None
    gender: Optional[str] = None
    image_url: Optional[str] = None