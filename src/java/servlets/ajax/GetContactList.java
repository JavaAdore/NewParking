/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.EmployeeWrapper;

/**
 *
 * @author orcl
 */
public class GetContactList extends HttpServlet {

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
            EmployeeWrapper emp = (EmployeeWrapper) request.getSession().getAttribute("emp");
            String deleteButtonFormatingClass = request.getParameter("deleteButtonFormatingClass");
            String deleteButtonMethod = request.getParameter("deleteButtonMethod");
            String identifier = request.getParameter("identifier");
            try {
                switch (identifier) {
                    case "p":
                        out.print(utils.Utils.loadContacts(emp.getGarage().getGarageId(), deleteButtonFormatingClass, deleteButtonMethod));
                        break;
                    case "f":
                        out.print(utils.Utils.loadFaxList(emp.getGarage().getGarageId(), deleteButtonFormatingClass, deleteButtonMethod));

                        break;
                    case "e":
                        out.print(utils.Utils.loadEmailList(emp.getGarage().getGarageId(), deleteButtonFormatingClass, deleteButtonMethod));

                        break;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                out.print(ex.getMessage());
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
