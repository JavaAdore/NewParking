/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAOS.DailyHistoryReportImp;
import DAOS.MonthlyHistoryReportImp;
import DAOS.YearlyHistoryReportImp;
import daosint.ReportsInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.DailyHistory;
import reportsClasses.ReportHistoryRecord;
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
            Utils.checkCurrentUserStatus(request);
            Utils.checkSession(request);
            ArrayList<ReportsInterface> historyRecord = null;
            EmployeeWrapper emp = (EmployeeWrapper) request.getSession().getAttribute("emp");
            if (emp.getGarage() != null) {

                String reportType = request.getParameter("selection");
                switch (reportType) {
                    case "d":
                        historyRecord = DailyHistoryReportImp.getInstance().getConsiceDailyHistory(emp.getGarage().getGarageId());

                        out.print(Utils.prepareSelectTag(historyRecord));
                        break;
                    case "m":
                        historyRecord = MonthlyHistoryReportImp.getInstance().getConsiceMonthlyHistory(emp.getGarage().getGarageId());
                        out.print(Utils.prepareSelectTag(historyRecord));
                        break;
                    case "y":
                        historyRecord = YearlyHistoryReportImp.getInstance().getConsiceYearlyHistory(emp.getGarage().getGarageId());
                        out.print(Utils.prepareSelectTag(historyRecord));
                        break;
                }
                request.getSession().setAttribute("report", null); 
                request.getSession().setAttribute("historyRecord", historyRecord);
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
