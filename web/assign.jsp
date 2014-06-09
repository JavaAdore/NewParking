<!DOCTYPE HTML>
<!--
        Aqueous: A responsive HTML5 website template by HTML5Templates.com
        Released for free under the Creative Commons Attribution 3.0 license (html5templates.com/license)
        Visit http://html5templates.com for more great templates or follow us on Twitter @HTML5T
-->
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
    </head>
    <body class="column1">
        <jsp:include page="ServiceProviderHeader\assigAdminHeader.jsp"/>

        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <form action="AssignAdminServlet" method="Post">
                                <center>            
                                    Select Admin
                                    <select name="admin">

                                        <%= utils.Utils.getAllAdminsInfo(false)%> 

                                    </select>

                                    Select Garage 
                                    <select name="garage">
                                        <%= utils.Utils.loadAllGaragesAsList("option")%> 
                                    </select>

                                    <input type="submit" value="Assign" id="removeMyButton1"/>
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
