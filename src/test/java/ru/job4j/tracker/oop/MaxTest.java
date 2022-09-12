package ru.job4j.tracker.oop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MaxTest {
    @Test
    public void whenMax1And2And3Then3() {
        int first = 1;
        int second = 2;
        int third = 3;
        int result = Max.max(first, second, third);
        int expected = 3;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenMax0And2And4And7Then7() {
        int first = 0;
        int second = 2;
        int third = 4;
        int fourth = 7;
        int result = Max.max(first, second, third, fourth);
        int expected = 7;
        assertThat(result).isEqualTo(expected);
    }
}
