    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAOS.UserImp;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import pojo.Users;
import utils.Utils;

/**
 *
 * @author orcl
 */
public class clientLoginHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    JSONObject obj = new JSONObject();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {

            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            System.out.println(userName);
            System.out.println(password);
            Users user = UserImp.getInstance().login(userName, password);

            if (user == null) {
                obj.put("error", "-1");
            } else {
                obj.put("error", "0");
                obj.put("userId", user.getUserId());
                obj.put("userName", (user.getUserName() != null) ? user.getUserName() : " ");
                obj.put("firstName", (user.getFirstName() != null) ? user.getFirstName() : " ");
                obj.put("lastName", (user.getLastName() != null) ? user.getLastName() : " ");
                obj.put("email", (user.getEmail() != null) ? user.getEmail() : " ");
                obj.put("password", (user.getPassword() != null) ? user.getPassword() : " ");

                if (user.getBirthDate() != null) {
                    obj.put("birthDate", Utils.toString(user.getBirthDate()));
                } else {
                    obj.put("birthDate", " ");
                }
                obj.put("gender", (user.getGender() != null) ? user.getGender() : " ");
                obj.put("phone", (user.getPhone() != null) ? user.getPhone() : " ");
            }

        } finally {
            System.out.println(obj.toString());
            out.print(obj.toString());
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
