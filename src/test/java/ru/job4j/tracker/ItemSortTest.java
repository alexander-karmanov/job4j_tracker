package ru.job4j.tracker;

import org.junit.Test;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemSortTest {
    @Test
    public void whenAscOrderSort() {
        List<Item> items = new ArrayList<>();
            items.add(new Item("Nick"));
            items.add(new Item("Bob"));
            items.add(new Item("John"));
            items.sort(new ItemAscByName());
        List<Item> expected = new ArrayList<>();
            expected.add(new Item("Bob"));
            expected.add(new Item("John"));
            expected.add(new Item("Nick"));
        assertThat(items.get(0).getName()).isEqualTo(expected.get(0).getName());
        assertThat(items.get(1).getName()).isEqualTo(expected.get(1).getName());
        assertThat(items.get(2).getName()).isEqualTo(expected.get(2).getName());
        }

    @Test
    public void whenDescOrderSort() {
        List<Item> items = new ArrayList<>();
            items.add(new Item("Bob"));
            items.add(new Item("Nick"));
            items.add(new Item("John"));
            items.sort(new ItemDescByName());
        List<Item> expected = new ArrayList<>();
            expected.add(new Item("Nick"));
            expected.add(new Item("John"));
            expected.add(new Item("Bob"));
        assertThat(items.get(0).getName()).isEqualTo(expected.get(0).getName());
        assertThat(items.get(1).getName()).isEqualTo(expected.get(1).getName());
        assertThat(items.get(2).getName()).isEqualTo(expected.get(2).getName());
    }
}
