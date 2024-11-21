package events;

import simulation.Statistic;
import traffic.Time;

public abstract class Event implements Comparable<Event> {
    private final int day;
    private final Time time;

    protected Event(int day, Time time) {
        this.day = day;
        this.time = new Time(time);
    }

    public int day() {
        return day;
    }

    public Time time() {
        return time;
    }

    public abstract int priority();

    @Override
    public int compareTo(Event other) {
        if (day() < other.day()) return 1;
        else if (day() == other.day()) {
            if (time().compareTo(other.time()) == 0) {
                return Integer.compare(other.priority(), this.priority());
            }
            return -time().compareTo(other.time());
        }
        else return -1;
    }

    public abstract boolean canBeDone();

    public abstract void doPossibleMovements(EventQueue queue, Statistic passengersTravels, Statistic sumOfWaitingTime);

    @Override
    public abstract String toString();
}
