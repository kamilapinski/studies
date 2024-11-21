package traffic;

public abstract class Line {
    protected final Time START_TIME = new Time(6, 0);
    protected final Time END_TIME = new Time(23, 0);

    protected final int lineNumber;
    protected final int numberOfVehicles;
    protected final int routeSize;
    protected final Route route;

    public Line(int lineNumber, int numberOfVehicles, int routeSize, Route route) {
        this.lineNumber = lineNumber;
        this.numberOfVehicles = numberOfVehicles;
        this.routeSize = routeSize;
        this.route = route;
    }

    @Override
    public String toString() {
        return String.valueOf(lineNumber);
    }
}
