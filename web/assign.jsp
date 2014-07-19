<jsp:include page="headers/checkingPage.jsp"/>
<!DOCTYPE HTML>
<!--
        Aqueous: A responsive HTML5 website template by HTML5Templates.com
        Released for free under the Creative Commons Attribution 3.0 license (html5templates.com/license)
        Visit http://html5templates.com for more great templates or follow us on Twitter @HTML5T
-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="headers/header.jsp"/>
<html>
    <head>
        <title></title>
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
        <script src="js/validators.js"></script>
        <script>
            function submitForm()
            {
                if (isNotEmpty('#garage') && isNotEmpty('#admin'))
                    $('#assign').submit();

            }
            setActive('#assignEmp');
        </script>
    </head>
    <body class="column1">


        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <form action="AssignAdminServlet" method="Post" id="assign">
                                <center>            
                                    Select Admin
                                    <select name="admin" id="admin">

                                        <c:forEach items="${admins}" var="admin">
                                            <c:if test="${admin.getActive()==1}">
                                                <option value="${admin.getEmployeeId()}">${admin.getFirstName()}   ${admin.getLastName()} (   ${admin.getEmail()}   ) </option>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${(empty admins) || numberOfInActiveEmployees==admins.size()}">
                                            <option value=-1 >Currently there is unassigned  employees </option>
                                        </c:if> 
                                    </select>

                                    Select Garage 
                                    <select name="garage" id="garage">
                                        <c:forEach items="${allGarages}" var="garage">
                                            <c:if test="${garage.getEnabled()==1}">
                                                <option value='${garage.getGarageId()}'>${garage.getTitle()}</option>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${numberOfInActiveGarages==allGarages.size() || (empty allGarages)}">
                                            <option value="-1">currently their is no Active garages</option>
                                        </c:if>
                                    </select>

                                    <input type="button" value="Assign" id="removeMyButton1" onclick="submitForm()"/>
                                </center>

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
