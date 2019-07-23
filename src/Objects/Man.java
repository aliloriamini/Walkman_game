package Objects;

/**
 * Created by amini on 06/26/2017.
 */

import java.awt.*;
import java.io.Serializable;

public class Man implements Serializable{

    public static final int LEFT = 0, RIGHT = 1, DOWN = 2, UP = 3, WIDTH = 22;
    private Rectangle boundingBox;
    private int x, y;
    public static int direction;


    public Man(int x, int y) {
        direction = UP;
        this.x = x;
        this.y = y;
        this.boundingBox = new Rectangle(this.x , this.y , 20, 20);
    }


    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.boundingBox = new Rectangle(this.x , this.y , 20, 20);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.boundingBox = new Rectangle(this.x , this.y , 20, 20);
    }

    public int getHitX() {
        return x + 9;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Rectangle getBoundingBox(){
        return this.boundingBox;
    }

}
