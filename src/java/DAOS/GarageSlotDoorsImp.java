/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import GObjects.Door;
import GObjects.Marker;
import GObjects.Slot;
import GObjects.Step;
import Sessions.ConnectionHandler;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import pojo.*;
import utils.Converters;
import utils.GeoLoc;
import utils.HaversineAlgorithm;
import utils.Utils;

/**
 *
 * @author orcl
 */
public class GarageSlotDoorsImp {
    
    Session garageSlotDoorsSession = ConnectionHandler.getGarageSlotDoorsSession();
    private static GarageSlotDoorsImp instance;
    
    private GarageSlotDoorsImp() {
    }
    
    public static GarageSlotDoorsImp getInstance() {
        
        if (instance == null) {
            instance = new GarageSlotDoorsImp();
        }
        return instance;
    }

    // add slot
    public GarageStatus addGarageSlot(GarageStatus newSlot) {
        try {
            
            garageSlotDoorsSession.persist(newSlot);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            //    garageSlotDoorsSession.getTransaction().rollback();
            // leave current slot null as it's 
        } finally {
            //   garageSlotDoorsSession.getTransaction().commit();
        }
        
        return newSlot;
    }

    // add door
    //will be called within session
    public GarageDoors addGarageDoor(GarageDoors door) {
        try {
            garageSlotDoorsSession.persist(door);
        } catch (Exception ex) {
            ex.printStackTrace();
            //  garageSlotDoorsSession.getTransaction().rollback();
            // leaev garagedoor is null as it's 
        }
        return door;
        
    }

//
//    public int addGarageDoor(ArrayList<GarageDoors> garageDoors, int garageId) {
//        int result = 0;
//        try {
//            garageSlotDoorsSession.clear();
//            garageSlotDoorsSession.beginTransaction();
//            for (GarageDoors garageDoor : garageDoors) {
//
//                garageSlotDoorsSession.save(garageDoor);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            garageSlotDoorsSession.getTransaction().rollback();
//            result = -1;
//        } finally {
//            garageSlotDoorsSession.getTransaction().commit();
//        }
//
//        return result;
//    }
//    public int addGarageSlot(ArrayList<GarageStatus> garageSlot) {
//        int result = 0;
//        try {
//            garageSlotDoorsSession.clear();
//
//            garageSlotDoorsSession.beginTransaction();
//            for (GarageStatus garageStatus : garageSlot) {
//                System.out.println("slotId = " + garageStatus.getSlotId());
//                garageSlotDoorsSession.save(garageStatus);
//
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            garageSlotDoorsSession.getTransaction().rollback();
//            result = -1;
//        } finally {
//            garageSlotDoorsSession.getTransaction().commit();
//        }
//
//        return result;
//    }
    public int addpath(int slotId, int doorId, String path) {
        int result = 0;
        try {
            garageSlotDoorsSession.clear();
            
            GarageSlotsDoors garageSlotsDoors = new GarageSlotsDoors(new GarageStatus(slotId), new GarageDoors(doorId), path);
            garageSlotDoorsSession.save(garageSlotsDoors);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            result = -1;
        } finally {
        }
        
        return result;
    }
    
