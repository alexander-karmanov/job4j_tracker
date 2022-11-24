package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("johnsmith@gmail.com", "John Smith");
        map.put("jackblack@gmail.com", "Jack Black");
        map.put("billgates@gmail.com", "Bill Gates");
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key + " " + value);
        }
    }
}
