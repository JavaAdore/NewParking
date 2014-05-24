/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapapplet;

import java.awt.Button;

import javax.swing.JButton;
import javax.swing.JLabel;

public class DoorButton
        extends JButton {

    int garageId = 8;

    public int getGarageId() {
        return garageId;
    }

    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }
    int doorId = 1;
    String doorName;
    double doorX = 0;
    double doorY = 0;
    double lon;
    double lat;

    public DoorButton(String doorName, double lat, double lon) {
        setDoorId(doorId);
        setDoorName(doorName);
        setLon(lon);
        setLat(lat);
    }

    public DoorButton() {
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorX(double doorX) {
        this.doorX = doorX;
    }

    public double getDoorX() {
        return doorX;
    }

    public void setDoorY(double doorY) {
        this.doorY = doorY;
    }

    public double getDoorY() {
        return doorY;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLon() {
        return lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setDoorId(int doorId) {
        this.doorId = doorId;
    }

    public int getDoorId() {
        return doorId;
    }
}