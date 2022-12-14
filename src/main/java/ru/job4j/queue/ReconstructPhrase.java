package ru.job4j.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class ReconstructPhrase {
    private final Queue<Character> descendingElements;

    private final Queue<Character> evenElements;

    public ReconstructPhrase(Queue<Character> descendingElements, Queue<Character> evenElements) {
        this.descendingElements = descendingElements;
        this.evenElements = evenElements;
    }

    private String getEvenElements() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = evenElements.size();
            for (int i = 0; i < size; i++) {
                if (i % 2 == 0) {
                    stringBuilder.append(evenElements.poll());
                } else {
                    evenElements.poll();
                }
            }
        return stringBuilder.toString();
    }

    private String getDescendingElements() {
        StringBuilder stringBuilder = new StringBuilder();
        Deque<Character> dequeDescEl = new LinkedList<>(descendingElements);
        int size = descendingElements.size();
            for (int i = 0; i < size; i++) {
                stringBuilder.append(dequeDescEl.pollLast());
            }
        return stringBuilder.toString();
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}
