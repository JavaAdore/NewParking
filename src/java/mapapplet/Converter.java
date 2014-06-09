/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapapplet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import pojo.GarageDoors;
import pojo.GarageSlotsDoors;
import pojo.GarageStatus;

/**
 *
 * @author orcl
 */
public class Converter {

    public static ArrayList<GarageDoors> getDoorsList(HashMap< String, DoorButton> garageDoors) {
        ArrayList<GarageDoors> doors = new ArrayList<GarageDoors>();
        for (DoorButton doorButton : garageDoors.values()) {
            doors.add(new GarageDoors(doorButton.getDoorId(), doorButton.getGarageId(), doorButton.getDoorName()));

        }
        return doors;

    }

    public static ArrayList<GarageStatus> getSlotsList(HashMap< Integer, SlotButton> garageSlots) {
        ArrayList<GarageStatus> slots = new ArrayList<>();
        for (SlotButton garageSlot : garageSlots.values()) {
            System.out.println("Slot Section");
            System.out.println(garageSlot.getSlotId() + " " + garageSlot.getGarageId());
            slots.add(new GarageStatus(garageSlot.getSlotId(), garageSlot.getGarageId()));
        }
        return slots;

    }

    public static ArrayList<GarageSlotsDoors> getPathsList(HashMap< String, MyObject> garagePaths) {
        ArrayList<GarageSlotsDoors> slotDoorPoints = new ArrayList<GarageSlotsDoors>();
        for (MyObject myObject : garagePaths.values()) {
            System.out.println(myObject.getSlotId() + " ----------------- " + myObject.getDoorId());

//            GarageSlotsDoors slotPoint = new GarageSlotsDoors(myObject.getSlotId(), myObject.getDoorId());
//            slotPoint.setPoints(prepareMeAPathPlease(myObject.getPoints()));
//            System.out.println("Door Id " + slotPoint.getId().getDoorId());
//            System.out.println("Slot Id " + slotPoint.getId().getSlotId());
//            System.out.println("path is "+ slotPoint.getPoints());
//            slotDoorPoints.add(slotPoint);
        }
        return slotDoorPoints;
    }

    public static String prepareMeAPathPlease(ArrayList<PointButton> pointButtons) {

        JsonArray theArray = new JsonArray();
        for (PointButton p : pointButtons) {
            theArray.add(ConvertStepToJson(new Step(p.getOrder(), p.getX(), p.getY())));
        }
        System.out.println(theArray);
        return theArray.toString();
    }

    public static JsonObject ConvertStepToJson(Step step) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("stepOrder", step.getId());
        jsonObject.addProperty("x", step.getX());
        jsonObject.addProperty("y", step.getY());
        return jsonObject;

    }
}
