package traffic;

public class Time {
    private int hour;
    private int min;

    public Time(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }

    public Time(int mins) {
        hour = mins / 60;
        min = mins - hour * 60;
    }

    public Time(Time other) {
        hour = other.hour;
        min = other.min;
    }

    public int toMins() {
        return hour * 60 + min;
    }

    public void plus(int mins) {
        mins += this.toMins();
        hour = mins / 60;
        min = mins - hour * 60;
    }

    public void plus(Time other) {
        plus(other.toMins());
    }

    public int compareTo(Time other) {
        return Integer.compare(this.toMins(), other.toMins());
    }

    private String twoDigits(int x) {
        if (x < 10) return "0" + x;
        else return String.valueOf(x);
    }

    @Override
    public String toString() {
        return twoDigits(hour) + ":" + twoDigits(min);
    }
}
