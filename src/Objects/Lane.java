package Objects;

/**
 * Created by amini on 06/26/2017.
 */

import java.util.ArrayList;

public class Lane {

    public static final int LEFT = 0, RIGHT = 1;
    int y, direction;
    int speed;
    ArrayList<LaneItem> laneItems = new ArrayList<LaneItem>();



    public Lane(int speed, int direction, int y) {
        this.speed = speed;
        this.direction = direction;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public double getSpeed() {
        return speed;
    }

    public ArrayList<LaneItem> getLaneItems() {
        return laneItems;
    }

    void update() {
        for (LaneItem laneItem : laneItems) {
            laneItem.update();
        }
    }
}
