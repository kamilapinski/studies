package traffic;

import passengersQueue.PassengersQueue;
import passengersQueue.TablePassengersQueue;
import simulation.Statistic;

public class Stop {
    private final String name;
    private final int capacity;

    private PassengersQueue passengers;

    public Stop(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.passengers = new TablePassengersQueue(this.capacity);
    }

    public boolean full() {
        return passengers.full();
    }

    public boolean empty() {
        return passengers.empty();
    }

    public void addPassenger(Passenger passenger) {
        if (full()) {
            return;
        }

        passengers.push(passenger);
    }

    public Passenger choosePassengerAndRemove() {
        if (empty()) {
            return null;
        }

        return passengers.pop();
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    public void clear() {
        passengers.clear();
    }

    public int passengersGoHome(Statistic lastWaitingTime) {
        int numberOfLastWaiting = 0;
        while (!passengers.empty()) {
            Passenger passenger = passengers.pop();
            lastWaitingTime.add(passenger.lastWaitingTime().toMins());
            numberOfLastWaiting++;
        }
        return numberOfLastWaiting;
    }

    @Override
    public String toString() {
        return name;
    }
}
