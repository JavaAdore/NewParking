
<%@page import="utils.EmployeeWrapper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<jsp:scriptlet>
    int myId = ((EmployeeWrapper) request.getSession().getAttribute("emp")).getEmployeeId();
    String admins = utils.Utils.loadAllEmployeesAsList(myId, "option");
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
        <script src=  "/js/validators.js"></script>
        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
    </head><body class="column1">
        <jsp:include page="ServiceProviderHeader/removeAdminHeader.jsp"/>
        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <form method="post" action="RemoveAdminServlet" id="removeadminform" onsubmit=" return areYouSure('adminInfo','Are you sure that you want to delete ?')">
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
