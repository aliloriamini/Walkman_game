package Objects;

import Save.GameSave;
import Save.VideoSaver;

/**
 * Created by amini on 06/26/2017.
 */
public class CarLane extends Lane {

    public static boolean LOAD = false;
    public static boolean VIDEO = false;

    public CarLane(int speed, int direction, int y) {
        super(speed, direction, y);

    }

    void update() {
        super.update();
        int carType = (int) (Math.random() * 3 + 1);
        speed = (int) (Math.random() * 2 + 1);

        int length;

        switch (carType) {
            case Car.SEMI: //if semi
                length = 120;
                break;
            case Car.CAR_2: //if limo
                length = 80;
                break;
            default: //else is type of car
                length = 40;
                break;
        }
        if (!VIDEO) {
            if (!LOAD) {
                if (direction == RIGHT) {
                    int location = -120 - (int) (Math.random() * 49) - length;
                    if (laneItems.size() == 0) {
                        laneItems.add(new Car(speed, (int) (Math.random() * 3 + 1), RIGHT, location, y));
                    }
                    for (int i = 0; i < laneItems.size(); i++) {
                        if (laneItems.get(i).getDirection() == LEFT && laneItems.get(i).getX() < -20) {
                            laneItems.remove(i);
                        }
                        if (laneItems.get(i).getDirection() == RIGHT && laneItems.get(i).getX() > 720) {
                            laneItems.remove(i);
                        }
                    }
                    if ((int) laneItems.get(laneItems.size() - 1).getX() + laneItems.get(laneItems.size() - 1).getWidth() > 200) {
                        Car newCar = new Car(speed, carType, RIGHT, location, y);
                        newCar.setX(location - newCar.getWidth());
                        laneItems.add(newCar);
                    }
                } else if (direction == LEFT) {
                    int location = 700 + (int) (Math.random() * 49) + length; //set location of car to spawn
                    if (laneItems.size() == 0) {
                        laneItems.add(new Car(speed, (int) (Math.random() * 3 + 1), LEFT, location, y));
                    }
                    for (int i = 0; i < laneItems.size(); i++) {
                        if (laneItems.get(i).getDirection() == RIGHT && laneItems.get(i).getX() > 720) {
                            laneItems.remove(i);
                        }
                    }
                    if ((int) laneItems.get(laneItems.size() - 1).getX() + laneItems.get(laneItems.size() - 1).getWidth() < 500) {
                        laneItems.add(new Car(speed, carType, LEFT, location, y));
                    }
                }
            } else {
                GameSave osave = new GameSave();
                double[] CarX = osave.LoadCarX();
                double[] CarY = osave.LoadCarY();
                double[] CarSpd = osave.LoadCarSpeed();
                int[] CarDir = osave.LoadCarDir();
                int[] CarTyp = osave.LoadCarType();
                for (int i = 0; i < CarX.length; i++) {
                    if (CarTyp[i] == 0) {
                        break;
                    }
                    laneItems.add(new Car(CarSpd[i], CarTyp[i], CarDir[i], CarX[i], CarY[i]));
                }

                LOAD = false;
            }
        } else {
            VideoSaver oSaver = new VideoSaver("Video");
            double[] CarX = oSaver.LoadCarX();
            double[] CarY = oSaver.LoadCarY();
            double[] CarSpd = oSaver.LoadCarSpeed();
            int[] CarDir = oSaver.LoadCarDir();
            int[] CarTyp = oSaver.LoadCarType();
            for (int i = 0; i < 15; i++) {

                laneItems.add(new Car(CarSpd[i], CarTyp[i], CarDir[i], CarX[i], CarY[i]));

            }

            VIDEO = false;
        }
    }

}
