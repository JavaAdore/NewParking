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

    static Session garageSession = ConnectionHandler.getGarageSession();
    static GarageSlotImp instance;

    public static GarageSlotImp getInstance() {
        if (instance == null) {
            instance = new GarageSlotImp();
        }
        return instance;
    }

    private GarageSlotImp() {
    }
    
    

   
}
