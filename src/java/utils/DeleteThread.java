/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import DAOS.EmployeesImp;
import DAOS.GarageImp;
import java.util.ArrayList;
import java.util.Date;
import pojo.DeleteEmployeeSchedule;
import pojo.DeleteGarageSchedule;

/**
 *
 * @author orcl
 */
public class DeleteThread {

    public DeleteThread() {
        Delete();

    }

    private void Delete() {

        new Thread() {
            public void run() {
                try {
                    for (;;) {
                        Date date = new Date();
                        GarageImp garageImpInstance = GarageImp.getInstance();
                        EmployeesImp employeesImpInstance = EmployeesImp.getInstance();

                        for (DeleteGarageSchedule deleteGarageSchedule : garageImpInstance.getDeleteGarageSchedule()) {
                            System.out.println("garage "+deleteGarageSchedule.getDeleteDate().toGMTString() + " " + date.toGMTString());
                            if (date.after(deleteGarageSchedule.getDeleteDate())) {
                                garageImpInstance.deleteGarage(deleteGarageSchedule.getGarageId());
                            }
                        }
                        for (DeleteEmployeeSchedule deleteEmployeeSchedule : employeesImpInstance.getDeleteEmployeeSchedule()) {
                            System.out.println("Employee "+deleteEmployeeSchedule.getDeleteDate().toGMTString() + " " + date.toGMTString());

                            if (date.after(deleteEmployeeSchedule.getDeleteDate())) {
                                employeesImpInstance.deleteMemember(deleteEmployeeSchedule.getEmployeeId());
                            }

                        }
                        sleep(86400000);
                        //sleep(6000);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("deleting thread is running dude");
            }
        }.start();

    }
}
