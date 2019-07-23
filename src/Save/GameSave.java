/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Save;

import Objects.Lane;
import Objects.LaneItem;
import Objects.Man;
import Objects.SaveHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sohrab
 */
final public class GameSave implements ISaveCar {

    private File oPathCarX = null;
    private File oPathCarY = null;
    private File oPathCarDir = null;
    private File oPathCarSpeed = null;
    private File oPathCarType = null;
    private File oPathCarLane = null;

    private File oPathUser = null;
    private File oPathUserDes = null;

    public GameSave() {
        oPathCarX = new File("src/files/CarX.txt");
        oPathCarY = new File("src/files/CarY.txt");
        oPathCarDir = new File("src/files/Cardir.txt");
        oPathCarSpeed = new File("src/files/Carspeed.txt");
        oPathCarType = new File("src/files/Cartype.txt");
        oPathCarLane = new File("src/files/CarLane.txt");
        oPathUser = new File("src/files/User.txt");
        oPathUserDes = new File("src/files/UserDes.ser");

    }

    @Override
    public void Delete() {
        if (oPathCarX.exists()) {
            oPathUser.delete();
            oPathUserDes.delete();
            oPathCarX.delete();
            oPathCarY.delete();
            oPathCarDir.delete();
            oPathCarSpeed.delete();
            oPathCarType.delete();
            oPathCarLane.delete();

        }
    }

    @Override
    public void SaveCar(Lane[] items) {
        ArrayList<LaneItem> oItems = new ArrayList<LaneItem>();
        for (Lane item : items) {
            for (int i = 0; i < item.getLaneItems().size(); i++) {
                oItems.add(item.getLaneItems().get(i));
            }
        }
        try {
            FileWriter writerX = new FileWriter(oPathCarX, true);
            FileWriter writerY = new FileWriter(oPathCarY, true);
            FileWriter writerDir = new FileWriter(oPathCarDir, true);
            FileWriter writerSpeed = new FileWriter(oPathCarSpeed, true);
            FileWriter writerType = new FileWriter(oPathCarType, true);

            for (LaneItem var : oItems) {
                writerX.write(var.getX() + " ");
                writerY.write(var.getY() + " ");
                writerDir.write(var.getDirection() + " ");
                writerSpeed.write(var.getspeed() + " ");
                writerType.write(var.getType() + " ");
            }
            writerX.close();
            writerY.close();
            writerDir.close();
            writerSpeed.close();
            writerType.close();

        } catch (IOException e) {
        }

    }

    @Override
    public void SaveLine(int Line) {
        try {
            FileWriter writer = new FileWriter(oPathCarLane, true);
            writer.write(Line + " ");
            writer.close();

        } catch (IOException e) {
        }
    }

    @Override
    public double[] LoadCarX() {
        int i = 0;
        double[] CarX = new double[20];
        try {
            Scanner in = new Scanner(oPathCarX);
            while (in.hasNext()) {
                CarX[i++] = in.nextDouble();

            }
        } catch (Exception e) {
        }
        return CarX;
    }

    @Override
    public double[] LoadCarY() {
        int i = 0;
        double[] CarY = new double[20];
        try {
            Scanner in = new Scanner(oPathCarY);
            while (in.hasNext()) {
                CarY[i++] = in.nextDouble();

            }
        } catch (Exception e) {
        }
        return CarY;
    }

    @Override
    public int[] LoadCarDir() {
        int i = 0;
        int[] CarDir = new int[20];
        try {
            Scanner in = new Scanner(oPathCarDir);
            while (in.hasNextInt()) {
                CarDir[i++] = in.nextInt();

            }
        } catch (Exception e) {
        }
        return CarDir;
    }

    @Override
    public int[] LoadCarType() {
        int i = 0;
        int[] CarTyp = new int[20];
        try {
            Scanner in = new Scanner(oPathCarType);
            while (in.hasNextInt()) {
                CarTyp[i++] = in.nextInt();

            }
        } catch (Exception e) {
        }
        return CarTyp;
    }

    @Override
    public double[] LoadCarSpeed() {
        int i = 0;
        double[] Carspd = new double[20];
        try {
            Scanner in = new Scanner(oPathCarSpeed);
            while (in.hasNextDouble()) {
                Carspd[i++] = in.nextDouble();

            }
        } catch (Exception e) {
        }
        return Carspd;
    }

    @Override
    public int LoadLine() {
        int Line = 4;
        try {
            Scanner in = new Scanner(oPathCarLane);
            Line = in.nextInt();
        } catch (IOException e) {
        }
        return Line;
    }

    public void SaveUser(Man gameMan) {
        Man oMan = gameMan;
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(oPathUser));
            out.writeObject(oMan);
            out.close();

        } catch (IOException e) {
        }

    }

    public void SaveUserHelper(SaveHelper Helper) {

        SaveHelper oHelper = Helper;
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(oPathUserDes));
            out.writeObject(oHelper);
            out.close();

        } catch (IOException e) {
        }

    }

    public Man LoadUser() {
        Man oMan = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(oPathUser));
            oMan = (Man) in.readObject();
            in.close();

        } catch (Exception e) {
        }
        return oMan;
    }

    public SaveHelper LoadUserHelper() {
        SaveHelper oHelper = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(oPathUserDes));
            oHelper = (SaveHelper) in.readObject();
            in.close();

        } catch (Exception e) {
        }
        return oHelper;
    }

}
