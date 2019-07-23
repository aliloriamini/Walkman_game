/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.io.Serializable;

/**
 *
 * @author Sohrab
 */
public class SaveHelper implements Serializable {

    private int Life;
    private int Time;

    public SaveHelper(int Life, int Time) {
        this.Life = Life;
        this.Time = Time;
    }

    public int getLife() {
        return Life;
    }
    
     public int getTime() {
        return Time;
    }
}
