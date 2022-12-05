package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.assertj.core.api.Assertions.assertThat;

public class JobTest {
    @Test
    public void whenComparatorByDescNameAndDescPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenComparatorByDescName() {
        Comparator<Job> cmpDescName = new JobDescByName();
        int rsl = cmpDescName.compare(
                new Job("Impl task", 1),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenComparatorByAscPriority() {
        Comparator<Job> cmpAscPriority = new JobAscByPriority();
        int rsl = cmpAscPriority.compare(
                new Job("Impl task", 5),
                new Job("Fix bug", 3)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenComparatorByAscNameAndAscPriority() {
        Comparator<Job> cmpAscNameAscPriority = new JobAscByName().thenComparing(new JobAscByPriority());
        int rsl = cmpAscNameAscPriority.compare(
                new Job("Turn on PC", 1),
                new Job("Fix bug", 2)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenComparatorByAscNameAndAscPriorityEqual() {
        Comparator<Job> cmpNamePriorityEqual = new JobAscByName().thenComparing(new JobAscByPriority());
        int rsl = cmpNamePriorityEqual.compare(
                new Job("Turn on PC", 1),
                new Job("Turn on PC", 1)
        );
        assertThat(rsl).isEqualTo(0);
    }

    @Test
    public void whenComparatorByAscPriorityAndAscName() {
        Comparator<Job> cmpAscPriorityAscName = new JobAscByPriority().thenComparing(new JobAscByName());
        int rsl = cmpAscPriorityAscName.compare(
                new Job("Fix bug", 0),
                new Job("Impl task", 2)
        );
        assertThat(rsl).isLessThan(0);
    }
}
