/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Sessions.ConnectionHandler;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.GarageDoors;
import pojo.GarageSlotsDoors;
import pojo.GarageStatus;
import pojo.SlotDoorId;

/**
 *
 * @author orcl
 */
public class SlotsDetailsImp {

    private static SlotsDetailsImp instance;
    private static Session slotDetailsSession = ConnectionHandler.garageSlotDetailsSession();

    private SlotsDetailsImp() {
    }

    public static SlotsDetailsImp getInstance() {
        if (instance == null) {
            instance = new SlotsDetailsImp();
        }
        return instance;
    }

    public GarageSlotsDoors getSlotDetail(int slotId, int doorId) {
        GarageSlotsDoors garageSlotDoors = null;
        try {
            slotDetailsSession.beginTransaction();
            Query q = slotDetailsSession.createQuery("from GarageSlotsDoors where slotId=:slot and doorId =:door");
            q.setParameter("slot", new GarageStatus(slotId));
            q.setParameter("door", new GarageDoors(doorId));
            garageSlotDoors = (GarageSlotsDoors) q.uniqueResult();

        } catch (Exception ex) {

            ex.printStackTrace();
        } finally {
            slotDetailsSession.getTransaction().commit();

        }
        return garageSlotDoors;
    }

}
