<%-- 
    Document   : updateempdata
    Created on : Mar 29, 2014, 11:39:43 PM
    Author     : Mahmoud Eltaieb
--%>


<%@page import="utils.Utils"%>
<%@page import="DAOS.EmployeesImp"%>
<!DOCTYPE HTML>
<!--
        Aqueous: A responsive HTML5 website template by HTML5Templates.com
        Released for free under the Creative Commons Attribution 3.0 license (html5templates.com/license)
        Visit http://html5templates.com for more great templates or follow us on Twitter @HTML5T
-->
<html>
    <head>
        <title>Parking system</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <noscript>
        <meta http-equiv="refresh" content="0; url=enablejavascript.jsp"/>
        </noscript> 
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
        <jsp:include page="ServiceProviderHeader/updateEmployeeDataHeader.jsp"/>

        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <center>
                                <form method="post" action="updateemployeeprofile.jsp" onsubmit="isNotEmpty(this)">
                                    "please choose employee do you want to update his / her data"
                                    <select name="employee">
                                        <%= Utils.loadAllEmployeesAsList("option")%> 
                                    </select>
                                    <input type="submit" value="update" id="removeMyButton"/>
                                </form>
                            </center>
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
