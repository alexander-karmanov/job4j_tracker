package ru.job4j.tracker.mockito;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByNameTest {
    @Test
    public void whenItemWasFoundByName() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Find item"));
        String name = "Find item";
        FindByNameAction findByName = new FindByNameAction(output);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(name);

        findByName.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + item + ln
        );
    }

    @Test
    public void whenItemWasNotFoundByName() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        String name = "Find item name";
        FindByNameAction findByName = new FindByNameAction(output);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(name);

        findByName.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + "Заявки с именем: " + name + " не найдены." + ln
        );
    }
}
