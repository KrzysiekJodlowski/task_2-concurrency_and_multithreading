package com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.dao;

import com.epam.solution.currencyExchangeApp.src.main.java.com.epam.exchange.model.Account;

import java.io.*;

public class AccountDAO {

    private static final String ACCOUNT_DIRECTORY = "/";

    public Account loadAccount(String accountId) throws IOException, ClassNotFoundException {
        File file = new File(ACCOUNT_DIRECTORY + accountId + ".dat");
        if (!file.exists()) {
            throw new FileNotFoundException("Account not found: " + accountId);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (Account) in.readObject();
        }
    }

    public void saveAccount(Account account) throws IOException {
        File file = new File(ACCOUNT_DIRECTORY + account.getAccountId() + ".dat");
        if (!file.exists()) {
            file.createNewFile();
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(account);
        }
    }
}
