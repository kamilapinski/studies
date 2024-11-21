package traffic;

public class Route {
    private final int length;
    private final Stop[] stops;
    private final Time[] times;

    public Route(int length, Stop[] stops, Time[] times) {
        this.length = length;
        this.stops = stops;
        this.times = times;
    }

    public Time calculateTimeOfRoute() {
        int mins = 0;
        for (int i = 0; i < length; i++) {
            mins += 2 * times[i].toMins();
        }
        return new Time(mins);
    }

    public int timeOfStopMins(int ind) {
        return times[ind].toMins();
    }

    public Stop stop(int ind) {
        return stops[ind];
    }

    public Stop[] partOfStops(int a, int b) {
        if (a > b) {
            int r = b;
            b = a;
            a = r;
        }

        if (a < 0 || b >= length) {
            return null;
        }

        Stop[] ans = new Stop[b - a + 1];
        int ind = 0;
        for (int i = a; i <= b; i++) {
            ans[ind] = stops[i];
            ind++;
        }
        return ans;
    }

    public int length() {
        return length;
    }

    public Route reverse() {
        Stop[] reversedStops = new Stop[length];
        Time[] reversedTimes = new Time[length];

        for (int i = 0; i < length - 1; i++) {
            reversedStops[i] = stops[length - i - 1];
            reversedTimes[i] = times[length - i - 2];
        }

        reversedStops[length - 1] = stops[0];
        reversedTimes[length - 1] = times[length - 1];

        return new Route(length, reversedStops, reversedTimes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(stops[i].toString()).append(" ");
            sb.append(times[i].toMins()).append(" ");
        }
        return sb.toString();
    }
}
