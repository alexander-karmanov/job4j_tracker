package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class Tracker {
    private final List<Item> items = new ArrayList<>(100);
    private int ids = 1;

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

    public List<Item> findByName(String key) {
        ArrayList<Item> rsl = new ArrayList<>();
        int count = 0;
        for (Item it : items) {
            if (it.getName().equals(key)) {
                rsl.add(it);
            }
        }
        return rsl;
    }

    public List<Item> findAll() {
        return List.copyOf(items);
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
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
            items.remove(index);
        }
        return rsl;
    }
}
