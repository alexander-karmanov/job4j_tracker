package ru.job4j.tracker;

import org.junit.Test;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertArrayEquals;

public class ItemSortTest {
    @Test
    public void whenAscOrderSort() {
        List<Item> items = new ArrayList<>(
                        Arrays.asList(
                        new Item("Nick"),
                        new Item("Bob"),
                        new Item("John")
                )
        );
            items.sort(new ItemAscByName());

        List<Item> expected = new ArrayList<>(
                Arrays.asList(
                        new Item("Bob"),
                        new Item("John"),
                        new Item("Nick")
                )
        );
        assertArrayEquals(items.toArray(), expected.toArray());
        }

    @Test
    public void whenDescOrderSort() {
        List<Item> items = new ArrayList<>(
                Arrays.asList(
                        new Item("Bob"),
                        new Item("Nick"),
                        new Item("John")
                )
        );
            items.sort(new ItemDescByName());
        List<Item> expected = new ArrayList<>(
                Arrays.asList(
                        new Item("Nick"),
                        new Item("John"),
                        new Item("Bob")
                )
        );
        assertArrayEquals(items.toArray(), expected.toArray());
    }
}
