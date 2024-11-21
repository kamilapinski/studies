package traffic;

public abstract class Vehicle {
    private final int vehicleNumber;
    private final Line line;

    public Vehicle(int vehicleNumber, Line line) {
        this.vehicleNumber = vehicleNumber;
        this.line = line;
    }

    @Override
    public String toString() {
        return line + " (nr bocz. " + vehicleNumber + ")";
    }


}
