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
//                for (;;)
                {
                    try {
                        new Thread() {
                            public void run() {
                                try {
                                    Date date = new Date();
                                    GarageImp garageImpInstance = GarageImp.getInstance();
                                    EmployeesImp employeesImpInstance = EmployeesImp.getInstance();

                                    for (DeleteGarageSchedule deleteGarageSchedule : garageImpInstance.getDeleteGarageSchedule()) {
                                        if (date.after(deleteGarageSchedule.getDeleteDate())) {
                                            garageImpInstance.deleteGarage(deleteGarageSchedule.getGarageId());
                                        }
                                    }
                                    for (DeleteEmployeeSchedule deleteEmployeeSchedule : employeesImpInstance.getDeleteEmployeeSchedule()) {
                                        if (date.after(deleteEmployeeSchedule.getDeleteDate())) {
                                            employeesImpInstance.deleteMemember(deleteEmployeeSchedule.getEmployeeId());
                                        }

                                    }

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                System.out.println("deleting thread is running dude");
                            }
                        }.start();
                        //86400000 
                        sleep(6000);
                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }

                }

            }

        }.start();
    }

    public static void main(String[] args) {
        new DeleteThread();

    }

}
