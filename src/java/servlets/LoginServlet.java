package servlets;

import DAOS.EmployeesImp;
import DAOS.GarageImp;
import daosint.ReportsInterface;
import errors.ErrorMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojo.Employees;
import utils.EmployeeRole;
import utils.EmployeeWrapper;
import utils.*;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/"));
        if (request.getServletContext().getAttribute("uri") == null);
        {
            request.getServletContext().setAttribute("uri", uri);
        }
        // Utils.checkSession(request);
        String email = request.getParameter("email");

        String password = request.getParameter("pass");

        /// database connectio
        Employees employee = EmployeesImp.getInstance().signIn(email, password);

        if (employee == null) {

            request.setAttribute("error", new ErrorMessage("Invalid username or password"));
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else if (employee.getActive() == 0) {
            request.setAttribute("error", new ErrorMessage("Sorry your account has been deactivated , for more info please contact adminstrator"));
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {

            EmployeeWrapper employeeWrapper = Utils.getEmployeeWrapper(employee);
            HttpSession session = request.getSession(true);
            session.setAttribute("loggedIn", "true");
            session.setAttribute("emp", employeeWrapper);
            System.out.println(employee + " employeeee");
            System.out.println(employee + " employeeee Wrapper");
            System.out.println(employee.getRoles().getRoleName() + " employeeee Wrapper Role  Name");

            String roleName = employeeWrapper.getRoles().getRoleName();

            Cookie cookie = new Cookie("seal", "seal");
            response.addCookie(cookie);
            if (roleName.equalsIgnoreCase(EmployeeRole.SERVICE_PROVIDER)) {
                request.getSession().setAttribute("defaultPage", Constants.SERVICE_PROVIDER_DEFAULT_PAGE);
                response.sendRedirect("addadmin.jsp");

            } else if (roleName.equalsIgnoreCase(EmployeeRole.ADMIN)) {
                if (employee.getGarage() == null) {
                    ErrorMessage error = new ErrorMessage("sorry you has not assigned to a garage yet");
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else if (employee.getGarage().getEnabled() == 0) {
                    request.setAttribute("error", new ErrorMessage("Sorry the garage you are assigned to is deactevated"));
                    request.getRequestDispatcher("login.jsp").forward(request, response);

                } else {
                    initializeReport(request, response);
                    request.getSession().setAttribute("defaultPage", Constants.GARAGE_ADMIN_DEFAULT_PAGE);
                    response.sendRedirect("editprofile.jsp");
                }

            } else {
                if (employee.getGarage() == null) {
                    ErrorMessage error = new ErrorMessage("sorry you has not assigned to a garage yet");
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("accountant.jsp").forward(request, response);
                } else if (employee.getGarage().getEnabled() == 0) {
                    request.setAttribute("error", new ErrorMessage("Sorry the garage you are assigned to is deactevated"));
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    initializeReport(request, response);
                    request.getSession().setAttribute("defaultPage", Constants.GARAGE_ACCOUNTATNT_DEFAULT_PAGE);
                    response.sendRedirect("accountant.jsp");
                }

            }

        }

    }

    public void initializeReport(HttpServletRequest request, HttpServletResponse response) {
//        ArrayList<ReportsInterface> dailyHistoryRecord, monthlyHistoryRecord, yearlyHistoryRecord;
//        ArrayList<ArrayList<ReportsInterface>> merged = new ArrayList();
//        EmployeeWrapper emp = (EmployeeWrapper) request.getSession().getAttribute("emp");
//        Calendar c = Calendar.getInstance();
//        if (emp.getGarage() != null) {
//            dailyHistoryRecord = DailyHistoryReportImp.getInstance().getConsiceDailyHistory(emp.getGarage().getGarageId());
//            monthlyHistoryRecord = MonthlyHistoryReportImp.getInstance().getConsiceMonthlyHistory(emp.getGarage().getGarageId());
//            yearlyHistoryRecord = YearlyHistoryReportImp.getInstance().getConsiceYearlyHistory(emp.getGarage().getGarageId());
//            merged.add(dailyHistoryRecord);
//            merged.add(monthlyHistoryRecord);
//            merged.add(yearlyHistoryRecord);
//            ArrayList<ReportsInterface> mergeHistoryReports = Utils.mergeHistoryReports(merged);
//            Date[] extractMinMaxDate = Utils.extractMinMaxDate(garageId);
//            Date minDate = extractMinMaxDate[0];
//            request.getSession().setAttribute("minDate", Utils.populateDate(minDate));
//            Date maxDate = extractMinMaxDate[1];
//            if (emp.getRoles().getRoleName().equalsIgnoreCase(EmployeeRole.ADMIN)) {
//                request.getSession().setAttribute("detailed", Utils.detailed(emp.getGarage().getGarageId()));
//            }
//            String uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + getServletContext().getContextPath();
//            request.getSession().setAttribute("maxDate", Utils.populateDate(maxDate));
//            request.getSession().setAttribute("uri", uri);
//            String imageURL = GarageImp.getInstance().getImagePath(emp.getGarage().getGarageId());
//            request.getSession().setAttribute("imageURL", imageURL);
//            request.getSession().setAttribute("historyRecord", mergeHistoryReports);

//        }
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
