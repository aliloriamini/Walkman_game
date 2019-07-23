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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sohrab
 */
final public class VideoSaver implements ISaveCar {

    private File oPathCar = null;
    private File oPathUserX = null;
    private File oPathUserY = null;
    private File oPathUserDir = null;
    private File oPathUserLives = null;
    private File oPathUserTime = null;

    private File oPathCarX = null;
    private File oPathCarY = null;
    private File oPathCarDir = null;
    private File oPathCarSpeed = null;
    private File oPathCarType = null;

    private File oPathCarLane = null;

    private String Name;

    public VideoSaver(String Name) {
        this.Name = Name;
        oPathUserX = new File("src/files/" + Name + "X.txt");
        oPathUserY = new File("src/files/" + Name + "Y.txt");
        oPathUserDir = new File("src/files/" + Name + "Dir.txt");
        oPathUserLives = new File("src/files/" + Name + "Live.txt");
        oPathUserTime = new File("src/files/" + Name + "Time.txt");

        oPathCarX = new File("src/files/" + Name + "CarX.txt");
        oPathCarY = new File("src/files/" + Name + "CarY.txt");
        oPathCarDir = new File("src/files/" + Name + "CarDir.txt");
        oPathCarSpeed = new File("src/files/" + Name + "CarSpeed.txt");
        oPathCarType = new File("src/files/" + Name + "CarTyp.txt");
        oPathCarLane = new File("src/files/" + Name + "CarLane.txt");

    

    }

    @Override
    public void Delete() {

        if (oPathUserX.exists()) {
            oPathUserX.delete();
            oPathUserY.delete();
            oPathUserDir.delete();
            oPathUserLives.delete();
            oPathUserTime.delete();
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

    public void SaveUser(Man gameMan, SaveHelper Helper) {

        try {
            FileWriter writerX = new FileWriter(oPathUserX, true);
            FileWriter writerY = new FileWriter(oPathUserY, true);
            FileWriter writerDir = new FileWriter(oPathUserDir, true);
            FileWriter writerLiv = new FileWriter(oPathUserLives, true);
            FileWriter writerTIm = new FileWriter(oPathUserTime, true);
            writerX.write(gameMan.getX() + " ");
            writerY.write(gameMan.getY() + " ");
            writerDir.write(gameMan.getDirection() + " ");
            writerLiv.write(Helper.getLife() + " ");
            writerTIm.write(Helper.getTime() + " ");
            writerX.close();
            writerY.close();
            writerDir.close();
            writerLiv.close();
            writerTIm.close();

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
    public int LoadLine() {
        int Line = 4;
        try {
            Scanner in = new Scanner(oPathCarLane);
            Line = in.nextInt();
        } catch (IOException e) {
        }
        return Line;
    }

    //-----------------------------------------------------
    public int[] LoadUserX() {
        int i = 0;
        int[] userCordX = new int[100000];
        try {
            Scanner CordX = new Scanner(oPathUserX);
            while (CordX.hasNextInt()) {
                userCordX[i++] = CordX.nextInt();

            }
        } catch (Exception e) {
        }

        return userCordX;

    }

    //-----------------------------------------------------
    public int[] LoadUserY() {
        int i = 0;
        int[] userCordY = new int[100000];
        try {
            Scanner CordY = new Scanner(oPathUserY);
            while (CordY.hasNextInt()) {
                userCordY[i++] = CordY.nextInt();

            }
        } catch (Exception e) {
        }
        return userCordY;
    }

    //-----------------------------------------------------
    public int[] LoadUserDir() {
        int i = 0;
        int[] userDir = new int[100000];

        try {
            Scanner Dir = new Scanner(oPathUserDir);
            while (Dir.hasNextInt()) {
                userDir[i++] = Dir.nextInt();

            }
        } catch (Exception e) {
        }
        return userDir;
    }

    //-----------------------------------------------------
    public int[] LoadUserLive() {
        int i = 0;
        int[] userLiv = new int[100000];

        try {
            Scanner Live = new Scanner(oPathUserLives);
            while (Live.hasNextInt()) {
                userLiv[i++] = Live.nextInt();

            }
        } catch (Exception e) {
        }
        return userLiv;
    }

    //-----------------------------------------------------
    public int[] LoadUserTime() {
        int i = 0;
        int[] userTime = new int[100000];

        try {
            Scanner Time = new Scanner(oPathUserTime);
            while (Time.hasNextInt()) {
                userTime[i++] = Time.nextInt();

            }
        } catch (Exception e) {
        }
        return userTime;
    }

    //-----------------------------------------------------
    @Override
    public double[] LoadCarX() {
        int i = 0;
        double[] CarX = new double[100000];
        try {
            Scanner CordX = new Scanner(oPathCarX);
            while (CordX.hasNextDouble()) {
                CarX[i++] = CordX.nextDouble();

            }
        } catch (Exception e) {
        }

        return CarX;

    }

    //-----------------------------------------------------
    @Override
    public double[] LoadCarY() {
        int i = 0;
        double[] CarY = new double[100000];
        try {
            Scanner CordY = new Scanner(oPathCarY);
            while (CordY.hasNextDouble()) {
                CarY[i++] = CordY.nextDouble();

            }
        } catch (Exception e) {
        }
        return CarY;
    }

    //-----------------------------------------------------
    @Override
    public int[] LoadCarDir() {
        int i = 0;
        int[] CarDir = new int[100000];

        try {
            Scanner Dir = new Scanner(oPathCarDir);
            while (Dir.hasNextInt()) {
                CarDir[i++] = Dir.nextInt();

            }
        } catch (Exception e) {
        }
        return CarDir;
    }

    //-----------------------------------------------------
    @Override
    public double[] LoadCarSpeed() {
        int i = 0;
        double[] CarSpeed = new double[100000];

        try {
            Scanner Live = new Scanner(oPathCarSpeed);
            while (Live.hasNextDouble()) {
                CarSpeed[i++] = Live.nextDouble();

            }
        } catch (Exception e) {
        }
        return CarSpeed;
    }

    //-----------------------------------------------------
    @Override
    public int[] LoadCarType() {
        int i = 0;
        int[] CarTyp = new int[100000];

        try {
            Scanner Live = new Scanner(oPathCarType);
            while (Live.hasNextInt()) {
                CarTyp[i++] = Live.nextInt();

            }
        } catch (Exception e) {
        }
        return CarTyp;
    }

}
