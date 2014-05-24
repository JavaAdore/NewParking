/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAOS.EmployeesDAO;
import DAOS.EmployeesImp;
import errors.ErrorMessage;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.Employees;
import pojo.Roles;
import utils.EmployeeRole;
import utils.EmployeeWrapper;
import utils.Utils;

/**
 *
 * @author orcl
 */
public class addAdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utils.checkCurrentUserStatus(request);
        Utils.checkSession(request);
        EmployeeWrapper me = (EmployeeWrapper) request.getSession().getAttribute("emp");

        String firstName = request.getParameter("firstName");

        String lastName = request.getParameter("lastName");

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        String confirmPassword = request.getParameter("confirmPassword");

        String gender = request.getParameter("gender");

        String birthdate = request.getParameter("birthdate");

        String role = request.getParameter("roles");

        EmployeeWrapper employeeWrapper = (EmployeeWrapper) request.getSession().getAttribute("emp");

        String roleName = employeeWrapper.getRoles().getRoleName();

        Employees employee = new Employees();

        employee.setFirstName(firstName);

        employee.setLastName(lastName);

        employee.setEmail(email);

        employee.setPassword(password);

        employee.setGender(gender);

        employee.setBirthDate(utils.Utils.totDate(birthdate));

        employee.setRoles(new Roles(Integer.parseInt(request.getParameter("role"))));

        if (roleName.equalsIgnoreCase(EmployeeRole.SERVICE_PROVIDER)) {

            employee.setGarage(null);

        } else {

            employee.setGarage(employeeWrapper.getGarage());
        }

        EmployeesImp employeesImp = EmployeesImp.getInstance();

        int result = employeesImp.addEmployee(me.getEmployeeId(), employee);
        switch (result) {
            case 0:
                request.setAttribute("error", new ErrorMessage(String.format("%s %s (%s) added", firstName, lastName, email)));
                break;
            case -2:
                request.setAttribute("error", new ErrorMessage(String.format("%s  is already exists", email)));
                break;
            case -1:
                request.setAttribute("error", new ErrorMessage(String.format("%looks like some error happend please contact adminstrator")));
                break;

        }

        if (roleName.equalsIgnoreCase(EmployeeRole.SERVICE_PROVIDER)) {

            request.getRequestDispatcher("addadmin.jsp").forward(request, response);

        } else {

            request.getRequestDispatcher("addadminbyadmin.jsp").forward(request, response);
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
