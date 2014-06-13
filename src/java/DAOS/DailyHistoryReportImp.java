/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Sessions.ConnectionHandler;
import daosint.ReportsInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.*;

/**
 *
 * @author orcl
 */
public class DailyHistoryReportImp {
    
    Session dailyHistoryReportSession = ConnectionHandler.getDailyHistoryReportSession();
    
    static DailyHistoryReportImp instance;
    
    public static DailyHistoryReportImp getInstance() {
        if (instance == null) {
            instance = new DailyHistoryReportImp();
            
        }
        return instance;
    }
    
    private DailyHistoryReportImp() {
    }
    
    public ArrayList<ReportsInterface> getDailyHistory(int garageId) {
        
        ArrayList<ReportsInterface> result = null;
        try {
            
            Query q = dailyHistoryReportSession.createQuery("from DailyHistory where slotId in :slotid");
            q.setParameterList("slotid", getGarageSlots(garageId));
            result = (ArrayList<ReportsInterface>) q.list();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
        
        return result;
    }
    
    public ArrayList<ReportsInterface> getConsiceDailyHistory(int garageId) {
        
        Garage garage =  GarageImp.getInstance().getGarage(garageId);
        ArrayList<ReportsInterface> result = new ArrayList<>();;
        try {
            
            Query q = dailyHistoryReportSession.createQuery("select  recordDate  ,  sum(hours) ,sum( income) from DailyHistory where slotId in :slotid group by recordDate order by recordDate ");
            q.setParameterList("slotid", garage.getGarageStatus());
            ArrayList<Object[]> res = (ArrayList<Object[]>) q.list();
            
            for (Object[] obj : res) {
                result.add(new DailyHistory((Date) obj[0], Double.valueOf(obj[1].toString()), Double.valueOf(obj[2].toString())));
                
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
        
        return result;
    }
    
    public GarageStatus[] getGarageSlots(int garageId) {
        
        GarageStatus[] slots;
        Query q = dailyHistoryReportSession.createQuery("select slotId from GarageStatus where garage = :garage");
        q.setParameter("garage", new Garage(garageId));
        
        List<Object> result = (List<Object>) q.list();
        slots = new GarageStatus[result.size()];
        for (int i = 0; i < result.size(); i++) {
            
            slots[i] = new GarageStatus(new Integer(result.get(i) + ""));
        }
        
        return slots;
        
    }
    
}
