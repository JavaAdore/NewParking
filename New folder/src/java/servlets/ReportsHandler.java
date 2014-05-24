/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAOS.*;
import utils.Utils;

/**
 *
 * @author orcl
 */
public class ReportsHandler extends HttpServlet 
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // till now this servet is properbly unused
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utils.checkCurrentUserStatus(request);
           Utils.checkSession(request);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String tempGarage = request.getParameter("garageId");
            if (tempGarage == null) {
                response.sendRedirect("viewreport.jsp");
                return;
            }
            int garageId = Integer.parseInt(tempGarage);
            DailyHistoryReportImp.getInstance().getDailyHistory(garageId);
            MonthlyHistoryReportImp.getInstance().getMonthlyHistory(garageId);
            YearlyHistoryReportImp.getInstance().getYearlyHistory(garageId);

            request.getSession().setAttribute("dailyHistory", DailyHistoryReportImp.getInstance().getDailyHistory(garageId));
            request.getSession().setAttribute("monthlyHistory", MonthlyHistoryReportImp.getInstance().getMonthlyHistory(garageId));
            request.getSession().setAttribute("yearlyHistory", YearlyHistoryReportImp.getInstance().getYearlyHistory(garageId));
            
            response.sendRedirect("displayreports");
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
