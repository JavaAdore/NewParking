/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author orcl
 */
public class Garage implements Serializable{

    int garageId;
    List<Door> doors = new ArrayList<>();

    public Garage() {
    }

    public Garage(int garageId) {
        this.garageId = garageId;
    }

    public int getGarageId() {
        return garageId;
    }

    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }

    public List<Door> getDoors() {
        return doors;
    }

    public void setDoors(ArrayList<Door> doors) {
        this.doors = doors;
    }

}
