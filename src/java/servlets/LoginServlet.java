package servlets;

import DAOS.EmployeesImp;
import errors.ErrorMessage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojo.Employees;
import utils.EmployeeRole;
import utils.EmployeeWrapper;
import utils.Utils;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utils.checkSession(request);
        String email = request.getParameter("email");

        String password = request.getParameter("pass");

        /// database connectio
        Employees employee = EmployeesImp.getInstance().signIn(email, password);

        if (employee == null) {

            request.setAttribute("error", new ErrorMessage("Invaled email or password"));
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {

            EmployeeWrapper employeeWrapper = Utils.getEmployeeWrapper(employee);

            HttpSession session = request.getSession(true);

            session.setAttribute("loggedIn", "true");

            session.setAttribute("emp", employeeWrapper);

            String roleName = employeeWrapper.getRoles().getRoleName();

            if (roleName.equalsIgnoreCase(EmployeeRole.SERVICE_PROVIDER)) {

                response.sendRedirect("addadmin.jsp");

            } else if (roleName.equalsIgnoreCase(EmployeeRole.ADMIN)) {

                response.sendRedirect("editprofile.jsp");

            } else {
                response.sendRedirect("accountant.jsp");
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
