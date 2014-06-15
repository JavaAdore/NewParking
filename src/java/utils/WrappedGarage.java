/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


/**
 *
 * @author orcl
 */
public class WrappedGarage {

    int garageId;

    String GarageName;
    int numberOfBusySlots;
    int numerOfAvailableSlots;
    double latitude;
    double lontitude;

    public WrappedGarage(String GarageName, int numberOfBusySlots, int numerOfAvailableSlots, double latitude, double lontitude) {
        this.GarageName = GarageName;
        this.numberOfBusySlots = numberOfBusySlots;
        this.numerOfAvailableSlots = numerOfAvailableSlots;
        this.latitude = latitude;
        this.lontitude = lontitude;
    }

    public WrappedGarage(int garageId, String GarageName, int numberOfBusySlots, int numerOfAvailableSlots, double latitude, double lontitude) {
        this.garageId = garageId;
        this.GarageName = GarageName;
        this.numberOfBusySlots = numberOfBusySlots;
        this.numerOfAvailableSlots = numerOfAvailableSlots;
        this.latitude = latitude;
        this.lontitude = lontitude;
    }

    public int getGarageId() {
        return garageId;
    }

    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLontitude() {
        return lontitude;
    }

    public void setLontitude(double lontitude) {
        this.lontitude = lontitude;
    }

    public WrappedGarage() {
    }

    public String getGarageName() {
        return GarageName;
    }

    public void setGarageName(String GarageName) {
        this.GarageName = GarageName;
    }

    public int getNumberOfBusySlots() {
        return numberOfBusySlots;
    }

    public void setNumberOfBusySlots(int numberOfBusySlots) {
        this.numberOfBusySlots = numberOfBusySlots;
    }

    public int getNumerOfAvailableSlots() {
        return numerOfAvailableSlots;
    }

    public void setNumerOfAvailableSlots(int numerOfAvailableSlots) {
        this.numerOfAvailableSlots = numerOfAvailableSlots;
    }

}
