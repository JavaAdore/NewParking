/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import DAOS.DailyHistoryReportImp;
import DAOS.EmployeesImp;
import DAOS.GarageImp;
import DAOS.GarageSlotDoorsImp;
import DAOS.GarageSlotsStatusImp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import pojo.Employees;
import reportsClasses.ReportHistoryRecord;

/**
 *
 * @author orcl
 */
public class Home {

    public static void main(String[] args) {

//        GObjects.Garage garage = GarageSlotDoorsImp.getInstance().generateGarageObject(4);
//            System.out.println("Ay kalam");
        // ArrayList<WrappedGarage> nearGarages = GarageImp.getInstance().getNearGarages(30.0712016,  31.0213405);
        EmployeesImp.getInstance().deleteMemember(new Employees(61));
        System.out.println("dd");
    }
}

  // 30.071352,31.021236,30.071354,31.021238 ==> 29 cm
// 30.071352,31.021236, 30.071354,31.021235 ==> 24 cm
// 30.071352,31.021236,30.071354,31.021236   ==> 22 cm
// int result = GarageSlotDoorsImp.getInstance().getNearestDoor(4, 30.071352, 31.021236);
  // System.out.println(result);
