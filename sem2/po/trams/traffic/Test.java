package traffic;

import dataReading.Reader;
import simulation.WholeSimulation;

public class Test {

    public static void main(String[] args) {
        Reader reader = new Reader();
        WholeSimulation wholeSimulation = reader.wholeSimulationRead();

        wholeSimulation.simulate();
    }

}