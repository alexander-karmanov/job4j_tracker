package ru.job4j.tracker.mockito;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteTest {
    @Test
    public void whenItemWasDeletedSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Deleted item"));
        DeleteAction delete = new DeleteAction(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        delete.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Delete item ===" + ln
                        + "Заявка удалена успешно." + ln
        );
    }
}
