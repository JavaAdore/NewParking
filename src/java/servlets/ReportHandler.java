/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAOS.DailyHistoryReportImp;
import DAOS.GarageImp;
import DAOS.MonthlyHistoryReportImp;
import DAOS.YearlyHistoryReportImp;
import daosint.ReportsInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.DailyHistory;
import reportsClasses.CustomDate;
import reportsClasses.ReportHistoryRecord;
import utils.EmployeeRole;
import utils.EmployeeWrapper;
import utils.Utils;

/**
 *
 * @author orcl
 */
public class ReportHandler extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            ArrayList<ReportsInterface> dailyHistoryRecord, monthlyHistoryRecord, yearlyHistoryRecord;
            ArrayList<ArrayList<ReportsInterface>> merged = new ArrayList();
            EmployeeWrapper emp = (EmployeeWrapper) request.getSession().getAttribute("emp");
            Calendar c = Calendar.getInstance();
            if (emp.getGarage() != null) {
                dailyHistoryRecord = DailyHistoryReportImp.getInstance().getConsiceDailyHistory(emp.getGarage().getGarageId());
                monthlyHistoryRecord = MonthlyHistoryReportImp.getInstance().getConsiceMonthlyHistory(emp.getGarage().getGarageId());
                yearlyHistoryRecord = YearlyHistoryReportImp.getInstance().getConsiceYearlyHistory(emp.getGarage().getGarageId());

                merged.add(dailyHistoryRecord);
                merged.add(monthlyHistoryRecord);
                merged.add(yearlyHistoryRecord);

                ArrayList<ReportsInterface> mergeHistoryReports = Utils.mergeHistoryReports(merged);
                Date minDate = Utils.getMinDate(dailyHistoryRecord, monthlyHistoryRecord, yearlyHistoryRecord);
                request.getSession().setAttribute("minDate", Utils.toString(minDate));
                Date maxDate = new Date();
                if (Utils.daysBetween(minDate, new Date()) > 0) {
                    c.add(Calendar.DAY_OF_MONTH, -1);
                    maxDate = c.getTime();
                    System.out.println(maxDate);

                }

                if (emp.getRoles().getRoleName().equalsIgnoreCase(EmployeeRole.ADMIN)) {
                    request.getSession().setAttribute("detailed", Utils.detailed(emp.getGarage().getGarageId()));
                }
                String uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + getServletContext().getContextPath();

                request.getSession().setAttribute("maxDate", Utils.toString(maxDate));
                request.getSession().setAttribute("uri", uri);
                String imageURL = GarageImp.getInstance().getImagePath(emp.getGarage().getGarageId());
                request.getSession().setAttribute("imageURL", imageURL);
                request.getSession().setAttribute("historyRecord", mergeHistoryReports);
                out.print(String.format(" min= %s max=%s value=%s", Utils.toString(minDate), Utils.toString(maxDate), Utils.toString(maxDate)));

            }

        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
