/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.calculators;

import guts.entities.Axis;
import guts.entities.Location;


/**
     * Calculates the needed positioncorrections based on the given
     * current location, position and direction. New position values are
     * returned as axis object.
     * 
     * @param currentLocation as location object
     * @param currentAxis as axis object
     * @param currentAngle as float
     * @param activeTowerLocation as Location object
     * @return newAxis as axis object
     */

/**
 * This class fetches the values of the current position 
 * of the Antenna (Pitch,Yaw,Roll), 
 * and returns the new calculated values​​, to correct the alignment of it.
 * @author Fethiye Güney
 */
public class AntennaCorrectionCalculator {
    
    public Axis calculateCorrection(Location currentLocation, Axis currentAxis, 
            double currentAngle, Location activeTowerLocation, double getCurrentAngel){ 
    
    double i, newAngle, deltax, deltay, pitch, newPitch, roll, newRoll;
        
  deltax = activeTowerLocation.getLongitude() - currentLocation.getLongitude();
  deltay = activeTowerLocation.getLatitude() - currentLocation.getLatitude();
        
       if (deltay == 0) {
           if(deltax > 0){
               newAngle=90;
           }
           else {
               newAngle=270;
           }
       }          
       else if (deltax == 0) {
          if(deltay > 0){
              newAngle=0;
          }
          else {
              newAngle=180;
          }
       }
      
       else{
          i=deltay/deltax;
          newAngle = Math.atan(Math.toRadians(i));
        }
        
       newAngle = (360 - getCurrentAngel + newAngle) % 360;
       
       roll=currentAxis.getRoll();
       newRoll=roll * (-1);
       pitch=currentAxis.getPitch();
       newPitch=pitch * (-1);
       
       return new Axis(newPitch,newAngle,newRoll);
    }
}