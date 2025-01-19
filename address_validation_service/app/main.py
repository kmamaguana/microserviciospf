from fastapi import FastAPI
from .services import create_address, get_address
from .schemas import AddressCreate, AddressResponse

app = FastAPI()

@app.post("/addresses/", response_model=AddressResponse)
async def create_address_endpoint(address: AddressCreate):
    return await create_address(address)

@app.get("/addresses/{address_id}", response_model=AddressResponse)
async def get_address_endpoint(address_id: int):
    return await get_address(address_id)
@app.get("/")
async def root():
    return {"message": "Bienvenido al servicio de validación de direcciones"}
