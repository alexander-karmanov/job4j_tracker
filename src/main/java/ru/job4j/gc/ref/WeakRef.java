package ru.job4j.gc.ref;

import java.lang.ref.WeakReference;

public class WeakRef {
    public static void main(String[] args) {
        /* безопасная работа с WeakReference */
        Object ob = new Object();
        WeakReference weakRef = new WeakReference<>(ob);
        Object ref = weakRef.get();
        if (ref != null) {
            System.out.println("Object exists");
        } else {
            System.out.println("Object does not exist");
        }
    }
}
