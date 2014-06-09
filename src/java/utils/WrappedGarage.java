/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.math.BigDecimal;

/**
 *
 * @author orcl
 */
public class WrappedGarage {

    int garageId;

    String GarageName;
    int numberOfBusySlots;
    int numerOfAvailableSlots;
    BigDecimal latitude;
    BigDecimal lontitude;

    public WrappedGarage(String GarageName, int numberOfBusySlots, int numerOfAvailableSlots, BigDecimal latitude, BigDecimal lontitude) {
        this.GarageName = GarageName;
        this.numberOfBusySlots = numberOfBusySlots;
        this.numerOfAvailableSlots = numerOfAvailableSlots;
        this.latitude = latitude;
        this.lontitude = lontitude;
    }

    public WrappedGarage(int garageId, String GarageName, int numberOfBusySlots, int numerOfAvailableSlots, BigDecimal latitude, BigDecimal lontitude) {
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

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLontitude() {
        return lontitude;
    }

    public void setLontitude(BigDecimal lontitude) {
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
