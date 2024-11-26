package com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.model;

import java.io.Serial;
import java.io.Serializable;

/**
 * @param code e.g., "USD", "EUR"
 */
public record Currency(String code) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L; // Optional but good practice for versioning

    @Override
    public String toString() {
        return code;
    }
}
