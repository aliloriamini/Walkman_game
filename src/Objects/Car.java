package Objects;

/**
 * Created by amini on 06/26/2017.
 */

public class Car extends LaneItem {

    public static final int SEMI = 3, LIMO = 2, CAR_2 = 1;

    public Car(double speed, int type, int direction, double x, double y) {
        super((int) speed, type, direction, x, y);
    }

    public int getWidth() {
        if (type == CAR_2)
            return 40;
        if (type == LIMO)
            return 80;
        if (type == SEMI)
            return 120;
        return 0;
    }
}
