from pydantic import BaseModel
from sqlalchemy import Column, Integer, String
from sqlalchemy.orm import relationship
from .database import Base

class Address(Base):
    __tablename__ = 'addresses'

    id = Column(Integer, primary_key=True, index=True)
    street = Column(String, index=True)
    city = Column(String)
    state = Column(String)
    postal_code = Column(String)
    country = Column(String)
