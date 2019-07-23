package Objects;

/**
 * Created by amini on 06/26/2017.
 */

import collision.CollisionDetector;

import java.awt.*;

public class LaneItem {

    boolean crosser = true;
    double x, y,speed;
    int direction;
    int type;
    public static Rectangle boundingBox;

    public LaneItem(int speed, int type, int direction, double x, double y) {
        this.speed = speed;
        this.type = type;
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.boundingBox = new Rectangle((int) x + 3, (int) y + 5, getWidth() - 5, 29);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    int getWidth() {
        return 40;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getspeed(){
        return speed;
    }

    public int getType() {
        return type;
    }

    void update() {
        CollisionDetector.carCrash(WalkManGame.carLanes);
        CollisionDetector.car_crossroad(WalkManGame.player,WalkManGame.carLanes);
        if (WalkManPanel.AI){
            CollisionDetector.AIMan(WalkManGame.player,WalkManGame.carLanes);
        }
        if (direction == Lane.RIGHT)
            setX(x + speed);
        else if (direction == Lane.LEFT)
            setX(x - speed);
    }

    public Rectangle getBoundingBox(){
        this.boundingBox = new Rectangle( (int) x + 3, (int) this.y + 5, this.getWidth() - 5,29);
        return this.boundingBox;
    }
    public Rectangle getCarBoundingBox(){
        this.boundingBox = new Rectangle( (int) x + 3, (int) this.y + 45, this.getWidth() - 5,29);
        return this.boundingBox;
    }
    public Rectangle getCar_2BoundingBox(){
        this.boundingBox = new Rectangle( (int) x + 3, (int) this.y - 35, this.getWidth() - 5,29);
        return this.boundingBox;
    }
    public Rectangle getCar_3BoundingBox(){
        this.boundingBox = new Rectangle( (int) x , (int) this.y + 15, type*40,40);
        return this.boundingBox;
    }
    public Rectangle getCar_4BoundingBox(){
        this.boundingBox = new Rectangle( (int) x-20 , (int) this.y, type*40+40,40);
        return this.boundingBox;
    }

    public void setcrosser(boolean crosser){
        this.crosser = crosser;
    }

    public boolean getcrosser(){
        return this.crosser;
    }

}
