/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daosint.ReportsInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.GarageStatus;
import reportsClasses.ReportHistoryRecord;
import utils.CustomDate;
import utils.EmployeeWrapper;
import utils.Utils;

/**
 *
 * @author orcl
 */
public class DetailedReportInitializer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utils.checkCurrentUserStatus(request);
        Utils.checkSession(request);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String from = request.getParameter("from");
            String to = request.getParameter("to");
            String minDate = Utils.populateString((CustomDate) request.getSession().getAttribute("minDate"));
            String maxDate = Utils.populateString((CustomDate) request.getSession().getAttribute("maxDate"));
            EmployeeWrapper emp = (EmployeeWrapper) request.getSession().getAttribute("emp");
            HashMap<GarageStatus, List<ReportsInterface>> historyRecord;
            historyRecord = (HashMap<GarageStatus, List<ReportsInterface>>) request.getSession().getAttribute("detailed");
            if (historyRecord != null) {
                HashMap<GarageStatus, ReportHistoryRecord> detailedReport = Utils.prepareSlotsHistoryRecord(historyRecord, (from.length() > 0) ? from : minDate, (to.length() > 0) ? to : maxDate, emp.getGarage().getGarageStatus().size());
                request.getSession().setAttribute("detailedReport", detailedReport);
                System.out.println();
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
