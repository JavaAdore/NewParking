/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAOS.GarageImp;
import DAOS.GarageSlotImp;
import DAOS.GarageSlotsStatusImp;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.Garage;
import pojo.GarageStatus;
import utils.EmployeeWrapper;
import utils.WrappedGarageSlotsStatus;

/**
 *
 * @author orcl
 */
public class UpdateSlotInitializer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            EmployeeWrapper emp = (EmployeeWrapper) request.getSession().getAttribute("emp");
            if (emp != null)
            {

                if (emp.getGarage() != null) {
                    if (emp.getRoles().getRoleName().equalsIgnoreCase(utils.EmployeeRole.ADMIN)) {
                        String uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + getServletContext().getContextPath();
                        Collection<GarageStatus> garageSlots = GarageImp.getInstance().getGarageStatusList(emp.getGarage().getGarageId());
                        System.out.println("size is " + garageSlots.size());
                        String imageURL = GarageImp.getInstance().getImagePath(emp.getGarage().getGarageId());
                        request.setAttribute("imageURL", imageURL);
                        request.getSession().setAttribute("garageSlots", garageSlots);
                        request.getSession().setAttribute("uri", uri);
                        request.getRequestDispatcher("updateslotsstatus.jsp").forward(request, response);
                    }
                }else
                {
                    response.sendRedirect(request.getHeader("Referer"));
                
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
