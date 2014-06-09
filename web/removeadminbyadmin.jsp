
<%@page import="utils.EmployeeWrapper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>
        <title>Parking System</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <noscript>
        <link rel="stylesheet" href="css/5grid/core.css" />
        <link rel="stylesheet" href="css/5grid/core-desktop.css" />
        <link rel="stylesheet" href="css/5grid/core-1200px.css" />
        <link rel="stylesheet" href="css/5grid/core-noscript.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-desktop.css" />
        </noscript>
        <script src="css/5grid/jquery.js"></script>
        <script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
    </head><body class="column1">
        <jsp:include page="adminHeaders\removeemployee.jsp"/>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <form method="post" action="RemoveAdminServlet" id="removeadminform">
                                <select name="adminInfo" >
                                    <%
                                        EmployeeWrapper emp = (EmployeeWrapper) session.getAttribute("emp");
                                        String res = utils.Utils.loadAllEmployeesAsListByGarageId(emp.getGarage().getGarageId(), emp.getEmployeeId());
                                    %>             

                                    <%= res%>


                                </select>
                                <input type="submit" value="Delete Employee" />

                            </form>
                            <center> <c:out value="${error.getErrorBody()}"/></center>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="copyright" class="5grid-layout">
            <section>
            </section>
        </div>
    </body>
</html>

