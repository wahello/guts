

package guts.sensors;

import guts.Config;
import guts.entities.Location;
import guts.sim.SimGPS;

/**
 * This class provides access to the locationdata from a GPS. It handles the
 * hardware access.
 * @author Cedric Ohle
 */
public class GPS {
    private int address;
    private SimGPS simGPS;
    
    public GPS(int address){
        this.address = address;
    }
    
    public GPS(){
        this.simGPS = new SimGPS();
    }
    
    public void setStartPoint(double latitude, double longitude) {
        simGPS.setLocation(new Location(latitude, longitude));
    }
    
    
    /**
     * This function returns the current location as a location object.
     * @return location as a location object
     */
    public Location fetchLocation(){
        if (Config.SIMULATIONENABLED){
            Location l = simGPS.getLocation();
            return l;
        }
        else{
            // Implement real hardware access
            return null;
        }
    }
}
