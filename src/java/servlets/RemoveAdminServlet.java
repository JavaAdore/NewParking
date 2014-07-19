package servlets;

import DAOS.*;
import errors.ErrorMessage;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.DeleteEmployeeSchedule;
import utils.EmployeeRole;
import utils.EmployeeWrapper;
import utils.Utils;

public class RemoveAdminServlet extends HttpServlet {

    private EmployeesImp employeesDao = EmployeesImp.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utils.checkCurrentUserStatus(request);
        Utils.checkSession(request);
        String res = request.getParameter("adminInfo");
        try {
            int adminId = Integer.parseInt(res);

            if (adminId != -1) {

                int result = employeesDao.addToDeletePlan(new DeleteEmployeeSchedule(adminId));
                switch (result) {
                    case 0:
                        request.setAttribute("error", new ErrorMessage(String.format("Employee deactivated")));
                        break;

                    default:
                        request.setAttribute("error", new ErrorMessage(String.format("%looks like some error happend please contact adminstrator")));
                        break;

                }

            }

            if (((EmployeeWrapper) request.getSession().getAttribute("emp")).getRoles().getRoleName().equals(EmployeeRole.SERVICE_PROVIDER)) {
                request.getRequestDispatcher("LoadAllEmployeesInitializer?toPage=removeadmin.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("RemoveAdminInitializer").forward(request, response);
            }
        } catch (Exception ex) {

            if (((EmployeeWrapper) request.getSession().getAttribute("emp")).getRoles().getRoleName().equals(EmployeeRole.SERVICE_PROVIDER)) {
                request.getRequestDispatcher("LoadAllEmployeesInitializer?toPage=removeadmin.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("RemoveAdminInitializer").forward(request, response);
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
