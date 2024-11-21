package events;

public interface EventQueue {

    Event top();

    boolean empty();

    void pop();

    void push(Event x);

    void clear();
}
