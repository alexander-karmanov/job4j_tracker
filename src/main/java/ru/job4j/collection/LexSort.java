package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        String[] strArrLeft = left.split("\\.");
        String[] strArrRight = right.split("\\.");
            int numLeft = Integer.parseInt(strArrLeft[0]);
            int numRight = Integer.parseInt(strArrRight[0]);
            return Integer.compare(numLeft, numRight);
    }
}
