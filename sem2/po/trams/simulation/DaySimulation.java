package simulation;

import events.Event;
import events.EventQueue;
import traffic.Losowanie;
import traffic.Passenger;
import traffic.Stop;
import traffic.TramLine;

public class DaySimulation extends Simulation {

    private final int dayNumber;
    private final Passenger[] passengers;

    public DaySimulation(int dayNumber, Stop[] stops, Passenger[] passengers, int tramCapacity, TramLine[] tramLines, int stopCapacity) {
        super(stops, passengers.length, tramCapacity, tramLines, stopCapacity);
        this.passengers = passengers;
        this.dayNumber = dayNumber;
    }

    private void clearTramLines() {
        for (TramLine tramLine : tramLines) {
            tramLine.clearTrams();
        }
    }

    private void clearAll(EventQueue queue) {
        clearTramLines();
        queue.clear();
    }

    private void passengersGoToStops(EventQueue queue) {
        for (Passenger passenger : passengers) {
            passenger.chooseTime();
            passenger.goToStop(dayNumber, queue);
        }
    }

    private void tramsGo(EventQueue queue) {
        for (TramLine tramLine : tramLines) {
            tramLine.tramsGo(queue, dayNumber);
        }
    }

    private void passengersTravel(EventQueue queue, Statistic passengersTravels, Statistic sumOfWaitingTime) {
        while (!queue.empty()) {
            Event currentEvent = queue.top();
            queue.pop();

            if (currentEvent.canBeDone()) {
                System.out.println(currentEvent);
            }
            currentEvent.doPossibleMovements(queue, this.passengersTravels, this.sumOfWaitingTime);
        }

        int numberOfLastWaiting = 0;
        for (Stop stop : stops) {
            numberOfLastWaiting += stop.passengersGoHome(this.sumOfWaitingTime);
        }

        //super.numberOfLastWaiting = numberOfLastWaiting;

        passengersTravels.add(this.passengersTravels);
        sumOfWaitingTime.add(this.sumOfWaitingTime);
    }

    @Override
    public void writeStatistics() {
        System.out.println("Statystyki symulacji dnia: " + dayNumber);
        System.out.println(" - Łączna liczba przejazdów: " + this.passengersTravels);
        System.out.println(" - Łączny czas czekania na przystankach: " + this.sumOfWaitingTime + " min");
    }

    @Override
    public void simulate(EventQueue queue, Statistic passengersTravels, Statistic sumOfWaitingTime) {
        passengersGoToStops(queue);
        tramsGo(queue);
        passengersTravel(queue, passengersTravels, sumOfWaitingTime);
        clearAll(queue);
    }
}
