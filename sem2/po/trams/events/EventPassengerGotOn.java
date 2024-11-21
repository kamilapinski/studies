package events;

import simulation.Statistic;
import traffic.Passenger;
import traffic.Stop;
import traffic.Time;
import traffic.Tram;

public class EventPassengerGotOn extends Event {
    private final Passenger passenger;
    private final Tram tram;

    public EventPassengerGotOn(int day, Time time, Passenger passenger, Tram tram) {
        super(day, time);
        this.passenger = passenger;
        this.tram = tram;
    }

    public int priority() {
        return 3;
    }

    @Override
    public String toString() {
        return super.day() + ", " + super.time() +": " + passenger.messagePassengerGotOn(tram);
    }

    @Override
    public boolean canBeDone() {
        return true;
    }

    @Override
    public void doPossibleMovements(EventQueue queue, Statistic passengersTravels, Statistic sumOfWaitingTime) {
    }
}

//takeAction: pasażer wsiada (dodanie do tramwaju i usunięcie w przystanku)