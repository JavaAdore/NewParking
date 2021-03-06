/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.ajax;

import DAOS.GarageImp;
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
public class ContactHandler extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            EmployeeWrapper emp = (EmployeeWrapper) request.getSession().getAttribute("emp");
            if (emp != null) {
                String contact = request.getParameter("contact");
                String contactType = request.getParameter("type");
                String qualifier = request.getParameter("qualifier");
                if(emp.getGarage()!=null)
                {
                if (qualifier != null) {
                    switch (qualifier) {
                        case "a":
                            if (contact != null && contactType != null) {
                                try {
                                    int gId = emp.getGarage().getGarageId();
                                    switch (contactType) {
                                        case "phone":
                                            out.print(GarageImp.getInstance().addContact(gId, contact, utils.Constants.PHONE));
                                            break;
                                        case "fax":
                                            out.print(GarageImp.getInstance().addContact(gId, contact, utils.Constants.FAX));
                                            break;
                                        case "email":
                                            out.print(GarageImp.getInstance().addContact(gId, contact, utils.Constants.EMAIL));
                                            break;
                                    }
                                } catch (Exception ex) {
                                    out.print(utils.Constants.FAILED);
                                    ex.printStackTrace();
                                }
                            }
                            break;
                        case "d":
                            int result = GarageImp.getInstance().deleteContact(emp.getGarage().getGarageId(), contact, contactType);
                            out.print(result);
                            break;

                    }
                }
            }
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
