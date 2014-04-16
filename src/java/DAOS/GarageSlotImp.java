/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Sessions.ConnectionHandler;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.Garage;
import pojo.GarageStatus;

/**
 *
 * @author orcl
 */
public class GarageSlotImp {

    static Session garageSlotSession = ConnectionHandler.getGarageSlotSession();
    static GarageSlotImp instance;

    public static GarageSlotImp getInstance() {
        if (instance == null) {
            instance = new GarageSlotImp();
        }
        return instance;
    }

    private GarageSlotImp() {
    }

    public String UpdateGarageSlot(int slotid, int status) {
        String output = "";
        try {
            garageSlotSession.beginTransaction();
            Query q = garageSlotSession.createQuery("from GarageStatus where slotid = :slotid ");
            q.setParameter("slotid", slotid);
            GarageStatus result = (GarageStatus) q.uniqueResult();
            if (result != null) {
                result.setStatus(status);
                if (status == 0) {
//                    garageSlotSession.getNamedQuery("update_Garage_status").setParameter("slotid", slotid).executeUpdate();
                    result.setConsumedHours(result.getConsumedHours() + utils.Utils.hoursBetween(result.getArrivalTime(), new Date()));
                    System.out.println("supposed to update garage slot");

                } else {
                    result.setArrivalTime(new Timestamp(new Date().getTime()));

                }
                output = "slot status has been updated successfully ";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            garageSlotSession.getTransaction().rollback();
            output = "looks like some error happend during updating slot status please contact adminstrator ";
        } finally {
            garageSlotSession.getTransaction().commit();
        }
        return output;

    }

    public GarageStatus getSlot(int slotId) {
        return (GarageStatus) garageSlotSession.get(GarageStatus.class, slotId);

    }

    public String setEnabled(int slotId, int enabled) {
        String result = "-1";
        GarageStatus slot = getSlot(slotId);
        if (slot != null) {
            slot.setEnabled(enabled);

            return updateSlot(slot) + "";
        }

        return result;
    }

    public int updateSlot(GarageStatus slot) {
        try {
            garageSlotSession.beginTransaction();
            garageSlotSession.persist(slot);
            garageSlotSession.getTransaction().commit();
        } catch (Exception ex) {
            return -1;
        }
        return 0;
    }

    public ArrayList<GarageStatus> getGarageStatusList(int garageId) {

        Query query = garageSlotSession.createQuery("from GarageStatus where garage=:garage");
        query.setParameter("garage", new Garage(garageId));
        return (ArrayList<GarageStatus>) query.list();

    }
}
