package servlets;

import DAOS.EmployeesImp;
import DAOS.GarageImp;
import static com.sun.jmx.snmp.EnumRowStatus.active;
import errors.ErrorMessage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.DeleteEmployeeSchedule;
import pojo.Employees;
import pojo.Garage;
import utils.EmployeeRole;
import utils.EmployeeWrapper;
import utils.Utils;

public class UpdateProfileHandler extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utils.checkCurrentUserStatus(request);
       
        EmployeeWrapper me = (EmployeeWrapper) request.getSession().getAttribute("emp");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        String garage = request.getParameter("garage");
        String isActive = request.getParameter("isActive");
        int result = -1;
        if (me.getRoles().getRoleName().equalsIgnoreCase(EmployeeRole.SERVICE_PROVIDER)) {

            result = EmployeesImp.getInstance().updateProfile(email, password, confirmPassword, garage, isActive);

        } else {

            result = EmployeesImp.getInstance().updateProfile(me.getEmployeeId(), password, confirmPassword);

        }

        switch (result) {

            case -1:
                request.setAttribute("error", new ErrorMessage("Looks Like some error happend please contact adminstrator"));
                break;
            case 0:
                request.setAttribute("error", new ErrorMessage("Data updated"));

                break;
        }

        switch (me.getRoles().getRoleName()) {
            case EmployeeRole.SERVICE_PROVIDER:
                request.getRequestDispatcher("LoadAllEmployeesInitializer?toPage=update.jsp").forward(request, response);
                break;
            case EmployeeRole.ADMIN:
                request.getRequestDispatcher("LoadAllEmployeesInitializer?toPage=editprofile.jsp").forward(request, response);
                break;

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
