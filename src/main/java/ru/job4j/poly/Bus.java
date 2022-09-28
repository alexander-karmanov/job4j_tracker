package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void go() {
        System.out.println("The bus #1 is going.");
    }

    @Override
    public void passengers(int people) {
        System.out.println("Number of passengers is: " + people);
    }

    @Override
    public double fillup(double fuel) {
        return fuel * 51;
    }
}
