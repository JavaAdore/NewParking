package servlets;

import DAOS.EmployeesImp;
import DAOS.GarageImp;
import errors.ErrorMessage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.Employees;
import pojo.Garage;
import utils.EmployeeRole;
import utils.Utils;

public class AssignAdminServlet extends HttpServlet {

    EmployeesImp employeesDao = EmployeesImp.getInstance();

    GarageImp garageDao = GarageImp.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Utils.checkCurrentUserStatus(request);
        Utils.checkSession(request);
        String tempAdmin = request.getParameter("admin");
        String tempGarage = request.getParameter("garage");

        int adminId = Integer.parseInt(tempAdmin);

        int garageId = Integer.parseInt(tempGarage);
        if (adminId == -1 || garageId == -1) {
            response.sendRedirect("assign.jsp");
            return;
        }
        Employees employee = employeesDao.getEmployee(adminId);

        Garage garage = garageDao.getGarage(garageId);

        employee.setGarage(garage);

        int result = employeesDao.updateProfile(employee);
        switch (result) {
            case 0:
                request.setAttribute("error", new ErrorMessage(String.format(" %s %s (%s) assigned as an adminstrator for garage  %s", employee.getFirstName(), employee.getLastName(), employee.getEmail(), garage.getTitle())));
                break;

            case -1:
                request.setAttribute("error", new ErrorMessage(String.format("looks like some error happend please contact adminstrator")));
                break;

        }

        request.getRequestDispatcher("AssignAdminHandler").forward(request, response);

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
