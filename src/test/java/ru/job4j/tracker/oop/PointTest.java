package ru.job4j.tracker.oop;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class PointTest {
    @Test
    public void when00to20then2() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        double dist = a.distance(b);
        double expected = 2;
        Assert.assertEquals(expected, dist, 0.01);
    }

    @Test
    public void when11to01then1() {
        Point a = new Point(1, 0);
        Point b = new Point(1, 1);
        double dist = a.distance(b);
        double expected = 1;
        Assert.assertEquals(expected, dist, 0.01);
    }

    @Test
    public void when23to25then3dot16() {
        Point a = new Point(2, 2);
        Point b = new Point(3, 5);
        double dist = a.distance(b);
        double expected = 3.16;
        Assert.assertEquals(expected, dist, 0.01);
    }
}