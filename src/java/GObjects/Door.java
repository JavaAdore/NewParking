/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GObjects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author orcl
 */
public class Door implements Marker {

    int doorId;
    String doorName;
    List<Slot> slots = new ArrayList<Slot>();
    double lat;
    double lon;
    int x;
    int y;

    public int getDoorId() {
        return doorId;
    }

    public void setDoorId(int doorId) {
        this.doorId = doorId;
    }

    public Door() {
    }

    public Door(int doorId, String doorName, int x, int y, double lat, double lon) {
        this.doorId = doorId;
        this.doorName = doorName;
        this.lat = lat;
        this.lon = lon;
        this.x = x;
        this.y = y;
    }

    public Door(String doorName) {

        this.doorName = doorName;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
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

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<Slot> slots) {
        this.slots = slots;
    }

    @Override
    public String getMarker() {
        return getDoorName();
    }

}
