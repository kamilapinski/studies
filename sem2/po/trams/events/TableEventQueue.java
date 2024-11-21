package events;

import java.util.Arrays;

public class TableEventQueue implements EventQueue {

    private Event[] queue;
    private int size;

    public TableEventQueue() {
        queue = new Event[0];
        size = 0;
    }

    private int more(int x) {
        return 2 * x + 1;
    }

    @Override
    public Event top() {
        assert size == 0 : "Kolejka zdarzeń jest pusta";
        return queue[size - 1];
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public void pop() {
        assert size == 0 : "Kolejka zdarzeń jest pusta";
        queue[size - 1] = null;
        size--;
        if (more(size) < queue.length) queue = Arrays.copyOf(queue, size);
    }

    @Override
    public void push(Event x) {
        if (size >= queue.length) queue = Arrays.copyOf(queue, more(size));

        queue[size] = x;
        size++;

        Arrays.sort(queue, 0, size);
    }

    @Override
    public void clear() {
        size = 0;
        queue = Arrays.copyOf(queue, size);
    }
}
