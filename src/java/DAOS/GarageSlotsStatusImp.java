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

    

    public GarageStatus getGarageStatus(int slotId) {
        return (GarageStatus) GarageSlotsStatusSession.get(GarageStatus.class, slotId);

    }

}
