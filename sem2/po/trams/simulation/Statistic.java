package simulation;

import java.text.DecimalFormat;

public class Statistic {

    private double value;

    public Statistic(int value) {
        this.value = value;
    }

    public Statistic(double value) {
        this.value = value;
    }

    public void add(int x) {
        value += x;
    }

    public void add(Statistic other) {
        value += other.value();
    }

    public double value() {
        return value;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return String.valueOf(df.format(value));
    }

}
