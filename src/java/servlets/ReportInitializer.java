/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAOS.ReportsImp;
import daosint.ReportsInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import report.ReportHistoryRecord;
import utils.*;
import utils.EmployeeWrapper;

/**
 *
 * @author orcl
 */
public class ReportInitializer extends HttpServlet {

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
            ReportHistoryRecord report = ReportsImp.getInstance().prepareHistoryRecord(emp.getGarage().getGarageId(), from, to);
            request.getSession().setAttribute("report", report);

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
