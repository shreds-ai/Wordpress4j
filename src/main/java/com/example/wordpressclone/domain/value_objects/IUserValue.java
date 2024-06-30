package com.example.wordpressclone.domain.value_objects;

import java.lang.FunctionalInterface;

/**
 * Interface to encapsulate operations related to user values, ensuring adherence to hexagonal architecture principles.
 * This interface acts as a port in the hexagonal architecture, allowing the domain layer to interact with different adapters for data persistence and other operations.
 */
@FunctionalInterface
public interface IUserValue {

    /**
     * Retrieves the value associated with the user.
     * This method serves as an adapter interaction point for fetching user-specific values.
     * @return the value of the user
     */
    String getValue();

    /**
     * Sets or updates the value associated with the user.
     * This method is used to update the user's value in the data store through an adapter.
     * @param value the new value to set
     */
    void setValue(String value);

    /**
     * Validates the given value against predefined business rules before it is set.
     * This validation ensures data integrity before persisting through an adapter.
     * @param value the value to validate
     * @return true if the value is valid, false otherwise
     */
    boolean validateValue(String value);
}