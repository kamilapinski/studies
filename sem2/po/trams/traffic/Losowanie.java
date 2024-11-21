package traffic;

import java.util.Random;

public class Losowanie {
    public static int losuj(int dolna, int gorna) {
        Random random = new Random();
        return random.nextInt(gorna - dolna + 1) + dolna;
    }

    public Stop stopDraw(Stop[] stops) {
        if (stops.length == 0) return null;
        int ind = losuj(0, stops.length - 1);
        return stops[ind];
    }

    public Time timeDraw(Time downTime, Time upTime) {
        int down = downTime.toMins();
        int up = upTime.toMins();
        if (down > up) {
            int r = down;
            down = up;
            up = r;
        }
        return new Time(losuj(down, up));
    }
}
