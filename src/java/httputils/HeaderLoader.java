/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httputils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author orcl
 */
public class HeaderLoader {

    private static void loadServiceProviderHeader() {
        serviceProviderHeader.add(new Header("addAdmin", "Add Emp", "addadmin.jsp"));
        serviceProviderHeader.add(new Header("removeEmp", "Remove Emp", "LoadAllEmployeesInitializer?toPage=removeadmin.jsp"));
        serviceProviderHeader.add(new Header("addGarage", "Add Garage", "addgarage.jsp"));
        serviceProviderHeader.add(new Header("updateGarage", "Update Garage", "LoadAllGaragesInitializer?toPage=updategarageinfo.jsp"));
        serviceProviderHeader.add(new Header("drawingTool", "DrawingTool", "drawgaragemap.jsp"));
        serviceProviderHeader.add(new Header("removeGarage", "Remove Garage", "LoadAllGaragesInitializer?toPage=removegarage.jsp"));
        serviceProviderHeader.add(new Header("assignEmp", "Assign Emp To Garage", "AssignAdminHandler"));
        serviceProviderHeader.add(new Header("updateProfile", "Update Profile", "LoadAllEmployeesInitializer?toPage=update.jsp"));

    }

    private static void loadAdminHeader() {

        adminHeader.add(new Header("editProfile", "Edit Profile", "editprofile.jsp"));
        adminHeader.add(new Header("addEmp", "Add Emp", "addadminbyadmin.jsp"));
        adminHeader.add(new Header("removeEmp", "Remove Emp", "RemoveAdminInitializer"));
        adminHeader.add(new Header("editGarageData", "Edit Garage Data", "editgaragedata.jsp"));
        adminHeader.add(new Header("updateSlot", "Update Slot Status", "UpdateSlotInitializer"));
        adminHeader.add(new Header("viewDetailedReport", "View Detailed Report", "viewdetailedreport.jsp"));
        adminHeader.add(new Header("viewReport", "View Report", "viewreport.jsp"));
    }

    private static void loadAccounantHeader() {
        accountatnHeader.add(new Header("viewReport", "View Report", "accountant.jsp"));

    }

    public HeaderLoader() {

    }

    private static List serviceProviderHeader = new ArrayList<Header>();
    private static List adminHeader = new ArrayList<Header>();
    private static List accountatnHeader = new ArrayList<Header>();

    public static List<Header> getServiceProviderHeader() {

        loadServiceProviderHeader();
        return serviceProviderHeader;
    }

    public static List<Header> getAdminHeader() {
        loadAdminHeader();
        return adminHeader;
    }

    public static List<Header> getAccountatnHeader() {
        loadAccounantHeader();
        return accountatnHeader;
    }

    public static void main(String[] args) {
        List<Header> serviceProviderHeader1 = getServiceProviderHeader();
        List<Header> adminHeader1 = getAdminHeader();
        List<Header> accountatnHeader1 = getAccountatnHeader();
        System.out.println();

    }
}
