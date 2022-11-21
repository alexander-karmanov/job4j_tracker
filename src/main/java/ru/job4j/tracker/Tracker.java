package ru.job4j.tracker;

import java.util.ArrayList;

public class Tracker {
    private final ArrayList<Item> items = new ArrayList<>(100);
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    public Item findById(int id) {
        Item rsl = null;
        for (Item it : items) {
            if (it.getId() == id) {
                rsl = it;
                break;
            }
        }
        return rsl;
    }

    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> rsl = new ArrayList<>(size);
        int count = 0;
        for (Item it : items) {
            if (it.getName().equals(key)) {
                rsl.add(it);
            }
        }
        return rsl;
    }

    public ArrayList<Item> findAll() {
        return items;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (Item it : items) {
            if (it.getId() == id) {
                rsl = it.getId();
            }
        }
        return rsl;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id) - 1;
        boolean rsl = index != -1;
            if (rsl) {
                item.setId(id);
                items.set(index, item);
                rsl = true;
            }
        return rsl;
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            items.remove(id - 1);
        }
        return rsl;
    }
}
