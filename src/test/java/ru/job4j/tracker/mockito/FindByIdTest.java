package ru.job4j.tracker.mockito;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByIdTest {
    @Test
    public void whenItemWasFoundById() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Find item"));
        FindByIdAction findById = new FindByIdAction(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        findById.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                        + item + ln
        );
    }

    @Test
    public void whenItemWasNotFoundById() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Find item"));
        FindByIdAction findById = new FindByIdAction(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(0);

        findById.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                        + "Заявка с введенным id: " + 0 + " не найдена." + ln
        );
    }
}
