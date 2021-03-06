/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAOS.EmployeesImp;
import DAOS.GarageImp;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.Employees;
import pojo.Garage;
import utils.EmployeeRole;
import utils.EmployeeWrapper;
import utils.Utils;

/**
 *
 * @author orcl
 */
public class LoadAllEmployeesInitializer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String toUrl = request.getParameter("toPage");

            EmployeeWrapper emp = (EmployeeWrapper) request.getSession().getAttribute("emp");
            ArrayList<Employees> allEmployees = null;
            if (emp.getRoles().getRoleName().equalsIgnoreCase(EmployeeRole.SERVICE_PROVIDER)) {
                allEmployees = (ArrayList<Employees>) EmployeesImp.getInstance().getAllEmployees();
            } else if (emp.getRoles().getRoleName().equalsIgnoreCase(EmployeeRole.ADMIN)) {
                allEmployees = (ArrayList<Employees>) EmployeesImp.getInstance().getAllEmployees(emp.getGarage().getGarageId());
            }

            request.setAttribute("numberOfEmployees", Utils.numberOfRoleHolders(allEmployees));
            request.setAttribute("numberOfInActiveEmployees", Utils.numberOfInActiveEmployees(allEmployees));
            request.setAttribute("allEmployees", allEmployees);

            request.getRequestDispatcher(toUrl).forward(request, response);
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