    public int addpath(String slotName, String doorName, String path) {
        return addpath(getSlotId(slotName).getSlotId(), getDoorId(doorName).getDoorId(), path);
        
    }

//    public int addpath(ArrayList<GarageSlotsDoors> paths) {
//        int result = 0;
//        try {
//            garageSlotDoorsSession.clear();
//
//            garageSlotDoorsSession.beginTransaction();
//            for (GarageSlotsDoors currentGarageSlotDoor : paths) {
//
//                GarageSlotsDoors temp = new GarageSlotsDoors(currentGarageSlotDoor.getId().getSlotId().getSlotId(), currentGarageSlotDoor.getId().getDoorId().getDoorId());
//                temp.setPoints(currentGarageSlotDoor.getPoints());
//                System.out.println("Door " + currentGarageSlotDoor.getId().getDoorId().getDoorId());
//                System.out.println("slot " + currentGarageSlotDoor.getId().getSlotId().getSlotId());
//                garageSlotDoorsSession.save(temp);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            garageSlotDoorsSession.getTransaction().rollback();
//            result = -1;
//        } finally {
//            garageSlotDoorsSession.getTransaction().commit();
//        }
//
//        return result;
//    }
    GarageStatus getSlotId(String slotName) {
        
        GarageStatus garageStatus = null;
        try {
            garageSlotDoorsSession.clear();
            
            Query q = garageSlotDoorsSession.createQuery("from GarageStatus where upper(slotName) like :slotName");
            q.setParameter("slotName", slotName.toUpperCase());
            garageStatus = (GarageStatus) q.uniqueResult();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            
        } finally {
        }
        return garageStatus;
        
    }
    
    private GarageDoors getDoorId(String doorName) {
        GarageDoors garageDoor = null;
        try {
            garageSlotDoorsSession.clear();
            
            Query q = garageSlotDoorsSession.createQuery("from GarageDoors where upper(doorName) like :doorName");
            q.setParameter("doorName", doorName.toUpperCase());
            garageDoor = (GarageDoors) q.uniqueResult();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            
        } finally {
        }
        return garageDoor;
    }

//     ArrayList<GarageDoors> doorsList = Converter.getDoorsList(doors);
//        ArrayList<GarageStatus> slotsList = Converter.getSlotsList(slots);
//        ArrayList<SlotDoorPoints> paths = Converter.getPathsList(getPaths());
//    public void handleAgarage(int garageId, ArrayList<GarageDoors> doorsList, ArrayList<GarageStatus> slotsList, ArrayList<GarageSlotsDoors> paths) {
//
////        addGarageDoor(doorsList, garageId);
//        addGarageSlot(slotsList);
//        addpath(paths);
//
//    }
    public int handleThisGaragePlease(GObjects.Garage garage) {
        int result = 0;
        try {
            
            deletingGarageData(garage.getGarageId());
            garageSlotDoorsSession.beginTransaction();
            
            HashMap<String, GarageStatus> slots = new HashMap<>();
            Garage garage1 = (Garage) garageSlotDoorsSession.get(Garage.class, garage.getGarageId());
            garage1.setSlotWidth(garage.getSlotWidth());
            garage1.setSlotHeight(garage.getSlotHeight());
            
            for (GObjects.Door door : garage.getDoors()) {
                GarageDoors tempDoor = new GarageDoors(garage.getGarageId(), door.getDoorName(), door.getX(), door.getY(), door.getLon(), door.getLat());
                tempDoor = addGarageDoor(tempDoor);
                if (tempDoor != null) {
                    for (GObjects.Slot slot : door.getSlots()) {
                        GarageSlotsDoors path;
                        GarageStatus currentSlot = null;
                        if (!slotIsAddedBefore(slot, slots)) {
                            GeoLoc myGeoLocation = Converters.getMyGeoLocation(garage1.getLat(), garage1.getLon(), (slot.getX() * 0.002154195011337868), (slot.getY() * 0.000204082));
                            currentSlot = new GarageStatus(slot.getSlotName(), garage.getGarageId(), slot.getX(), slot.getY(), myGeoLocation.getLat(), myGeoLocation.getLon());
                            currentSlot = addGarageSlot(currentSlot);
                            
                            if (currentSlot != null) {
                                slots = addToHashMap(currentSlot, slots);
                            }
                        } else {
                            currentSlot = getSlot(slot, slots);
                        }
                        path = new GarageSlotsDoors(currentSlot, tempDoor, utils.Utils.prepareMeAPathPlease((ArrayList<Step>) slot.getPath()));
                        addNewPath(path);
                        
                    }
                }
            }
            garageSlotDoorsSession.getTransaction().commit();
            
        } catch (Exception ex) {
            result = -1;
            ex.printStackTrace();
            
        }
        
        return result;
    }
    
