/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAOS.GarageImp;
import errors.ErrorMessage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.DeleteGarageSchedule;
import utils.Utils;

/**
 *
 * @author orcl
 */
public class RemoveGarageHandler extends HttpServlet {

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
        Utils.checkCurrentUserStatus(request);
        Utils.checkSession(request);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String garage = request.getParameter("garage");
            if (garage != null) {

                int currentGarage = Integer.parseInt(garage);
                if (currentGarage != -1) {
                    int result = GarageImp.getInstance().addToDeletePlan(new DeleteGarageSchedule(currentGarage));
                    switch (result) {
                        case utils.Constants.SUCCESS:
                            request.setAttribute("error", new ErrorMessage(String.format("Garage deleted")));
                            break;

                        case utils.Constants.FAILED:
                            request.setAttribute("error", new ErrorMessage(String.format("looks like some error happend please contact adminstrator")));
                            break;
                        case utils.Constants.NOT_FOUND:
                            request.setAttribute("error", new ErrorMessage(String.format("No Such garage")));
                            break;

                    }

                }
            } else {
                throw new exceptions.DataNotFoundException();

            }
        } catch (Exception ex) {
            request.getRequestDispatcher("LoadAllGaragesInitializer?toPage=removegarage.jsp").forward(request, response);
        }
        request.getRequestDispatcher("LoadAllGaragesInitializer?toPage=removegarage.jsp").forward(request, response);
        out.close();

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
