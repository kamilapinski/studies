package simulation;

import events.EventQueue;
import traffic.Stop;
import traffic.Time;
import traffic.TramLine;

public abstract class Simulation {

    protected final Stop[] stops;
    protected final int numberOfPassengers;
    protected final int tramCapacity;
    protected final TramLine[] tramLines;
    protected final int stopCapacity;

    protected Statistic passengersTravels;
    protected Statistic sumOfWaitingTime;
    protected int numberOfLastWaiting;

    public Simulation(Stop[] stops, int numberOfPassengers, int tramCapacity, TramLine[] tramLines, int stopCapacity) {
        this.stops = stops;
        this.numberOfPassengers = numberOfPassengers;
        this.tramCapacity = tramCapacity;
        this.tramLines = tramLines;
        this.stopCapacity = stopCapacity;

        this.numberOfLastWaiting = 0;
        passengersTravels = new Statistic(0);
        sumOfWaitingTime = new Statistic(0);
    }

    public Statistic averageWaitingTime() {
        if (sumOfWaitingTime.value() == 0) return new Statistic(0);
        else return new Statistic(sumOfWaitingTime.value() / (passengersTravels.value() + numberOfLastWaiting));
    }

    public abstract void simulate(EventQueue queue, Statistic passengersTravels, Statistic sumOfWaitingTime);

    public abstract void writeStatistics();
}
