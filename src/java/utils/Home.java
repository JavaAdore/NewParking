/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import DAOS.EmployeesImp;
import DAOS.GarageImp;
import DAOS.GarageSlotDoorsImp;
import DAOS.GarageSlotsStatusImp;
import DAOS.ReportsImp;
import DAOS.SlotsDetailsImp;
import DAOS.UserImp;
import daosint.ReportsInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import pojo.Address;
import pojo.Employees;
import pojo.Garage;
import pojo.GarageSlotsDoors;
import pojo.Map;
import pojo.Users;
import report.ReportHistoryRecord;

/**
 *
 * @author orcl
 */
public class Home {

    public static void main(String[] args) {

//GarageImp.getInstance().addContact(530 , "01112256793", utils.Constants.PHONE);
        UserImp.getInstance().addApplciationFeedback(1, "bad application");
    }
}