    public boolean slotIsAddedBefore(Slot slot, HashMap slots) {
        
        return slots.containsKey(slot.getX() + "-" + slot.getY());
    }
    
    public GarageStatus getSlot(Slot slot, HashMap slots) {
        
        return (GarageStatus) slots.get(slot.getX() + "-" + slot.getY());
    }

    /**
     * ************************************************************************************
     */
    /* 
     public int handleThisGaragePlease(GObjects.Garage garage) {
     int result = 0;
     try {
     beginTransaction();
     deleteOldDoorsAndSlots(garage.getGarageId());
     for (GObjects.Door door : garage.getDoors()) {
     GarageDoors tempDoor = new GarageDoors(garage.getGarageId(), door.getDoorName(), door.getX(), door.getY(), door.getLon(), door.getLat());
     int currentDoor = addGarageDoor(tempDoor);
     for (GObjects.Slot slot : door.getSlots()) {
     GarageStatus garageStatus = new GarageStatus(slot.getSlotName(), garage.getGarageId(), slot.getX(), slot.getY());
     int currentSlot = addGarageSlot(garageStatus);
     GarageSlotsDoors path = new GarageSlotsDoors(currentSlot, currentDoor, utils.Utils.prepareMeAPathPlease((ArrayList<Step>) slot.getPath()));
     addNewPath(path);

     }
     }

     } catch (Exception ex) {
     result = -1;
     ex.printStackTrace();

     } finally {

     commit();

     }

     return result;
     }
     */
    /**
     * ************************************************************************************
     */
    private void addNewPath(GarageSlotsDoors path) {
        try {
            
            garageSlotDoorsSession.persist(path);
            
        } catch (Exception ex) {
            
            ex.printStackTrace();
        }
    }
    
    public Transaction beginTransaction() {
        
        return garageSlotDoorsSession.beginTransaction();
    }
    
