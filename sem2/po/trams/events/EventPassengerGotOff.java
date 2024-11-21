package events;

import simulation.Statistic;
import traffic.Passenger;
import traffic.Stop;
import traffic.Time;
import traffic.Tram;

public class EventPassengerGotOff extends Event {

    private final Passenger passenger;
    private final Stop stop;
    private final Tram tram;

    public EventPassengerGotOff(int day, Time time, Stop stop, Passenger passenger, Tram tram) {
        super(day, time);
        this.stop = stop;
        this.passenger = passenger;
        this.tram = tram;
    }

    public int priority() {
        return 2;
    }

    @Override
    public String toString() {
        return super.day() + ", " + super.time() +": Pasażer " + passenger + " wysiadł na przystanku " + stop;
    }

    @Override
    public boolean canBeDone() {
        return true;
    }

    @Override
    public void doPossibleMovements(EventQueue queue, Statistic passengersTravels, Statistic sumOfWaitingTime) {
    }
}

