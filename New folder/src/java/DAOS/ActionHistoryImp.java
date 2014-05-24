/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import Sessions.ConnectionHandler;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import pojo.AdminsActions;

/**
 *
 * @author Mahmoud Eltaieb
 */
public class ActionHistoryImp {

    static Session actionHistorySession = ConnectionHandler.getActionHistorySession();

    public int addActionHistory(pojo.AdminsActions action) {
        int result = 0;
        try {
            actionHistorySession.beginTransaction();
            {

                actionHistorySession.save(action);
                result = 0;
            }
        } catch (Exception ex) {
            result = -1;

        } finally {
            actionHistorySession.getTransaction().commit();
        }
        return result;
    }

    public int addActionHistory(int adminId, int employeeId, char actionType) {
        return addActionHistory(new AdminsActions(adminId, employeeId, actionType));
    }

    // pretty cool name is'nt it
    public ArrayList<AdminsActions> whatHappendToMe(int employeeId) {
        ArrayList<AdminsActions> whatHappendToMe = null;
        try {
            Query q = actionHistorySession.createQuery("from ActionHistory where employee=:employee");
            q.setParameter("employee",  EmployeesImp.getInstance().getEmployee(employeeId));
            whatHappendToMe = (ArrayList<AdminsActions>) q.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return whatHappendToMe;
    }

    // pretty cool name is'nt it
    public ArrayList<AdminsActions> whatIDid(int adminId) {
        ArrayList<AdminsActions> whatIDid = null;
        try {
            Query q = actionHistorySession.createQuery("from ActionHistory where admin=:admin");
            q.setParameter("admin",  EmployeesImp.getInstance().getEmployee(adminId));
            whatIDid = (ArrayList<AdminsActions>) q.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return whatIDid;
    }

}
