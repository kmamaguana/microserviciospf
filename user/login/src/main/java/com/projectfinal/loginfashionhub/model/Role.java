package com.projectfinal.loginfashionhub.model;

public enum Role {

    VENDOR("Usuario que se encarga de vender productos en la plataforma"),  // Rol de Vendedor
    CUSTOMER("Usuario que realiza compras en la plataforma");              // Rol de Cliente

    private final String description; // Descripción del rol

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
