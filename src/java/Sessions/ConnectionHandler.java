/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sessions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Mahmoud Eltaieb
 */
public class ConnectionHandler {

    public static void initiateSessions() {
        getYearlyHistoryReportSession();

        getMonthlyHistoryReportSession();
        getDailyHistoryReportSession();
        garageSlotDetailsSession();
        getGarageSlotStatusSession();
        getGarageSlotDoorsSession();
        getGarageSlotSession();
        getUserSession();
        getActionHistorySession();
        getEmployeeSession();
        getGarageSession();
        getMapSession();
        getRoleSession();

    }

    public static void destroySessions() {
        
    }

    String hibernatePath = "";
    private static SessionFactory sessionFactory;
    private static Session roleSession;
    private static Session mapSession;
    private static Session garageSession;
    private static Session employeeSession;
    private static Session actionHistorySession;
    private static Session userSession;
    private static Session garageSlotSession;
    private static Session garageSlotDoorsSession;
    private static Session garageSlotStatusSession;
    private static Session garageSlotDetailsSession;
    private static Session dailyHistoryReportSession;
    private static Session monthlyHistoryReportSession;
    private static Session yearlyHistoryReportSession;

    static {
        System.out.println(ConnectionHandler.class.getResource(""));
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

    }

    private ConnectionHandler() {
    }

    public static Session getRoleSession() {
        if (roleSession == null) {
            roleSession = sessionFactory.openSession();
        }
        return roleSession;
    }

    public static Session getMapSession() {
        if (mapSession == null) {
            mapSession = sessionFactory.openSession();
        }
        return mapSession;
    }

    public static Session getGarageSession() {
        if (garageSession == null) {
            garageSession = sessionFactory.openSession();
        }
        return garageSession;
    }

    public static Session getEmployeeSession() {
        if (employeeSession == null) {
            employeeSession = sessionFactory.openSession();
        }
        return employeeSession;
    }

    public static Session getActionHistorySession() {
        if (actionHistorySession == null) {
            actionHistorySession = sessionFactory.openSession();
        }
        return actionHistorySession;
    }

    public static Session getUserSession() {
        if (userSession == null) {
            userSession = sessionFactory.openSession();
        }
        return userSession;
    }

    public static Session getGarageSlotSession() {
        if (garageSlotSession == null) {
            garageSlotSession = sessionFactory.openSession();
        }
        return garageSlotSession;
    }

    public static Session getGarageSlotDoorsSession() {
        if (garageSlotDoorsSession == null) {
            garageSlotDoorsSession = sessionFactory.openSession();
        }
        return garageSlotDoorsSession;
    }

    public static Session getGarageSlotStatusSession() {
        if (garageSlotStatusSession == null) {
            garageSlotStatusSession = sessionFactory.openSession();
        }
        return garageSlotStatusSession;
    }

    public static Session garageSlotDetailsSession() {
        if (garageSlotDetailsSession == null) {
            garageSlotDetailsSession = sessionFactory.openSession();
        }
        return garageSlotDetailsSession;
    }

    public static Session getDailyHistoryReportSession() {
        if (dailyHistoryReportSession == null) {
            dailyHistoryReportSession = sessionFactory.openSession();
        }
        return dailyHistoryReportSession;
    }

    public static Session getMonthlyHistoryReportSession() {
        if (monthlyHistoryReportSession == null) {
            monthlyHistoryReportSession = sessionFactory.openSession();
        }
        return monthlyHistoryReportSession;
    }

    public static Session getYearlyHistoryReportSession() {
        if (yearlyHistoryReportSession == null) {
            yearlyHistoryReportSession = sessionFactory.openSession();
        }
        return yearlyHistoryReportSession;
    }

    public static void destroyRoleSession() {
        roleSession = null;

    }

    public static void destroyMapSession() {
        mapSession = null;
    }

    public static void destroyGarageSession() {
        garageSession = null;
    }

    public static void destroyEmpployeeSession() {
        employeeSession = null;
    }

    public static void destroyActionHistorySession() {
        actionHistorySession = null;
    }

    public static void destroyUserSession() {
        userSession = null;
    }

    public static void destroyGarageSlotSession() {
        garageSlotSession = null;
    }

    public static void destroyGarageSlotDoorsSession() {
        garageSlotDoorsSession = null;
    }

    public static void destroyGarageSlotStatusSession() {
        garageSlotStatusSession = null;
    }

    public static void destroyGarageSlotDetailsSession() {
        garageSlotDetailsSession = null;
    }

    public static void destroyDailyHistoryReportSession() {
        dailyHistoryReportSession = null;
    }

    public static void destroyMonthlyHistoryReportSession() {
        monthlyHistoryReportSession = null;
    }

    public static void destroyYearlyHistoryReportSession() {
        yearlyHistoryReportSession = null;
    }

}
