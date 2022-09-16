package ru.job4j.oop;

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

    @Test
    public void when110to221then1dot73() {
        Point a = new Point(1, 1, 0);
        Point b = new Point(2, 2, 1);
        double dist = a.distance3d(b);
        double expected = 1.73;
        Assert.assertEquals(expected, dist, 0.01);
    }

    @Test
    public void when502to107then6dot40() {
        Point a = new Point(5, 0, 2);
        Point b = new Point(1, 0, 7);
        double dist = a.distance3d(b);
        double expected = 6.40;
        Assert.assertEquals(expected, dist, 0.01);
    }

    @Test
    public void when000to100then1() {
        Point a = new Point(0, 0, 0);
        Point b = new Point(1, 0, 0);
        double dist = a.distance3d(b);
        double expected = 1;
        Assert.assertEquals(expected, dist, 0.01);
    }
}
