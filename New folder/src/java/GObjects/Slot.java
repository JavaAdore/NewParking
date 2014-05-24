/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author orcl
 */
public class Slot implements Marker, Serializable {

    String slotName;
    int x;
    int y;
    HashMap<Date, ValueContainer> totalDailyConsumedHours;
    HashMap<Date, ValueContainer> totalMonthlyConsumedHours;
    HashMap<Date, ValueContainer> totalYearlyConsumedHours;

    List<Step> path = new ArrayList<>();

    public Slot() {
    }

    public Slot(String slotName, int x, int y) {

        this.slotName = slotName;

        this.x = x;

        this.y = y;

    }

    public Slot(String slotName, int x, int y, List<Step> path, HashMap<Date,ValueContainer> totalDailyConsumedHours, HashMap<Date,ValueContainer> totalMonthlyConsumedHours, HashMap<Date,ValueContainer> totalYearlyConsumedHours) {
        this.slotName = slotName;
        this.x = x;
        this.y = y;
        this.path = path;
        this.totalDailyConsumedHours = totalDailyConsumedHours;
        this.totalMonthlyConsumedHours = totalMonthlyConsumedHours;
        this.totalYearlyConsumedHours = totalYearlyConsumedHours;
    }

    public HashMap<Date,ValueContainer> getTotalDailyConsumedHours() {
        return totalDailyConsumedHours;
    }

    public void setTotalDailyConsumedHours(HashMap<Date,ValueContainer> totalDailyConsumedHours) {
        this.totalDailyConsumedHours = totalDailyConsumedHours;
    }

    public HashMap<Date,ValueContainer> getTotalMonthlyConsumedHours() {
        return totalMonthlyConsumedHours;
    }

    public void setTotalMonthlyConsumedHours(HashMap<Date,ValueContainer> totalMonthlyConsumedHours) {
        this.totalMonthlyConsumedHours = totalMonthlyConsumedHours;
    }

    public HashMap<Date,ValueContainer> getTotalYearlyConsumedHours() {
        return totalYearlyConsumedHours;
    }

    public void setTotalYearlyConsumedHours(HashMap<Date,ValueContainer> totalYearlyConsumedHours) {
        this.totalYearlyConsumedHours = totalYearlyConsumedHours;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
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

    public List<Step> getPath() {
        return path;
    }

    public void setPath(ArrayList<Step> path) {
        this.path = path;
    }

    public void addStep(Step step) {

        path.add(step);

    }

    public void clearPath() {

        path.clear();
    }

    @Override
    public String getMarker() {
        return getSlotName();
    }

    @Override
    public boolean equals(Object obj) {
        Slot slot = (Slot) obj;
        return ((((Slot) obj).getX() == getX()) && (((Slot) obj).getY() == getY()));
    }
}
