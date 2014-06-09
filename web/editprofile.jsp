
<%@page import="utils.EmployeeWrapper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <%!EmployeeWrapper emp;%>
    <%
        emp = (EmployeeWrapper) request.getSession().getAttribute("emp");
    %>
    <head>
        <title>Parking System</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <script src="css/5grid/jquery.js"></script>
        <script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
    </head><body>
        <div id="header-wrapper">
            <header id="header">
                <div class="5grid-layout">
                    <div class="row">
                        <div class="12u" id="logo"> <!-- Logo -->

                            <form action="LogOutServlet" method="Post">
                                <input id="gobutton" type="submit" value="Sign Out" />
                            </form>
                            <h1><a href="#" class="mobileUI-site-name">Parking System</a></h1>

                        </div>
                    </div>
                </div>
                <div id="menu-wrapper">
                    <div class="5grid-layout">
                        <div class="row">
                            <div class="12u" id="menu">
                                <nav class="mobileUI-site-nav">
                                    <ul>
                                        <li class="current_page_item"><a href="editprofile.jsp">Edit Profile</a></li>
                                        <li><a href="addadminbyadmin.jsp">Add Employee</a></li>
                                        <li><a href="removeadminbyadmin.jsp">Remove Employee</a></li>
                                        <li><a href="viewreport.jsp">View Reports</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        </div>
        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <center>
                                <form action ="UpdateProfileHandler" method="post">
                                    <table>
                                        <th colspan="2">
                                        <center> <c:out value="${error.getErrorBody()}"/></center>
                                        </th>
                                        <tr>
                                            <td>Email:</td>
                                            <td><input name="email" type="email" required readonly value ="<%= emp.getEmail()%>"/> </td>
                                        </tr>
                                        <tr>
                                            <td>New Password:</td>
                                            <td><input name="password" type="password" required  /> </td>
                                        </tr>
                                        <tr>
                                            <td>Confirm New Password:</td>
                                            <td><input name="confirmPassword" type="password" required /> </td>
                                        </tr>
                                        <tr>

                                            <td><input type="submit" id="myButton" value="Update" /> </td>
                                        </tr>
                                    </table>
                                </form>
                            </center>
                        </div>
                    </div>
                    <div id="copyright" class="5grid-layout">	
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>