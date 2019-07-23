package collision;

import Objects.*;

import java.awt.geom.Area;
import java.util.ArrayList;

/**
 * Created by amini on 06/26/2017.
 */

public class CollisionDetector {

    public static boolean in_line = false;

    public static boolean CollisionDetector(Man man, Lane[] items) {

        for (Lane item : items) {
            for (int i = 0; i < item.getLaneItems().size(); i++) {
                Area intersect = new Area(item.getLaneItems().get(i).getBoundingBox());
                intersect.intersect(new Area(man.getBoundingBox()));
                if (!intersect.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void AIMan(Man man, Lane[] items){
        boolean move = true;
        ArrayList<LaneItem> all_item = new ArrayList<LaneItem>();
        for (Lane item : items) {
            for (int i = 0; i<item.getLaneItems().size(); i++){
                all_item.add(item.getLaneItems().get(i));
            }
        }
            for (int i = 0; i < all_item.size(); i++) {
               if (man.getY()>100){
                   Area intersect = new Area(all_item.get(i).getCar_3BoundingBox());
                   intersect.intersect(new Area(man.getBoundingBox()));
                           if (!intersect.isEmpty()){
                               move = false;
                           }
                        }
                   }
        if (move) {
            man.setY(man.getY() - 1);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            man.setDirection(Man.UP);
        }
    }

    public static void carCrash( Lane[] items) {
        boolean iscar = true;
        boolean iscar_2 = true;
        ArrayList<LaneItem> all_item = new ArrayList<LaneItem>();
        for (Lane item : items) {
            for (int i = 0; i<item.getLaneItems().size(); i++){
                all_item.add(item.getLaneItems().get(i));
            }
        }

                for (int a = 0; a < all_item.size(); a++) {
                    for (int j = 0; j < all_item.size(); j++) {
                        if (a != j) {
                            if (all_item.get(a).getDirection() == Lane.LEFT && all_item.get(j).getDirection() == Lane.LEFT) {
                                if (all_item.get(a).getX() + all_item.get(a).getType() * 40 + 3 == all_item.get(j).getX()&& all_item.get(a).getY() == all_item.get(j).getY()) {
                                    for (int k = 0; k< all_item.size(); k++){
                                        Area intersect = new Area(all_item.get(k).getBoundingBox());
                                        intersect.intersect(new Area(all_item.get(j).getCarBoundingBox()));
                                        if (!intersect.isEmpty()){
                                            iscar = false;
                                        }
                                    }
                                        if (all_item.get(a).getY()<(WalkManPanel.line_numbers/2-1)*40+75 && iscar){
                                            all_item.get(j).setY(all_item.get(a).getY()+40);
                                            iscar = true;
                                        }else {
                                            all_item.get(j).setSpeed(all_item.get(a).getspeed());
                                        }
                                }
                            }

                            else {
                                if (all_item.get(a).getX() == all_item.get(j).getX() + all_item.get(j).getType() * 40 + 3 && all_item.get(a).getY() == all_item.get(j).getY()) {
                                    for (int k = 0; k< all_item.size(); k++){
                                        Area intersect_2 = new Area(all_item.get(k).getBoundingBox());
                                        intersect_2.intersect(new Area(all_item.get(j).getCar_2BoundingBox()));
                                        if (!intersect_2.isEmpty()){
                                            iscar_2 = false;
                                        }
                                    }
                                    if (all_item.get(j).getY()>(WalkManPanel.line_numbers/2)*40+75 && iscar_2){
                                        all_item.get(j).setY(all_item.get(a).getY()-40);
                                        iscar_2 = true;
                                    }else {
                                        all_item.get(j).setSpeed(all_item.get(a).getspeed());
                                    }

                                }
                            }
                        }
                    }
                }

    }

    public static void car_crossroad(Man man, Lane[] items){
        crossroad crossroad = new crossroad();
        Area intersect = new Area(crossroad.getBoundingBox());
        for (Lane item : items) {
            for (int i = 0; i < item.getLaneItems().size(); i++) {
                intersect.intersect(new Area(man.getBoundingBox()));
                if (!intersect.isEmpty()) {
                    in_line = true;
                    Area intersect2 = new Area(item.getLaneItems().get(i).getCar_4BoundingBox());
                    intersect2.intersect(new Area(man.getBoundingBox()));
                        if (!intersect2.isEmpty() && item.getLaneItems().get(i).getcrosser()){
                            item.getLaneItems().get(i).setSpeed(item.getLaneItems().get(i).getspeed()/2);
                            item.getLaneItems().get(i).setcrosser(false);
                        }
                }else {
                    in_line = false;
                }
            }
        }

    }

}