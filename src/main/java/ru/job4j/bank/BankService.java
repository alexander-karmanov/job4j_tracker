package ru.job4j.bank;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
            users.putIfAbsent(user, new ArrayList<>());
    }

    public boolean deleteUser(String passport) {
        boolean result = false;
        for (User user: users.keySet()) {
            if (user.getPassport().equals(passport)) {
                users.remove(passport);
                result = true;
                break;
            }
        }
        return result;
    }

    public void addAccount(String passport, Account account) {
        User foundUser = findByPassport(passport);
        if (foundUser != null) {
            List<Account> userAccounts = users.get(foundUser);
            if (!userAccounts.contains(account)) {
                userAccounts.add(account);
            }
        }
    }

    public User findByPassport(String passport) {
        User resultUser = null;
        for (User user: users.keySet()) {
            if (user.getPassport().equals(passport)) {
                resultUser = user;
                break;
            }
        }
        return resultUser;
    }

    public Account findByRequisite(String passport, String requisite) {
        Account rsl = null;
        User user = findByPassport(passport);
        if (user != null) {
            for (Account account : users.get(user)) {
                if (account.getRequisite().equals(requisite)) {
                    rsl = account;
                    break;
                }
            }
        }
        return rsl;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        User srcUser = findByPassport(srcPassport);
        User destUser = findByPassport(destPassport);
        int srcAccount = users.get(srcUser).indexOf(new Account(srcRequisite, -1));
        int destAccount = users.get(srcUser).indexOf(new Account(destRequisite, -1));

            if (srcUser != null
                & destUser != null
                & srcAccount != -1
                & destAccount != -1) {
                double srcMoney = users.get(srcUser).get(srcAccount).getBalance();
                if (srcMoney >= amount) {
                    double destMoney = users.get(destUser).get(destAccount).getBalance();
                    users.get(srcUser).get(srcAccount).setBalance(srcMoney - amount);
                    users.get(destUser).get(destAccount).setBalance(destMoney + amount);
                    rsl = true;
                }
            }

        return rsl;
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
