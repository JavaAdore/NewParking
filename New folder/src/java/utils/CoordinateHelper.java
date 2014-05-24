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

    public CoordinateHelper(Object[] boundingCoordinates) {

        GeoLocation temp = (GeoLocation) boundingCoordinates[0];
        minLat = temp.getLatitudeInDegrees();
        maxLat = temp.getLatitudeInDegrees();
        minLon = temp.getLongitudeInDegrees();
        maxLon = temp.getLongitudeInDegrees();

        for (Object tempLocation : boundingCoordinates) {
            GeoLocation location = (GeoLocation) tempLocation;
            if (minLat > location.getLatitudeInDegrees()) {
                minLat = location.getLatitudeInDegrees();
            }
            if (minLon > location.getLongitudeInDegrees()) {
                minLon = location.getLongitudeInDegrees();
            }
            if (maxLat < location.getLatitudeInDegrees()) {
                maxLat = location.getLatitudeInDegrees();
            }
            if (maxLon < location.getLongitudeInDegrees()) {
                maxLon = location.getLongitudeInDegrees();
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
