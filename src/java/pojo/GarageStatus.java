package pojo;
// Generated Apr 3, 2014 7:53:49 PM by Hibernate Tools 3.2.1.GA

import GObjects.Marker;
import GObjects.ValueContainer;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import utils.Utils;

/**
 * GarageStatus generated by hbm2java
 */
@Entity
@NamedNativeQuery(name = "update_Garage_status", query = "CALL update_consumed_hours(:slotid)")
public class GarageStatus implements java.io.Serializable, Marker {

    @Id
    @GeneratedValue
    private int slotId;
    private String slotName;
    @ManyToOne()
    private Garage garage;
    private int status;
    @Temporal(TemporalType.TIMESTAMP )
    private Date arrivalTime = new Timestamp(new Date().getTime());
    private double consumedHours = 0;
    int x;
    int y;
    double lat;
    double lon;
    int enabled=1;

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
    
    @OneToMany(mappedBy = "slotId", orphanRemoval = true)
    @ElementCollection(fetch = FetchType.EAGER)
    Set<DailyHistory> dailyHistory = new HashSet<>();
    @OneToMany(mappedBy = "slotId", orphanRemoval = true)
    @ElementCollection(fetch = FetchType.EAGER)
    Set<MonthlyHistory> monthlyHistory = new HashSet<>();
    @OneToMany(mappedBy = "slotId", orphanRemoval = true)
    @ElementCollection(fetch = FetchType.EAGER)
    Set<YearlyHistory> yearlyHistorys = new HashSet<>();

    public GarageStatus() {
    }

    public GarageStatus(int slotId) {
        this.slotId = slotId;
    }

    public GarageStatus(int slotId, int garageId) {
        this.slotId = slotId;
        this.garage = new Garage(garageId);
        this.slotName = slotId + "-" + garageId;
    }

    public GarageStatus(String slotName, int x, int y, Garage garage) {
        this.slotName = slotName;
        this.x = x;
        this.y = y;
        this.garage = garage;
    }

    public GarageStatus(String slotName) {
        this.slotName = slotName;
    }

    public GarageStatus(String slotName, Garage garage, int slotId, int status) {
        this.slotName = slotName;
        this.garage = garage;
        this.slotId = slotId;
        this.status = status;

    }

    public GarageStatus(String slotName, Garage garage, int slotId, int status, int x, int y) {
        this.slotName = slotName;
        this.garage = garage;
        this.slotId = slotId;
        this.status = status;
        this.x = x;
        this.y = y;

    }

    public GarageStatus(String slotName, int garageId, int x, int y,double lat , double lon) {
        this.slotName = slotName;
        this.garage = new Garage(garageId);
        this.x = x;
        this.y = y;
        this.lat= lat;
        this.lon= lon;

    }

    public double getConsumedHours() {
        return consumedHours;
    }

    public void setConsumedHours(double consumedHours) {
        this.consumedHours = consumedHours;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getSlotName() {
        return this.slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public Garage getGarage() {
        return this.garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public int getSlotId() {
        return this.slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getArrivalTime() {
        return this.arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Set<DailyHistory> getDailyHistory() {
        return dailyHistory;
    }

    public void setDailyHistory(Set<DailyHistory> dailyHistory) {
        this.dailyHistory = dailyHistory;
    }

    public Set<MonthlyHistory> getMonthlyHistory() {
        return monthlyHistory;
    }

    public void setMonthlyHistory(Set<MonthlyHistory> monthlyHistory) {
        this.monthlyHistory = monthlyHistory;
    }

    public Set<YearlyHistory> getYearlyHistorys() {
        return yearlyHistorys;
    }

    public void setYearlyHistorys(Set<YearlyHistory> yearlyHistorys) {
        this.yearlyHistorys = yearlyHistorys;
    }

    @Override
    public String getMarker() {
        return getSlotId() + "";
    }

    public HashMap<Date, ValueContainer>  getTotalDailyConsumedHours() {
        return Utils.getTotalConsumedHours(getDailyHistory());

    }

    public HashMap<Date, ValueContainer>  getTotalMonthlyConsumedHours() {
        return Utils.getTotalConsumedHours(getMonthlyHistory());

    }

    public HashMap<Date, ValueContainer>  getTotalYearlyConsumedHours() 
    {
        return Utils.getTotalConsumedHours(getYearlyHistorys());
    }
}
