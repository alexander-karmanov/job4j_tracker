package ru.job4j.search;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        var persons = phones.find("Petr");
        assertThat(persons.get(0).getSurname()).isEqualTo("Arsentev");
    }

    @Test
    public void whenNotFoundByName() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("John", "Smith", "7778899", "London")
        );
        var persons = phones.find("Bill");
        assertThat(persons.isEmpty()).isTrue();
    }
}
