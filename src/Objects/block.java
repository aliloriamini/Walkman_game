package Objects;

import java.awt.*;

/**
 * Created by amini on 07/07/2017.
 */
public class block {
    public  int block_y = (WalkManPanel.line_numbers/2)*40 + 75;
    public  int block_Width = WalkManPanel.WIDTH;
    public  int block_hight = 4;
    private Rectangle boundingBox;
    public Rectangle getBoundingBox(){
        this.boundingBox = new Rectangle(0 ,block_y, block_Width,block_hight);
        return this.boundingBox;
    }
}
