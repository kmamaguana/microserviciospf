from pydantic import BaseModel

class AddressCreate(BaseModel):
    street: str
    city: str
    state: str
    postal_code: str
    country: str

class AddressResponse(AddressCreate):
    id: int

    class Config:
        rom_attributes = True