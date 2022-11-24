package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PassportOfficeTest {
    @Test
    public void whenTestAddMethod() {
        Citizen citizen = new Citizen("2f44a", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        assertThat(office.get(citizen.getPassport())).isEqualTo(citizen);
    }

    @Test
    public void whenAddDuplicatePassport() {
        Citizen citizen = new Citizen("5g76a", "John Smith");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        Citizen citizen2 = new Citizen("5g76a", "John Smith");
        assertThat(office.add(citizen2)).isFalse();
    }
}
