package com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class ExchangeRate implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L; // Optional but good practice for versioning
    private final Currency fromCurrency;
    private final Currency toCurrency;
    private final BigDecimal exchangeRate;

    public ExchangeRate(Currency fromCurrency, Currency toCurrency, BigDecimal rate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.exchangeRate = rate;
    }

    public BigDecimal getRate() {
        return exchangeRate;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }
}
