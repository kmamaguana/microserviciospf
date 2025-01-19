from sqlalchemy.orm import Session
from .models import Address
from .schemas import AddressCreate, AddressResponse
from .database import SessionLocal

def get_address(address_id: int, db: Session = SessionLocal()) -> AddressResponse:
    address = db.query(Address).filter(Address.id == address_id).first()
    if address is None:
        raise ValueError(f"Address with id {address_id} not found")
    return AddressResponse.from_orm(address)

async def create_address(address: AddressCreate, db: Session = SessionLocal()) -> AddressResponse:
    db_address = Address(**address.dict())
    db.add(db_address)
    db.commit()
    db.refresh(db_address)
    return AddressResponse.from_orm(db_address)
