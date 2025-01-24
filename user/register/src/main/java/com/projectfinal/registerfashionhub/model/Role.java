package com.projectfinal.registerfashionhub.model;

/**
 * Enum representing the different roles that users can have within the system.
 * This enum helps to define the access and permissions for users.
 */
public enum Role {

    // Enum constants with associated descriptions
    VENDOR("Usuario vendedor en la plataforma"),  // Role of Vendor
    CUSTOMER("Usuario que compra en la plataforma");              // Role of Customer

    // Description of the role
    private final String description;

    /**
     * Constructor for the Role enum.
     *
     * @param description the description of the role
     */
    Role(String description) {
        this.description = description;
    }

    /**
     * Retrieves the description of the role.
     *
     * @return the description of the role
     */
    public String getDescription() {
        return description;
    }
}
