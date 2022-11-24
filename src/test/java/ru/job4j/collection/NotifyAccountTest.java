package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class NotifyAccountTest {
    @Test
    public void whenAddTwoDifferentAccounts() {
        List<Account> accounts = Arrays.asList(
                new Account("123", "Petr Arsentev", "eDer3432f"),
                new Account("142", "Petr Arsentev", "eDer3432f")
        );
        HashSet<Account> expect = new HashSet<>();
        expect.add(new Account("123", "Petr Arsentev", "eDer3432f"));
        expect.add(new Account("142", "Petr Arsentev", "eDer3432f"));
        assertThat(NotifyAccount.sent(accounts)).containsAll(expect);
    }

    @Test
    public void whenAddTwoDuplicateAccounts() {
        List<Account> accounts = Arrays.asList(
                new Account("123", "Petr Arsentev", "eDer3432f"),
                new Account("123", "Ivan Ivanov", "000000001")
        );
        HashSet<Account> expect = new HashSet<>();
        expect.add(new Account("123", "Petr Arsentev", "eDer3432f"));
        assertThat(NotifyAccount.sent(accounts)).containsAll(expect);
    }

    @Test
    public void whenAddSomeDuplicateAccounts() {
        List<Account> accounts = Arrays.asList(
                new Account("123", "Petr Arsentev", "eDer3432f"),
                new Account("142", "Petr Arsentev", "eDer3432f"),
                new Account("123", "Ivan Ivanov", "000000001"),
                new Account("142", "Petr Petrov", "000000002"),
                new Account("123", "Mark Markov", "000000003")
        );
        HashSet<Account> expect = new HashSet<>();
        expect.add(new Account("123", "Petr Arsentev", "eDer3432f"));
        expect.add(new Account("142", "Petr Arsentev", "eDer3432f"));
        assertThat(NotifyAccount.sent(accounts)).containsAll(expect);
    }

    @Test
    public void whenAddSomeMoreDuplicateAccounts() {
        List<Account> accounts = Arrays.asList(
                new Account("789", "John Smith", "wFhj8401f"),
                new Account("555", "Bill Smith", "eGsv9444f"),
                new Account("789", "Tony Robbins", "252540007"),
                new Account("555", "Brad Pitt", "789540003")
        );
        HashSet<Account> expect = new HashSet<>();
        expect.add(new Account("789", "John Smith", "wFhj8401f"));
        expect.add(new Account("555", "Bill Smith", "eGsv9444f"));
        assertThat(NotifyAccount.sent(accounts)).containsAll(expect);
    }
}
