/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Sessions.ConnectionHandler;
import daosint.ReportsInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.DailyHistory;
import pojo.Garage;
import utils.*;
import reportsClasses.ReportHistoryRecord;

/**
 *
 * @author orcl
 */
public class ReportsImp {

    private Session garageSession = ConnectionHandler.getGarageSession();
    private static ReportsImp instance;

    public static ReportsImp getInstance() {
        if (instance == null) {
            instance = new ReportsImp();
        }
        return instance;
    }

    private ReportsImp() {
    }

    public List<DailyHistory> getDailyHistory(int garageId) {

        List result = new ArrayList<DailyHistory>();
        try {

            Garage garage = (Garage) garageSession.get(Garage.class, garageId);
            if (garage != null) {
                if (garage.getGarageStatus().size() > 0) {
                    Query q = garageSession.createQuery("from DailyHistory where slotId in :slotid");
                    q.setParameterList("slotid", garage.getGarageStatus());
                    result = (ArrayList<DailyHistory>) q.list();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return result;
    }

    public ArrayList<ReportsInterface> getConsiceDailyHistory(int garageId) {

        Garage garage = GarageImp.getInstance().getGarage(garageId);
        ArrayList<ReportsInterface> result = new ArrayList<>();;
        try {

            Query q = garageSession.createQuery("select  recordDate  ,  sum(hours) ,sum( income) from DailyHistory where slotId in :slotid group by recordDate order by recordDate ");
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

    public List<DailyHistory> getMonthlyHistory(int garageId) {

        List result = new ArrayList<DailyHistory>();
        try {

            Garage garage = (Garage) garageSession.get(Garage.class, garageId);
            if (garage != null) {
                if (garage.getGarageStatus().size() > 0) {
                    Query q = garageSession.createQuery("from MonthlyHistory where slotId in :slotid");
                    q.setParameterList("slotid", garage.getGarageStatus());
                    result = (ArrayList<DailyHistory>) q.list();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return result;
    }

    public ArrayList<ReportsInterface> getConsiceMonthlyHistory(int garageId) {

        Garage garage = GarageImp.getInstance().getGarage(garageId);
        ArrayList<ReportsInterface> result = new ArrayList<>();;
        try {

            Query q = garageSession.createQuery("  select  recordDate  ,  sum(hours) , sum(income) from MonthlyHistory where slotId in :slotid group by recordDate order by recordDate   ");
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

    public ArrayList<ReportsInterface> getConsiceYearlyHistory(int garageId) {

        Garage garage = GarageImp.getInstance().getGarage(garageId);
        ArrayList<ReportsInterface> result = new ArrayList<>();;
        try {

            Query q = garageSession.createQuery("  select  recordDate  ,  sum(hours)  , sum(income) from YearlyHistory where slotId in :slotid group by recordDate order by recordDate   ");
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

    public List<DailyHistory> getYearlyHistory(int garageId) {

        List result = new ArrayList<DailyHistory>();
        try {

            Garage garage = (Garage) garageSession.get(Garage.class, garageId);
            if (garage != null) {
                if (garage.getGarageStatus().size() > 0) {
                    Query q = garageSession.createQuery("from YearlyHistory where slotId in :slotid");
                    q.setParameterList("slotid", garage.getGarageStatus());
                    result = (ArrayList<DailyHistory>) q.list();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return result;
    }

    public List< ReportsInterface> getConciseReport(int garageId) {
        List<ArrayList<ReportsInterface>> merged = new ArrayList();
        merged.add(getConsiceDailyHistory(garageId));
        merged.add(getConsiceMonthlyHistory(garageId));
        merged.add(getConsiceYearlyHistory(garageId));

        return mergeHistoryReports(merged);

    }

    public static List< ReportsInterface> mergeHistoryReports(List<ArrayList<ReportsInterface>> merged) {

        ArrayList<ReportsInterface> result = new ArrayList<>();
        for (ArrayList<ReportsInterface> category : merged) {
            for (ReportsInterface record : category) {

                result.add(record);
            }
        }
        return result;
    }

    public ReportHistoryRecord prepareHistoryRecord(int garageId, String from, String to) {
        ReportHistoryRecord result = new ReportHistoryRecord();

        Garage garage = GarageImp.getInstance().getGarage(garageId);
        if (garage != null) {
            double hours = 0;
            double income = 0;

            Date minDate = Utils.correctDates(Utils.totDate(from, "MM/dd/yyyy"), Utils.totDate(to, "MM/dd/yyyy"), "min");
            Date maxDate = Utils.correctDates(Utils.totDate(from, "MM/dd/yyyy"), Utils.totDate(to, "MM/dd/yyyy"), "max");

            for (ReportsInterface reportInterface : getConciseReport(garage.getGarageId())) {
                if ((reportInterface.getRecordDate().after(minDate) || reportInterface.getRecordDate().equals(minDate)) && (reportInterface.getRecordDate().before(maxDate) || reportInterface.getRecordDate().equals(maxDate))) {
                    hours += reportInterface.getHours();
                    income += reportInterface.getIncome();
                }
            }
            result.setIncome(income);
            result.setRecordDate(new Date());
            result.setHours(hours);
            result.setFrom(Utils.toString(minDate));
            result.setTo(Utils.toString(maxDate));

            result.setAvrageOrConsumption((hours / (((Utils.daysBetween(minDate, maxDate) + 1) * 24) * GarageUtils.getActiveSlots(garage.getGarageStatus()).size())));
        }
        return result;
    }

}