    public void commit() {
        
    }

//    private void deleteOldDoorsAndSlots(int garageId) {
//        try {
//            Query deleteData = garageSlotDoorsSession.createQuery("delete from GarageDoors where garage  =:garage");
//
//            deleteData.setParameter("garage", new Garage(garageId));
//
//            deleteData.executeUpdate();
//            deleteData = garageSlotDoorsSession.createQuery("delete from GarageStatus where garage  =:garage");
//
//            deleteData.setParameter("garage", new Garage(garageId));
//
//            deleteData.executeUpdate();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//
//        }
//
//    }
    public GObjects.Garage generateGarageObject(int garageId) {
        
        GObjects.Garage garage = null;
        try {
            
            Garage myGarage = GarageImp.getInstance().getGarage(garageId);
            Collection<GarageStatus> slots = myGarage.getGarageStatus();
            Collection<GarageDoors> doors = myGarage.getGarageDoors();
            Query q = garageSlotDoorsSession.createQuery("from GarageSlotsDoors where slotId  in  :slots and doorId in :doors");
            q.setParameterList("slots", slots);
            q.setParameterList("doors", doors);
            Collection<GarageSlotsDoors> garageSlotsDoors = q.list();
            
            garage = new GObjects.Garage(garageId);
            
            ArrayList<GObjects.Door> tempDoors = new ArrayList<GObjects.Door>();
            ArrayList<GObjects.Slot> tempSlot = new ArrayList<GObjects.Slot>();
            Door currentDoor;
            Slot currentSlot;
            
            for (GarageSlotsDoors garageSlotsDoor : garageSlotsDoors) {
                GarageDoors g = garageSlotsDoor.getDoorId();
                GarageStatus s = garageSlotsDoor.getSlotId();
                
                if (!contains(tempDoors, garageSlotsDoor.getDoorId().getDoorName())) {
                    currentDoor = new Door(g.getDoorId(), g.getDoorName(), g.getX(), g.getY(), g.getLat(), g.getLon());
                    tempDoors.add(currentDoor);
                    
                } else {
                    currentDoor = getElement(tempDoors, g.getDoorName());
                }
                if (!contains(tempSlot, garageSlotsDoor.getSlotId().getSlotName())) {
                    currentSlot = new Slot(s.getSlotName(), s.getX(), s.getY(), preparePathArray(garageSlotsDoor.getPoints()), s.getTotalDailyConsumedHours(), s.getTotalMonthlyConsumedHours(), s.getTotalYearlyConsumedHours());
                    tempSlot.add(currentSlot);
                    
                } else {
                    currentSlot = getElement(tempSlot, s.getSlotName());
                }
                
                currentDoor.getSlots().add(currentSlot);
                garage.setDoors(tempDoors);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            garageSlotDoorsSession.getTransaction().rollback();
            
        }
        return garage;
    }
    
    public List<SlotDoorId> getGaraegSlots(ArrayList<GarageDoors> doors, ArrayList<GarageStatus> slots) {
        List<SlotDoorId> result = new ArrayList<SlotDoorId>();
        for (GarageDoors door : doors) {
            for (GarageStatus slot : slots) {
                result.add(new SlotDoorId(slot.getSlotId(), door.getDoorId()));
            }
        }
        return result;
    }
    
    public <T extends Marker> boolean contains(ArrayList<T> list, Object obj) {
        
        for (T door : list) {
            if (door.getMarker().equals(obj.toString())) {
                return true;
            }
            
        }
        return false;
    }
    
    public static void main(String[] args) {
        
    }
    
    private <T extends Marker> T getElement(ArrayList<T> elements, String marker) {
        
        for (T x : elements) {
            if (x.getMarker().equals(marker)) {
                return x;
            }
            
        }
        return null;
    }
    
    private List<Step> preparePathArray(String points) {
        List<Step> steps = new ArrayList<Step>();
        try {
            JSONParser jsonParser = new JSONParser();
            Object myObject = jsonParser.parse(points);
            JSONArray jSONArray = (JSONArray) myObject;
            Gson gson = new Gson();
            for (int i = 0; i < jSONArray.size(); i++) {
                
                steps.add(gson.fromJson(jSONArray.get(i).toString(), Step.class));
            }
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return steps;
    }
    
    public int getNearestDoor(int garageId, double lat, double lon) {
        int nearestDoorId;
        
        Garage garage = GarageImp.getInstance().getGarage(garageId);
        
        HashMap<Integer, GarageDoors> doorsHashMap = new HashMap<Integer, GarageDoors>();
        
        Object[] garageDoors = garage.getGarageDoors().toArray();
        for (int i = 0; i < garageDoors.length; i++) {
            GarageDoors currentDoor = (GarageDoors) garageDoors[i];
            int distance = HaversineAlgorithm.HaversineInCintemerters(lat, lon, currentDoor.getLat(), currentDoor.getLon());
            doorsHashMap.put(distance, currentDoor);
        }
        
        return doorsHashMap.get(Collections.min(doorsHashMap.keySet())).getDoorId();
        
    }
    
    private HashMap<String, GarageStatus> addToHashMap(GarageStatus currentSlot, HashMap<String, GarageStatus> slots) {
        slots.put(currentSlot.getX() + "-" + currentSlot.getY(), currentSlot);
        return slots;
    }
    
    private void deletingGarageData(int garageId) {
        garageSlotDoorsSession.beginTransaction();
        Garage garage1 = (Garage) garageSlotDoorsSession.get(Garage.class, garageId);
        
        for (GarageDoors door : garage1.getGarageDoors()) {
            garageSlotDoorsSession.delete(door);
            
        }
        for (GarageStatus slot : garage1.getGarageStatus()) {
            garageSlotDoorsSession.delete(slot);
            
        }
        garageSlotDoorsSession.getTransaction().commit();
    }
    
}
