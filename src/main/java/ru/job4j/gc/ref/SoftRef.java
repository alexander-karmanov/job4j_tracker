package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;

public class SoftRef {
    public static void main(String[] args) {
        /* безопасная работа с SoftReference */
        Object ob = new Object();
        SoftReference softRef = new SoftReference<>(ob);
        Object ref = softRef.get();
        if (ref != null) {
            System.out.println("Object exists");
        } else {
            System.out.println("Object does not exist");
        }
    }
}
