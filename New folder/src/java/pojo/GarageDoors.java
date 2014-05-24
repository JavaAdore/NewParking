/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import GObjects.Marker;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author orcl
 */
@Entity
public class GarageDoors implements Serializable, Marker {

    // prorties 
    @Id
    @GeneratedValue
    int doorId;
    @ManyToOne
    Garage garage;
    String doorName;
    double lon;
    double lat;
    int x;
    int y;

    public GarageDoors(String doorName, Garage garage) {
        this.doorName = doorName;
        this.garage = garage;
    }

    public GarageDoors() {
    }

    public GarageDoors(int doorId) {
        this.doorId = doorId;
    }

    public GarageDoors(String doorName) {
        this.doorName = doorName;
    }

    public GarageDoors(Garage garage, String doorName) {
        this.garage = garage;
        this.doorName = doorName;
    }

    public GarageDoors(int doorId, int garageId, String doorName) {
        this.doorId = doorId;
        this.garage = new Garage(garageId);
        this.doorName = doorName;

    }

    public GarageDoors(int doorId, Garage garage, String doorName, double lon, double lat) {
        this.doorId = doorId;
        this.garage = garage;
        this.doorName = doorName;
        this.lon = lon;
        this.lat = lat;
    }

    public GarageDoors(int garageId, String doorName, int x, int y, double lon, double lat) {

        this.garage = new Garage(garageId);
        this.doorName = doorName;
        this.lon = lon;
        this.lat = lat;
        this.x = x;
        this.y = y;
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

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getDoorId() {
        return doorId;
    }

    public void setDoorId(int doorId) {
        this.doorId = doorId;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }

    @Override
    public String getMarker() {
        return getDoorName();
    }
}
