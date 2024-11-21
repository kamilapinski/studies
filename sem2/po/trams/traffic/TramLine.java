package traffic;

import events.EventQueue;

public class TramLine extends Line{
    private final Tram[] trams;

    public TramLine(int lineNumber, int numberOfVehicles, int routeSize, Route route) {
        super(lineNumber, numberOfVehicles, routeSize, route);
        this.trams = new Tram[numberOfVehicles];
    }

    public void setTrams(int nr, int capacity) {
        Route reversedRoute = route.reverse();

        int i = 0;
        while (i < numberOfVehicles){
            trams[i] = new Tram(nr + i, this, capacity, route, super.END_TIME);
            if (i + 1 < numberOfVehicles)
                trams[i + 1] = new Tram(nr + i + 1, this, capacity, reversedRoute, super.END_TIME);

            i += 2;
        }
    }

    public int numberOfTrams() {
        return numberOfVehicles;
    }

    private Time calculateInterval() {
        int timeOfRoute = route.calculateTimeOfRoute().toMins();
        int interval = timeOfRoute / numberOfVehicles; //zakładam, że już po prostu zaokrąglone w dół

        return new Time(interval);
    }

    public void tramsGo(EventQueue queue, int day) {
        Time time = new Time(super.START_TIME);

        Time interval = calculateInterval();

        int i = 0;
        while (i < numberOfVehicles) {
            Time startTime = new Time(time);
            trams[i].go(queue, day, new Time(time));
            if (i + 1 < numberOfVehicles)
                trams[i + 1].go(queue, day, new Time(time));

            time.plus(interval);
            i += 2;
        }
    }

    public void clearTrams() {
        for (Tram tram : trams) {
            tram.clear();
        }
    }

    @Override
    public String toString() {
        return numberOfVehicles + " " + routeSize + " " + route;
    }
}
