/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapapplet;

import DAOS.GarageSlotDoorsImp;
import java.util.ArrayList;
import java.util.HashMap;
import pojo.GarageDoors;
import pojo.GarageSlotsDoors;
import pojo.GarageStatus;

/**
 *
 * @author orcl
 */
public class AppletHandler {

    HashMap< String, DoorButton> doors = new HashMap<>();
    HashMap< Integer, SlotButton> slots = new HashMap<>();
    HashMap< String, MyObject> paths = new HashMap<>();

    public AppletHandler( int garageId ,HashMap< String, DoorButton> doors, HashMap< Integer, SlotButton> slots, HashMap< String, MyObject> paths) {
        setDoors(doors);
        setPaths(paths);
        setSlots(slots);
        doYourJobDear(garageId);
    }

    public HashMap<String, DoorButton> getDoors() {
        return doors;
    }

    public void setDoors(HashMap<String, DoorButton> doors) {
        this.doors = doors;
    }

    public HashMap<Integer, SlotButton> getSlots() {
        return slots;
    }

    public void setSlots(HashMap<Integer, SlotButton> slots) {
        this.slots = slots;
    }

    public HashMap<String, MyObject> getPaths() {
        return paths;
    }

    public void setPaths(HashMap<String, MyObject> paths) {
        this.paths = paths;
    }

    private void doYourJobDear(int garageId) {
        ArrayList<GarageDoors> doorsList = Converter.getDoorsList(doors);
        ArrayList<GarageStatus> slotsList = Converter.getSlotsList(slots);
        ArrayList<GarageSlotsDoors> paths = Converter.getPathsList(getPaths());
        
        // should be the webService i think
        
         //GarageSlotDoorsImp.getInstance().handleAgarage(garageId, doorsList,slotsList,paths);
    }
}
