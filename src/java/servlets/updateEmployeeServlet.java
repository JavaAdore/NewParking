package servlets;

import DAOS.EmployeesImp;
import errors.ErrorMessage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.Employees;
import pojo.Roles;
import utils.Utils;

public class updateEmployeeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Utils.checkCurrentUserStatus(request);
        Utils.checkSession(request);
        try {

            String firstName = request.getParameter("firstName");

            String lastName = request.getParameter("lastName");

            String email = request.getParameter("email");

            String password = request.getParameter("password");

            String birthdate = request.getParameter("birthdate");

            String role = request.getParameter("roles");

            Employees tempEmp = new Employees();
            tempEmp.setFirstName(firstName);
            tempEmp.setLastName(lastName);
            tempEmp.setPassword(password);
            tempEmp.setEmail(email);
            //tempEmp.setBirthDate(utils.Utils.toDate(birthdate));

            tempEmp.setRoles(new Roles(Integer.parseInt(role)));

            int result = EmployeesImp.getInstance().updateProfileByEmail(tempEmp);
            switch(result)
            {   
                case 0 :
                        request.setAttribute("error", new ErrorMessage("Update has been done successfully"));
                    break;
                    case -1 :
                                            request.setAttribute("error", new ErrorMessage("Looks like some error happend please contact adminstrator"));

                    break;
            }
            request.getRequestDispatcher("updateempdata.jsp").forward(request, response);

        } finally {
            out.close();
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
