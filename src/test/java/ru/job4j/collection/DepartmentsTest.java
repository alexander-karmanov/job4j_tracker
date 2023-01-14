package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DepartmentsTest {
    @Test
    public void whenMissed() {
        List<String> input = List.of("k1/sk1", "k2/sk1");
        List<String> expect = List.of("k1", "k1/sk1", "k2", "k2/sk1");
        List<String> result = Departments.fillGaps(input);
        assertThat(result).containsSequence(expect);
    }

    @Test
    public void whenNonChange() {
        List<String> input = List.of("k1", "k1/sk1");
        List<String> expect = List.of("k1", "k1/sk1");
        List<String> result = Departments.fillGaps(input);
        assertThat(result).containsSequence(expect);
    }

    @Test
    public void whenMissedSomeDepartmentsCode() {
        List<String> input = List.of(
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K2/SK1/SSK1"
        );
        List<String> expect = List.of(
                "K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K2",
                "K2/SK1",
                "K2/SK1/SSK1"
        );
        List<String> result = Departments.fillGaps(input);
        assertThat(result).containsSequence(expect);
    }

    @Test
    public void whenSortAscWithoutMissedDepartments() {
        List<String> input = List.of(
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K2",
                "K2/SK1",
                "K1/SK2",
                "K1",
                "K2/SK1/SSK2",
                "K2/SK1/SSK1"
        );
        List<String> expect = List.of(
                "K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2",
                "K2",
                "K2/SK1",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2"
        );
        List<String> input1 = new ArrayList<>();
        Collections.copy(input, input1);
        List<String> expect1 = new ArrayList<>();
        Collections.copy(expect, expect1);
        Departments.sortAsc(input1);
        assertThat(input1).containsSequence(expect1);
    }

    @Test
    public void whenSortAscWithMissedDepartments() {
        List<String> input = List.of(
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K2/SK1",
                "K1/SK2",
                "K2/SK1/SSK2",
                "K2/SK1/SSK1"
        );
        List<String> expect = List.of(
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2",
                "K2/SK1",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2"
        );
        List<String> input1 = new ArrayList<>();
        Collections.copy(input, input1);
        List<String> expect1 = new ArrayList<>();
        Collections.copy(expect, expect1);
        Departments.sortAsc(input1);
        assertThat(input1).containsSequence(expect1);
    }

    @Test
    public void whenSortDescWithoutMissedDepartments() {
        List<String> input = List.of(
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K2",
                "K2/SK1",
                "K1/SK2",
                "K1",
                "K2/SK1/SSK2",
                "K2/SK1/SSK1"
        );
        List<String> expect = List.of(
                "K2",
                "K2/SK1",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2",
                "K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2"
        );
        List<String> input1 = new ArrayList<>();
        Collections.copy(input, input1);
        List<String> expect1 = new ArrayList<>();
        Collections.copy(expect, expect1);
        Departments.sortDesc(input1);
        assertThat(input1).containsSequence(expect1);
    }

    @Test
    public void whenSortDescWithMissedDepartments() {
        List<String> input = List.of(
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K2/SK1",
                "K1/SK2",
                "K2/SK1/SSK2",
                "K2/SK1/SSK1"
        );
        List<String> expect = List.of(
                "K2/SK1",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2"
        );
        List<String> input1 = new ArrayList<>();
        Collections.copy(input, input1);
        List<String> expect1 = new ArrayList<>();
        Collections.copy(expect, expect1);
        Departments.sortDesc(input1);
        assertThat(input1).containsSequence(expect1);
    }
}
