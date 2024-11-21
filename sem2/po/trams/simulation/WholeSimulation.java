package simulation;

import events.EventQueue;
import events.TableEventQueue;
import traffic.*;

public class WholeSimulation extends Simulation {

    private final int numberOfDays;

    public WholeSimulation(int numberOfDays, Stop[] stops, int numberOfPassengers, int tramCapacity, TramLine[] tramLines, int stopCapacity) {
        super(stops, numberOfPassengers, tramCapacity, tramLines, stopCapacity);
        this.numberOfDays = numberOfDays;
    }

    private Passenger[] setPassengers() {
        Passenger[] passengers = new Passenger[numberOfPassengers];
        for (int i = 0; i < numberOfPassengers; i++) {
            passengers[i] = new Passenger(i);
            passengers[i].chooseStartStop(stops);
        }

        return passengers;
    }

    private void setTramLines() {
        int numberOfTrams = 0;
        for (TramLine tramLine : tramLines) {
            tramLine.setTrams(numberOfTrams, tramCapacity);
            numberOfTrams += tramLine.numberOfTrams();
        }
    }

    @Override
    public void simulate(EventQueue queue, Statistic passengersTravels, Statistic sumOfWaitingTime) {
        Passenger[] passengers = setPassengers();
        setTramLines();

        DaySimulation[] daySimulations = new DaySimulation[numberOfDays];

        for (int i = 1; i <= numberOfDays; i++) {
            daySimulations[i - 1] = new DaySimulation(i, stops, passengers, tramCapacity, tramLines, stopCapacity);
            daySimulations[i - 1].simulate(queue, passengersTravels, sumOfWaitingTime);
        }

        for (int i = 1; i <= numberOfDays; i++) {
            daySimulations[i - 1].writeStatistics();
        }
    }

    @Override
    public void writeStatistics() {
        System.out.println("Statystyki całej symulacji: ");
        System.out.println(" - Łączna liczba przejazdów: " + this.passengersTravels);
        System.out.println(" - Średni czas czekania na przystankach: " + averageWaitingTime() + " min");
    }

    private void writeStops() {
        for (Stop stop : stops) {
            System.out.println(stop);
        }
    }

    private void writeTramLines() {
        for (TramLine tramLine : tramLines) {
            System.out.println(tramLine);
        }
    }

    private void writeParameters() {
        System.out.println(numberOfDays);
        System.out.println(stopCapacity);
        System.out.println(stops.length);
        writeStops();
        System.out.println(numberOfPassengers);
        System.out.println(tramCapacity);
        System.out.println(tramLines.length);
        writeTramLines();
    }

    public void simulate() {
        EventQueue queue = new TableEventQueue();
        writeParameters();
        simulate(queue, this.passengersTravels, this.sumOfWaitingTime);
        writeStatistics();
    }
}
