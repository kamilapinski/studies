package dataReading;

import simulation.WholeSimulation;
import traffic.Route;
import traffic.Stop;
import traffic.TramLine;
import traffic.Time;

import java.util.Scanner;

public class Reader {


    public WholeSimulation wholeSimulationRead() {
        Scanner scanner = new Scanner(System.in);

        final int numberOfDays = scanner.nextInt();
        final int stopCapacity = scanner.nextInt();

        final int numberOfStops = scanner.nextInt();
        Stop[] stops = new Stop[numberOfStops];
        for (int i = 0; i < stops.length; i++) {
            String stopName = scanner.next();
            stops[i] = new Stop(stopName, stopCapacity);
        }

        final int numberOfPassengers = scanner.nextInt();
        final int tramCapacity = scanner.nextInt();

        final int numberOfTramLines = scanner.nextInt();
        TramLine[] tramLines = new TramLine[numberOfTramLines];
        for (int i = 0; i < tramLines.length; i++) {
            final int numberOfTrams = scanner.nextInt();
            final int sizeOfRoute = scanner.nextInt();

            Stop[] stopsOfRoute = new Stop[sizeOfRoute];
            Time[] timesOfRoute = new Time[sizeOfRoute];

            for (int j = 0; j < sizeOfRoute; j++) {
                String stopName = scanner.next();
                int time = scanner.nextInt();

                Stop stop = null;
                for (Stop stopOfStops : stops) {
                    if (stopOfStops.hasName(stopName)) {
                        stop = stopOfStops;
                        break;
                    }
                }

                stopsOfRoute[j] = stop;
                timesOfRoute[j] = new Time(time);
            }

            Route route = new Route(sizeOfRoute, stopsOfRoute, timesOfRoute);

            tramLines[i] = new TramLine(i, numberOfTrams, sizeOfRoute, route);

        }

        scanner.close();

        return new WholeSimulation(numberOfDays, stops, numberOfPassengers, tramCapacity, tramLines, stopCapacity);
    }


}
