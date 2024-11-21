package traffic;

import events.EventPassengerCame;
import events.EventQueue;

public class Passenger implements Comparable<Passenger> {

    private final Time PASSENGER_START_HOUR = new Time(6, 0);
    private final Time PASSENGER_END_HOUR = new Time(12, 0);
    private final Time PASSENGER_GO_HOME_TIME = new Time(24, 0);

    private final int number;

    private Stop startStop;
    private Time stopTime;
    private Stop endStop;

    public Passenger(int number) {
        this.number = number;
    }

    public void goToStop(int dayNumber, EventQueue queue) {
        if (startStop.full()) {
            return;
        }

        EventPassengerCame event = new EventPassengerCame(dayNumber, stopTime, startStop, this);
        queue.push(event);
    }

    public void chooseStartStop(Stop[] stops) {
        Losowanie losowanie = new Losowanie();
        startStop = losowanie.stopDraw(stops);
    }

    public void getOn(Stop[] stops) {
        Losowanie losowanie = new Losowanie();
        endStop = losowanie.stopDraw(stops);
    }

    public void chooseTime(Time down, Time up) {
        Losowanie losowanie = new Losowanie();
        stopTime = losowanie.timeDraw(down, up);
    }

    public void chooseTime() {
        chooseTime(PASSENGER_START_HOUR, PASSENGER_END_HOUR);
    }

    public int comingTimeMins() {
        return stopTime.toMins();
    }

    public void setStopTime(Time newComingTime) {
        stopTime = new Time(newComingTime);
    }

    public int compareTo(Passenger other) {
        if (this.comingTimeMins() < other.comingTimeMins()) return 1;
        else if (this.comingTimeMins() == other.comingTimeMins()) return 0;
        else return -1;
    }

    public boolean endStopCompare(Stop stop) {
        return endStop == stop;
    }

    public Time lastWaitingTime() {
        return new Time(PASSENGER_GO_HOME_TIME.toMins() - stopTime.toMins());
    }

    public String messagePassengerGotOn(Tram tram) {
        return "Pasażer " + this + " wsiadł do tramwaju linii " + tram + " z zamiarem dojechania do przystanku " + this.endStop;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
