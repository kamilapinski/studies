package passengersQueue;

import traffic.Passenger;

import java.util.Arrays;

public class TablePassengersQueue implements PassengersQueue {

    private Passenger[] queue;
    private int size;

    public TablePassengersQueue(int capacity) {
        queue = new Passenger[capacity];
        size = 0;
    }

    @Override
    public boolean full() {
        return (size >= queue.length);
    }

    @Override
    public Passenger top() {
        assert size == 0 : "Kolejka pasażerów jest pusta";
        return queue[size - 1];
    }

    @Override
    public boolean empty() {
        return (size == 0);
    }

    @Override
    public Passenger pop() {
        assert size == 0 : "Kolejka pasażerów jest pusta";
        Passenger ans = queue[size - 1];
        queue[size - 1] = null;
        size--;
        return ans;
    }

    @Override
    public void push(Passenger x) {
        queue[size] = x;
        size++;
        Arrays.sort(queue, 0, size);
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void clear() {
        for (Passenger passenger : queue) {
            passenger = null;
        }
        size = 0;
    }
}
