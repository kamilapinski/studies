package events;

import simulation.Statistic;
import traffic.Passenger;
import traffic.Stop;
import traffic.Time;

public class EventPassengerResigned extends Event{

    private final Passenger passenger;
    private final Stop stop;

    public EventPassengerResigned(int day, Time time, Stop stop, Passenger passenger) {
        super(day, time);
        this.stop = stop;
        this.passenger = passenger;
    }

    public int priority() {
        return 0;
    }

    @Override
    public String toString() {
        return super.day() + ", " + super.time() +": Pasażer " + passenger + " przyszedł na przystanek " + stop + " i zrezygnował z podróży";
    }

    @Override
    public void doPossibleMovements(EventQueue queue, Statistic passengersTravels, Statistic sumOfWaitingTime) {
    }

    @Override
    public boolean canBeDone() {
        return true;
    }

}
