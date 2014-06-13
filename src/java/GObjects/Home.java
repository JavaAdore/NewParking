/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GObjects;

import DAOS.EmployeesImp;
import DAOS.GarageImp;
import DAOS.GarageSlotDoorsImp;
import DAOS.UserImp;
import java.util.List;
import pojo.Employees;
import pojo.Users;
import utils.HelperClass;

/**
 *
 * @author orcl
 */
public class Home {

    public static void main(String[] args) {
        //  pojo.Garage garage = GarageImp.getInstance().getGarage("iti");
        //GarageImp.getInstance().deleteGarage(garage.getGarageId());
        HelperClass.insertInitialDataPlease();
        //GarageSlotDoorsImp.getInstance().han dleThisGaragePlease(HelperClass.prepareMeGarageDemoPlease(4));
        //    GObjects.Garage g = GarageSlotDoorsImp.getInstance().generateGarageObject(4);
        //    System.out.println("dddd");
        
        
        
       // System.out.println(slot1.equals(slot2));
       
       // System.out.println(GarageSlotDoorsImp.getInstance().getNearestDoor(4, 30.0707049, 31.0209113));
        ;
    }

    public static void printListSize(List list) {

        System.out.println(list.size());

    }

    public static void printOutput(Object x) {
        System.out.println(x.toString());

    }

}
