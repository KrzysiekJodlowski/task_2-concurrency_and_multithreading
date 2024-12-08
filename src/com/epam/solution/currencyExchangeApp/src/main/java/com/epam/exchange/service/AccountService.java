package com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.service;

import com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.dao.AccountDAO;
import com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.exception.InsufficientFundsException;
import com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.model.Account;
import com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.model.Currency;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

public class AccountService {

    private final AccountDAO accountDAO;
    private final ReentrantLock lock = new ReentrantLock();

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void exchangeCurrency(Account account, Currency fromCurrency, Currency toCurrency, BigDecimal amount) throws InsufficientFundsException {
        lock.lock();
        try {
            BigDecimal balance = account.getCurrencyAmount(fromCurrency);
            if (balance.compareTo(amount) < 0) {
                throw new InsufficientFundsException("Insufficient funds for currency exchange.");
            }

            BigDecimal newBalance = balance.subtract(amount);
            account.setCurrencyAmount(fromCurrency, newBalance);
            // Assuming exchange rate is 1:1 for simplicity. Can be more complex based on real data.
            account.addCurrency(toCurrency, amount);
            // Persist the updated account
            accountDAO.saveAccount(account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
