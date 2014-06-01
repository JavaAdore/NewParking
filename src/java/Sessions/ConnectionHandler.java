/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sessions;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.BasicType;

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
        getEmployeeSession();
        getGarageSession();

        getActionHistorySession();
        getMapSession();
        getRoleSession();
    }

    public static void destroySessions() {

        roleSession.close();
        mapSession.close();
        garageSession.close();
        employeeSession.close();
        actionHistorySession.close();
        userSession.close();
        garageSlotSession.close();
        garageSlotDoorsSession.close();
        garageSlotStatusSession.close();
        garageSlotDetailsSession.close();
        dailyHistoryReportSession.close();
        monthlyHistoryReportSession.close();
        yearlyHistoryReportSession.close();

        sessionFactory.close();
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
            garageSession.setCacheMode(CacheMode.NORMAL);
        }
        return garageSession;

    }

    public static Session getEmployeeSession() {
        if (employeeSession == null) {
            employeeSession = sessionFactory.openSession();
            employeeSession.setCacheMode(CacheMode.NORMAL);
        }
        return employeeSession;
    }

    public static Session getActionHistorySession() {
        if (actionHistorySession == null) {
            actionHistorySession = sessionFactory.openSession();
            actionHistorySession.setCacheMode(CacheMode.NORMAL);
        }
        return actionHistorySession;
    }

    public static Session getUserSession() {
        if (userSession == null) {
            userSession = sessionFactory.openSession();
            userSession.setCacheMode(CacheMode.NORMAL);
        }
        return userSession;
    }

    public static Session getGarageSlotSession() {
        if (garageSlotSession == null) {
            garageSlotSession = sessionFactory.openSession();
            garageSlotSession.setCacheMode(CacheMode.NORMAL);
        }
        return garageSlotSession;
    }

    public static Session getGarageSlotDoorsSession() {
        if (garageSlotDoorsSession == null) {
            garageSlotDoorsSession = sessionFactory.openSession();
            garageSlotDoorsSession.setCacheMode(CacheMode.NORMAL);

        }
        return garageSlotDoorsSession;
    }

    public static Session getGarageSlotStatusSession() {
        if (garageSlotStatusSession == null) {
            garageSlotStatusSession = sessionFactory.openSession();
            garageSlotStatusSession.setCacheMode(CacheMode.NORMAL);
        }
        return garageSlotStatusSession;
    }

    public static Session garageSlotDetailsSession() {
        if (garageSlotDetailsSession == null) {
            garageSlotDetailsSession = sessionFactory.openSession();
            garageSlotDetailsSession.setCacheMode(CacheMode.NORMAL);
        }
        return garageSlotDetailsSession;
    }

    public static Session getDailyHistoryReportSession() {
        if (dailyHistoryReportSession == null) {
            dailyHistoryReportSession = sessionFactory.openSession();
            dailyHistoryReportSession.setCacheMode(CacheMode.NORMAL);
        }
        return dailyHistoryReportSession;
    }

    public static Session getMonthlyHistoryReportSession() {
        if (monthlyHistoryReportSession == null) {
            monthlyHistoryReportSession = sessionFactory.openSession();
            monthlyHistoryReportSession.setCacheMode(CacheMode.NORMAL);
        }
        return monthlyHistoryReportSession;
    }

    public static Session getYearlyHistoryReportSession() {
        if (yearlyHistoryReportSession == null) {
            yearlyHistoryReportSession = sessionFactory.openSession();
            yearlyHistoryReportSession.setCacheMode(CacheMode.NORMAL);
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
