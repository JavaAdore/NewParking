package utils;

public class CustomGeoLocation implements GeoLocationInt {

    private double radLat;  // latitude in radians
    private double radLon;  // longitude in radians
    private double degLat;  // latitude in degrees
    private double degLon;  // longitude in degrees
    private static final double MIN_LAT = Math.toRadians(-90d);  // -PI/2
    private static final double MAX_LAT = Math.toRadians(90d);   //  PI/2
    private static final double MIN_LON = Math.toRadians(-180d); // -PI
    private static final double MAX_LON = Math.toRadians(180d);  //  PI

    private static double distance;

    private CustomGeoLocation() {
    }

    /**
     * @param latitude the latitude, in degrees.
     * @param longitude the longitude, in degrees.
     * @param d
     * @return
     */
    public static CustomGeoLocation fromDegrees(double latitude, double longitude, double d) {
        CustomGeoLocation result = new CustomGeoLocation();
        result.radLat = Math.toRadians(latitude);
        result.radLon = Math.toRadians(longitude);
        result.degLat = latitude;
        result.degLon = longitude;
        distance = d;
        result.checkBounds();

        return result;
    }

    /**
     * @param latitude the latitude, in radians.
     * @param longitude the longitude, in radians.
     */
    public static CustomGeoLocation fromRadians(double latitude, double longitude, double d) {
        CustomGeoLocation result = new CustomGeoLocation();
        result.radLat = latitude;
        result.radLon = longitude;
        result.degLat = Math.toDegrees(latitude);
        result.degLon = Math.toDegrees(longitude);
        distance = d;

        result.checkBounds();
        return result;
    }

    public CustomGeoLocation(double latitude, double longitude) {
        degLat = Math.toDegrees(latitude);
        degLon = Math.toDegrees(longitude);
    }

    private void checkBounds() {
        if (radLat < MIN_LAT || radLat > MAX_LAT
                || radLon < MIN_LON || radLon > MAX_LON) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @return the latitude, in degrees.
     */
    public double getLatitudeInDegrees() {
        return degLat;
    }

    /**
     * @return the longitude, in degrees.
     */
    public double getLongitudeInDegrees() {
        return degLon;
    }

    /**
     * @return the latitude, in radians.
     */
    public double getLatitudeInRadians() {
        return radLat;
    }

    /**
     * @return the longitude, in radians.
     */
    public double getLongitudeInRadians() {
        return radLon;
    }

    @Override
    public String toString() {
        return "(" + degLat + "\u00B0, " + degLon + "\u00B0) = ("
                + radLat + " rad, " + radLon + " rad)";
    }

    public double distanceTo(CustomGeoLocation location, double radius) {
        return Math.acos(Math.sin(radLat) * Math.sin(location.radLat)
                + Math.cos(radLat) * Math.cos(location.radLat)
                * Math.cos(radLon - location.radLon)) * radius;
    }

    public CustomGeoLocation[] boundingCoordinates(double distance, double radius) {
        double minLon, maxLon, minLat, maxLat;
        if (radius < 0d || distance < 0d) {
            throw new IllegalArgumentException();
        }

        // angular distance in radians on a great circle
        double radDist = distance / radius;

        minLat = radLat - radDist;
        maxLat = radLat + radDist;

        if (minLat > MIN_LAT && maxLat < MAX_LAT) {
            double deltaLon = Math.asin(Math.sin(radDist)
                    / Math.cos(radLat));
            minLon = radLon - deltaLon;
            if (minLon < MIN_LON) {
                minLon += 2d * Math.PI;
            }
            maxLon = radLon + deltaLon;
            if (maxLon > MAX_LON) {
                maxLon -= 2d * Math.PI;
            }
        } else {
            // a pole is within the distance
            minLat = Math.max(minLat, MIN_LAT);
            maxLat = Math.min(maxLat, MAX_LAT);
            minLon = MIN_LON;
            maxLon = MAX_LON;
        }

        return new CustomGeoLocation[]{
            new CustomGeoLocation(minLat, minLon),
            new CustomGeoLocation(maxLat, maxLon),
            new CustomGeoLocation(minLat, maxLon),
            new CustomGeoLocation(maxLat, minLon)
        };
    }

    public CustomGeoLocation[] boundingCoordinates() {
        return boundingCoordinates(distance, 6378.1);
    }
}
