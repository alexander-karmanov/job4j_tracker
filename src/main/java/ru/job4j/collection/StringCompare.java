package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int stLength = Math.min(left.length(), right.length());
        int rsl = 0;
        for (int i = 0; i < stLength; i++) {
            if (rsl != 0) {
                 break;
            }
            rsl = Character.compare(left.charAt(i), right.charAt(i));
        }
            return rsl == 0 ? Integer.compare(left.length(), right.length()) : rsl;
    }
}
