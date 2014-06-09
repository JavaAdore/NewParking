/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author orcl
 */
public class CoordinateHelper {

    CoordinateHelper() {
    }
    double minLon = 0;
    double maxLon = 0;
    double minLat = 0;
    double maxLat = 0;

    public CoordinateHelper(GeoLocation[] boundingCoordinates) {
        GeoLocation temp = boundingCoordinates[0];
        minLat = temp.getLatitudeInDegrees();
        maxLat = temp.getLatitudeInDegrees();
        minLon = temp.getLongitudeInDegrees();
        maxLon = temp.getLongitudeInDegrees();

        for (GeoLocation tempLocation : boundingCoordinates) {
            if (minLat > tempLocation.getLatitudeInDegrees()) {
                minLat = tempLocation.getLatitudeInDegrees();
            }
            if (minLon > tempLocation.getLongitudeInDegrees()) {
                minLon = tempLocation.getLongitudeInDegrees();
            }
            if (maxLat < tempLocation.getLatitudeInDegrees()) {
                maxLat = tempLocation.getLatitudeInDegrees();
            }
            if (maxLon < tempLocation.getLongitudeInDegrees()) {
                maxLon = tempLocation.getLongitudeInDegrees();
            }

        }


    }

    public double getMinLon() {
        return minLon;
    }

    public double getMaxLon() {
        return maxLon;
    }

    public double getMinLat() {
        return minLat;
    }

    public double getMaxLat() {
        return maxLat;
    }
}
