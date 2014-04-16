/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Sessions.ConnectionHandler;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.Garage;
import pojo.GarageStatus;
import utils.WrappedGarageSlotsStatus;

/**
 *
 * @author orcl
 */
public class GarageSlotsStatusImp {

    private static GarageSlotsStatusImp instance;
    private final Session GarageSlotsStatusSession = ConnectionHandler.getGarageSlotStatusSession();

    private GarageSlotsStatusImp() {
    }

    public static GarageSlotsStatusImp getInstance() {
        if (instance == null) {
            instance = new GarageSlotsStatusImp();
        }
        return instance;

    }

    public ArrayList<WrappedGarageSlotsStatus> getGarageSlotsStatus(int garageId) {
        ArrayList<WrappedGarageSlotsStatus> output = new ArrayList<WrappedGarageSlotsStatus>();
        try {

            GarageSlotsStatusSession.beginTransaction();
            Query q = GarageSlotsStatusSession.createSQLQuery("select slotId , status , x , y  ,  slotName from  GarageStatus where Garage_GarageId = ?");
            q.setInteger(0, garageId);
            List<Object[]> result = (List<Object[]>) q.list();
            for (Object[] obj : result) {
                int slotId = new Integer(obj[0] + "");
                int status = new Integer(obj[1] + "");
                int x = new Integer(obj[2] + "");
                int y = new Integer(obj[3] + "");
                String slotName = new String(obj[4] + "");
                output.add(new WrappedGarageSlotsStatus(slotId, slotName, status, x, y));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            GarageSlotsStatusSession.getTransaction().rollback();
        } finally {
            GarageSlotsStatusSession.getTransaction().commit();
        }
        return output;
    }

    public GarageStatus getGarageStatus(int slotId) {
        return (GarageStatus) GarageSlotsStatusSession.get(GarageStatus.class, slotId);

    }

}
