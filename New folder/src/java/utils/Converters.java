/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author orcl
 */
public class Converters {

    public static double MeterToPixels(double meters) 
    {
        return meters * 3779.527559055;
    }

    public static double KiloMetersToPixels(double kiloMeters) {

        return kiloMeters * 1000 * 3779.527559055;
    }

    public static double pixelsToMeter(double pixels) {

        return pixels * (1 / 3779.527559055);
    }

    public static double pixelsToKiloMeter(double pixels) {
        return (pixels / 1000) * (1 / 3779.527559055);
    }

    public static void main(String[] args) {

        GeoLoc geoLoc = getMyGeoLocation(30.071431,31.020601, .050, 0.05);
        System.out.println(geoLoc.toString());

    }

    static boolean myFlag = false;

    public static GeoLoc getMyGeoLocation(double latitude, double longitude, double x, double y) {

        GeoLoc geoLoc = null;
        CustomGeoLocation geoLocation = null;
        if (myFlag == false) 
        {
            geoLocation = CustomGeoLocation.fromDegrees(latitude, longitude, y);
            geoLoc = new GeoLoc(new CustomCoordinateHelper(geoLocation.boundingCoordinates()).getMinLat(), longitude);
            myFlag=true;
            return getMyGeoLocation(geoLoc.getLat(), longitude, x, y);
        }
        geoLocation = CustomGeoLocation.fromDegrees(latitude, longitude, x);
        return new GeoLoc(latitude, new CustomCoordinateHelper(geoLocation.boundingCoordinates()).getMaxLon());
    }

}
