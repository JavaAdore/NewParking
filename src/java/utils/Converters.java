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

    public static double MeterToPixels(double meters) {

        return meters * 3779.527559055;
    }

    public static double KiloMetersToPixels(double kiloMeters) {

        return kiloMeters * 1000 * 3779.527559055;
    }

    public static double pixelsToMeter(double pixels) {

        return pixels * (1 / 3779.527559055);
    }

    public static double pixelsToKiloMeter(double pixels) 
    {
        return (pixels / 1000) * (1 / 3779.527559055);
    }

    public static void main(String[] args) {

    GeoLoc geoLoc = getMyGeoLocation(30.071362,31.020893, 0 , .050);
    System.out.println(geoLoc.toString());
    
    }

    public static  GeoLoc getMyGeoLocation(double latitude, double longitude, double x, double y) {
            CustomCoordinateHelper coordinateHelper;
            CustomGeoLocation customGeoLocation;
            customGeoLocation = CustomGeoLocation.fromDegrees(latitude, longitude, y);
            CustomGeoLocation[] boundingCoordinates = customGeoLocation.boundingCoordinates();
            coordinateHelper = new CustomCoordinateHelper(boundingCoordinates);
            //customGeoLocation = CustomGeoLocation.fromDegrees(coordinateHelper.getMinLat(),longitude, x);
            coordinateHelper = new CustomCoordinateHelper(customGeoLocation.boundingCoordinates());
            return new GeoLoc(coordinateHelper.getMinLat(),longitude);
        

    }

}
