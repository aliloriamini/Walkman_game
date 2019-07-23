package Objects;

import java.awt.*;

/**
 * Created by amini on 07/04/2017.
 */
public class crossroad {
    public static int postion = WalkManPanel.WIDTH/2;
    public static int Crossroad_width = 60;
    public static int Crossroad_hight = 4;
    public static int crossroad_x = postion-Crossroad_width;
    private Rectangle boundingBox;
    public Rectangle getBoundingBox(){
        this.boundingBox = new Rectangle( (int) crossroad_x ,0, Crossroad_width,WalkManPanel.HEIGHT);
        return this.boundingBox;
    }

}
