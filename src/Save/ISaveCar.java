/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Save;

import Objects.CarLane;
import Objects.Lane;

/**
 *
 * @author Sohrab
 */
public interface ISaveCar {

    public void SaveCar(Lane[] items);

    public void Delete();

    public void SaveLine(int Line);

    public int LoadLine();

    public double[] LoadCarX();

    public double[] LoadCarY();

    public int[] LoadCarDir();

    public double[] LoadCarSpeed();

    public int[] LoadCarType();

}
