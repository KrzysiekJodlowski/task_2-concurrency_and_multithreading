package com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L; // Optional but good practice for versioning
    private final String accountId;
    private final Map<Currency, BigDecimal> currencies = new HashMap<>();

    public Account(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void addCurrency(Currency currency, BigDecimal amount) {
        currencies.put(currency, amount);
    }

    public BigDecimal getCurrencyAmount(Currency currency) {
        return currencies.getOrDefault(currency, BigDecimal.ZERO);
    }

    public void setCurrencyAmount(Currency currency, BigDecimal amount) {
        currencies.put(currency, amount);
    }

    public Map<Currency, BigDecimal> getCurrencies() {
        return currencies;
    }
}
