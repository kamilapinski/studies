package events;

import simulation.Statistic;
import traffic.Passenger;
import traffic.Stop;
import traffic.Time;
import traffic.Tram;

public class EventTramArrived extends Event{

    private final Tram tram;
    private final Stop stop;
    private final int idOfStopInRoute;
    private final int directionOfTram;

    public EventTramArrived(int day, Time time, Stop stop, Tram tram, int idOfStopInRoute, int directionOfTram) {
        super(day, time);
        this.stop = stop;
        this.tram = tram;
        this.idOfStopInRoute = idOfStopInRoute;
        this.directionOfTram = directionOfTram;
    }

    public int priority() {
        return 0;
    }

    @Override
    public String toString() {
        return super.day() + ", " + super.time() +": Tramwaj linii " + tram +" przyjechał na przystanek " + stop;
    }

    @Override
    public boolean canBeDone() {
        return true;
    }

    @Override
    public void doPossibleMovements(EventQueue queue, Statistic passengersTravels, Statistic sumOfWaitingTime) {
        tram.letPassengersGetOff(queue, super.day(), super.time(), stop);
        tram.letPassengersGetOn(queue, super.day(), super.time(), stop, idOfStopInRoute, directionOfTram, passengersTravels, sumOfWaitingTime);
    }
}