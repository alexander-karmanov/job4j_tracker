package ru.job4j.bank;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Класс описывает работу банковского сервиса с использованием
 * моделей данных "пользователь" (User) и "банковский счет" (Account)
 * и возможностью добавлять, удалять, находить пользователей и их счета,
 * а также переводить деньги с одного счета на другой.
 */
public class BankService {
    /**
     * Хранение пользователей и счетов осуществляется в коллекции типа Map,
     * в качестве ключа используется тип User, в качестве значения -
     * коллекция List с типом Account.
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход пользователя и добавляет его в базу users,
     * если его там нет, также добаляется список его счетов.
     * @param user - пользователь, который добавляется в базу.
     */
    public void addUser(User user) {
            users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод принимает на вход паспорт, находит по нему пользователя
     * и удаляет его из базы.
     * @param passport - паспорт, по которому происходит поиск пользователя
     * @return возвращает true, если пользователь был найден в базе и удален.
     */
    public boolean deleteUser(String passport) {
        return users.remove(new User(passport, "")) != null;
    }

    /**
     * Метод добавляет банковский счет в базу users.
     * Метод принимает на вход паспорт и счет, затем происходит поиск
     * пользователя по паспорту. Если пользователь найден, и такого
     * счета у него нет, то счет добавляется.
     * @param passport - паспорт, по которому происходит поиск пользователя
     * @param account - банковский счет, добавляемый в базу
     */
    public void addAccount(String passport, Account account) {
        User foundUser = findByPassport(passport);
        if (foundUser != null) {
            List<Account> userAccounts = users.get(foundUser);
            if (!userAccounts.contains(account)) {
                userAccounts.add(account);
            }
        }
    }

    /**
     * Метод ищет по паспорту пользователя в базе.
     * @param passport - паспорт, по которому происходит поиск
     * пользователя в базе users.
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(s -> s.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
        }

    /**
     * Метод осуществляет поиск счета по реквизитам в базе users.
     * Если по паспорту найден пользователь, в списке его счетов
     * осуществляется поиск нужного счета по реквизитам.
     * @param passport - паспорт, по которому происходит поиск пользователя.
     * @param requisite - реквизит, по которому происходит поиск счета в
     * списке счетов пользователя.
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            return users.get(user)
                    .stream()
                    .filter(s -> s.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод осуществляет перевод денежных средств с одного счета на другой.
     * По паспорту и реквизитам находится счет, с которого спишутся деньги,
     * и счет, на который они будут перечислены. Если счета найдены в базе,
     * и баланс источника больше или равен заданному количеству, происходит
     * перевод денег.
     * @param srcPassport - паспорт пользователя, со счета которого спишутся деньги
     * @param destPassport - паспорт пользователя, на счет которого будут перечислены деньги
     * @param srcRequisite - реквизит счета, с которого спишутся деньги
     * @param destRequisite - реквизит счета, на который будут перечислены деньги
     * @param amount - денежные средства для перевода
     * @return возвращает true, если перевод осуществлен успешно
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null
            && destAccount != null
            && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
