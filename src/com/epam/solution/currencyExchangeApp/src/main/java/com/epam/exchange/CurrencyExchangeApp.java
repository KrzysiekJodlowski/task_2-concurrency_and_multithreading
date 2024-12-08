package com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange;

import com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.dao.AccountDAO;
import com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.model.Account;
import com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.model.Currency;
import com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.service.AccountService;
import com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.util.LoggerUtil;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CurrencyExchangeApp {
    private static final AccountDAO accountDAO = new AccountDAO();
    private static final AccountService accountService = new AccountService(accountDAO);

    public static void main(String[] args) throws Exception {
        try (ExecutorService executorService = Executors.newFixedThreadPool(5)) {

            // Sample setup
            Currency usd = new Currency("USD");
            Currency eur = new Currency("EUR");
            Account account = new Account("12345");
            account.addCurrency(usd, new BigDecimal("1000"));
            account.addCurrency(eur, new BigDecimal("200"));

            accountDAO.saveAccount(account);

            // Simulate currency exchange
            Runnable exchangeTask = () -> {
                try {
                    Account loadedAccount = accountDAO.loadAccount("12345");
                    accountService.exchangeCurrency(loadedAccount, usd, eur, new BigDecimal("100"));
                    LoggerUtil.logInfo("Successfully exchanged currency for account " + loadedAccount.getAccountId());
                } catch (Exception e) {
                    LoggerUtil.logError("Error during currency exchange", e);
                }
            };

            // Submit multiple exchange tasks
            for (int i = 0; i < 5; i++) {
                executorService.submit(exchangeTask);
            }

            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        }
    }
}
