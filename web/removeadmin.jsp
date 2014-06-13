
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="ServiceProviderHeader/removeAdminHeader.jsp"/>
<%@page import= "utils.EmployeeWrapper" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<jsp:scriptlet>
    String admins = "";
    EmployeeWrapper employee = (EmployeeWrapper) request.getSession().getAttribute("emp");
    if (employee != null) {
        int myId = employee.getEmployeeId();
        admins = utils.Utils.loadAllEmployeesAsList(myId, "option");
    }
</jsp:scriptlet>

<!DOCTYPE HTML>

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
        <script type="text/javascript">
            
            
            
        </script>


        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
    </head><body class="column1">
        <script src="js/validators.js"></script>

        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <form method="post" action="RemoveAdminServlet"   onsubmit= "return areYouSure('#adminInfo', 'Are you sure that you want to delete ?')">
                                Choose Employee email:  
                                <select name="adminInfo"  id="adminInfo">

                                    <%= admins%>             

                                </select>

                                <input type="submit" id="removeMyButton" value="Delete Employee" />

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
