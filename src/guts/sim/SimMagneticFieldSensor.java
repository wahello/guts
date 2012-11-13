/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.sim;

import guts.*;

/**
 *
 * @author Cedric Ohle
 * @author Patrick Selge
 */
public class SimMagneticFieldSensor {
    
    public SimMagneticFieldSensor() {

    }

    public double getAngelToMagneticNorth() {
        if(simLength <= 0) {
            simLength = (int)(Math.random() * (1600 / Config.REFRESHRATE)) + 1;
            direction = getNextDirection(Math.random());  
        }        

        double delta = getDeltaAngel(Math.random());
        
     
        if(direction != 0) {
            angel = angel + (direction * delta);
            angel = (angel < 0) ? (angel + 360) : (angel % 360); 
        }
              
        if(Config.DEBUG >= Config.LOG_ALL) {
            System.out.println("Magnetic Field Sensor: retries:" + simLength + 
                    " | direction:" + direction + " | orientation:" + angel + 
                    " | delta:"+delta);
        }
        simLength--;
        
        return angel;
    }
    
    protected int getNextDirection(double random) {
        int multi = (((int)Math.ceil(random * 3)));
        
        if (multi==3) { return 1; }
        if (multi==2) { return -1; }
        return 0;
    }
    
    protected double getDeltaAngel(double random) {
        return ((int)(random * 300 + 1))/(16000.0 / Config.REFRESHRATE);
    }

    
    protected static double getCurrentAngel(){
        return angel;
    }
    
    private static double angel = 0;
    private int simLength = 0;
    private int direction = 0;
    
}
