package passengersQueue;

import traffic.Passenger;

public interface PassengersQueue {
    Passenger top();

    boolean empty();

    Passenger pop();

    void push(Passenger x);

    int size();

    boolean full();

    void clear();
}
