package traffic;

import events.*;
import simulation.Statistic;

public class Tram extends Vehicle {
    private final Time endTime;
    private final int capacity;
    private int numberOfPassengers;
    private Passenger[] passengers;
    private Route route;

    public Tram(int tramNumber, Line line, int capacity, Route route, Time endTime) {
        super(tramNumber, line);
        this.capacity = capacity;
        this.numberOfPassengers = 0;
        this.passengers = new Passenger[capacity];
        this.route = route;
        this.endTime = endTime;
    }

    public boolean full() {
        return (numberOfPassengers >= capacity);
    }

    public boolean empty() {
        return (numberOfPassengers == 0);
    }

    private void removePassenger(int idOfPassenger) {
        if (idOfPassenger >= numberOfPassengers || idOfPassenger < 0 ||empty()) return;
        passengers[idOfPassenger] = passengers[numberOfPassengers - 1]; //jeśli wysiadanie losowe to git
        passengers[numberOfPassengers - 1] = null;
        numberOfPassengers--;
    }

    private void tryToRemovePassenger(EventQueue queue, int day, Time time, int idOfPassenger, Stop stop) {
        if (stop.full() || idOfPassenger >= numberOfPassengers || idOfPassenger < 0 || empty()) {
            return;
        }

        Event event = new EventPassengerGotOff(day, time, stop, passengers[idOfPassenger], this);
        queue.push(event);

        stop.addPassenger(passengers[idOfPassenger]);
        passengers[idOfPassenger].setStopTime(time);

        removePassenger(idOfPassenger);
    }

    public void letPassengersGetOff(EventQueue queue, int day, Time time, Stop stop) {
        for (int i = numberOfPassengers - 1; i >= 0; i--) {
            if (passengers[i].endStopCompare(stop)) {
                tryToRemovePassenger(queue, day, time, i, stop);
            }
        }
    }

    private void addPassenger(Passenger passenger) {
        if (full()) return;
        passengers[numberOfPassengers] = passenger;
        numberOfPassengers++;
    }

    private void tryToAddPassenger(EventQueue queue, int day, Time time, Passenger passenger, Stop[] stopsToChoose) {
        if (full() || stopsToChoose.length == 0) {
            return;
        }

        passenger.getOn(stopsToChoose);

        Event event = new EventPassengerGotOn(day, new Time(time), passenger, this);
        queue.push(event);

        addPassenger(passenger);
    }

    private Stop[] stopsToChoose(int i, int direction) {
        int last;
        if (direction == 1) last = route.length() - 1;
        else if (direction == -1) last = 0;
        else {
            System.out.println("Error: stopsToChoose direction has wrong value");
            return null;
        }
        return route.partOfStops(i + direction, last);
    }

    public void letPassengersGetOn(EventQueue queue, int day, Time time, Stop stop, int idOfStopInRoute, int direction, Statistic passengersTravels, Statistic sumOfWaitingTime) {
        Stop[] stops = stopsToChoose(idOfStopInRoute, direction);
        if (stops == null) {
            //oznacza to, że jesteśmy na ostatnim przystanku, więc nie wpuszczamy pasażerów, bo nigdzie nie dojadą
            return;
        }

        while (!full() && !stop.empty()) {
            Passenger passenger = stop.choosePassengerAndRemove();
            tryToAddPassenger(queue, day, time, passenger, stops);
            passengersTravels.add(1);
            sumOfWaitingTime.add(time.toMins() - passenger.comingTimeMins());
        }
    }

    private void goThereOrBack(EventQueue queue, int day, Time time, int direction) {
        int idOfFirstStop = 0;

        int timeParameter = 0;
        if (direction == -1) {
            idOfFirstStop = route.length() - 1;
            timeParameter = -1;
        }

        int i = idOfFirstStop;

        while (i >= 0 && i < route.length()) {
            Event event = new EventTramArrived(day, new Time(time), route.stop(i), this, i, direction);
            queue.push(event);

            int idOfTime = i + timeParameter;
            if (idOfTime == -1) idOfTime = route.length() - 1;
            time.plus(route.timeOfStopMins(idOfTime));

            i += direction;
        }
    }

    private void goOneRide(EventQueue queue, int day, Time time) {
        goThereOrBack(queue, day, time, 1);
        goThereOrBack(queue, day, time, -1);
    }

    public void go(EventQueue queue, int day, Time time) {
        while (time.compareTo(endTime) <= 0) {
            goOneRide(queue, day, time);
        }
    }

    public void clear() {
        numberOfPassengers = 0;
    }

}
