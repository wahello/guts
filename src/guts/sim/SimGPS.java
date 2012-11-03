/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guts.sim;

import guts.Config;
import guts.entities.Location;
import java.util.Date;

/**
 *
 * @author Cedric Ohle
 */
public class SimGPS {
    
    private Location location;
    
    public void setLocation(Location startLocation){
        this.location = startLocation;
    }
    
    public void generateNewLocation(){
        //Todo: Max 180 abfangen, bei Long und lat
        double newLongitude;
        double newLatitude;
        double angel = SimMagneticFieldSensor.getCurrentAngel();
        
        double longitudedelta = (Math.random() * 300+1)/(160000000/ Config.REFRESHRATE);
        
        if(angel > 0 && angel <= 90 ){
            newLongitude = this.location.getLongitude() + longitudedelta;
            newLatitude = this.location.getLatitude() + Math.sin(angel);
        } else if(angel > 90 && angel <= 180){
            newLongitude = this.location.getLongitude() + longitudedelta;
            newLatitude = this.location.getLatitude() - Math.sin(angel);
        } else if(angel > 180 && angel <= 270){
            newLongitude = this.location.getLongitude() - longitudedelta;
            newLatitude = this.location.getLatitude() - Math.sin(angel);
        } else {
            newLongitude = this.location.getLongitude() - longitudedelta;
            newLatitude = this.location.getLatitude() + Math.sin(angel);
        }
        Location newLocation = new Location(newLatitude, newLongitude);
    }

    public double getLongitude() {
        return this.location.getLongitude();
    }

    public double getLatitude() {
        return this.location.getLatitude();
    }
    
    public Location getLocation() {
        return this.location;
    }
    
}
