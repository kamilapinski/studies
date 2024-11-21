package events;

import simulation.Statistic;
import traffic.Passenger;
import traffic.Stop;
import traffic.Time;

public class EventPassengerCame extends Event {

    private final Passenger passenger;
    private final Stop stop;

    public EventPassengerCame(int day, Time time, Stop stop, Passenger passenger) {
        super(day, time);
        this.stop = stop;
        this.passenger = passenger;
    }

    public int priority() {
        return 0;
    }

    @Override
    public String toString() {
        return super.day() + ", " + super.time() +": Pasażer " + passenger + " przyszedł na przystanek " + stop;
    }

    @Override
    public void doPossibleMovements(EventQueue queue, Statistic passengersTravels, Statistic sumOfWaitingTime) {
        if (canBeDone()) {
            stop.addPassenger(passenger);
        }
        else {
            Event event = new EventPassengerResigned(day(), new Time(time()), stop, passenger);
            queue.push(event);
        }
    }

    @Override
    public boolean canBeDone() {
        return !stop.full();
    }
}
