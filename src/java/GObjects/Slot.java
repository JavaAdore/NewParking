/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author orcl
 */
public class Slot implements Marker {

    String slotName;
    int x;
    int y;
    HashMap<Integer, ValueContainer> totalDailyConsumedHours;
    HashMap<Integer, ValueContainer> totalMonthlyConsumedHours;
    HashMap<Integer, ValueContainer> totalYearlyConsumedHours;

    List<Step> path;

    public Slot() {
    }

    public Slot(String slotName, int x, int y, List<Step> path, HashMap<Integer, ValueContainer> totalDailyConsumedHours, HashMap<Integer, ValueContainer> totalMonthlyConsumedHours, HashMap<Integer, ValueContainer> totalYearlyConsumedHours) {
        this.slotName = slotName;
        this.x = x;
        this.y = y;
        this.path = path;
        this.totalDailyConsumedHours = totalDailyConsumedHours;
        this.totalMonthlyConsumedHours = totalMonthlyConsumedHours;
        this.totalYearlyConsumedHours = totalYearlyConsumedHours;
    }

    public HashMap<Integer, ValueContainer> getTotalDailyConsumedHours() {
        return totalDailyConsumedHours;
    }

    public void setTotalDailyConsumedHours(HashMap<Integer, ValueContainer> totalDailyConsumedHours) {
        this.totalDailyConsumedHours = totalDailyConsumedHours;
    }

    public HashMap<Integer, ValueContainer> getTotalMonthlyConsumedHours() {
        return totalMonthlyConsumedHours;
    }

    public void setTotalMonthlyConsumedHours(HashMap<Integer, ValueContainer> totalMonthlyConsumedHours) {
        this.totalMonthlyConsumedHours = totalMonthlyConsumedHours;
    }

    public HashMap<Integer, ValueContainer> getTotalYearlyConsumedHours() {
        return totalYearlyConsumedHours;
    }

    public void setTotalYearlyConsumedHours(HashMap<Integer, ValueContainer> totalYearlyConsumedHours) {
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

    @Override
    public String getMarker() {
        return getSlotName();
    }

    @Override
    public boolean equals(Object obj) 
    {
        Slot slot = (Slot) obj;
        return ((((Slot) obj).getX() == getX()) && (((Slot) obj).getY() == getY()));
    }
}
