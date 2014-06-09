<%@page import="utils.*"%>
<!DOCTYPE HTML>
<!--
        Aqueous: A responsive HTML5 website template by HTML5Templates.com
        Released for free under the Creative Commons Attribution 3.0 license (html5templates.com/license)
        Visit http://html5templates.com for more great templates or follow us on Twitter @HTML5T
-->
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
         <script src="js/validators.js"></script>
        <link rel="stylesheet" href="css/style-desktop.css" />
        </noscript>
        <script src="css/5grid/jquery.js"></script>
        <script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
    </head><body class="column1">
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
                                        <li><a href="addadmin.jsp">Add Employee</a></li>
                                        <li><a href="removeadmin.jsp">Remove Employee</a></li>
                                        <li><a href="addgarage.jsp">Add Garage</a></li>
                                        <li class="current_page_item"><a href="removegarage.jsp">Remove Garage</a></li>
                                        <li><a href="assign.jsp">Assign Employee To Garage</a></li>
                                        <li><a href="update.jsp">Update Employees Profiles</a></li>
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
                            <form action="RemoveGarageHandler" method="post" id="removegarageform" onsubmit="return areYouSure('confirm deleting garage ?')">
                                <table>
                                    <th colspan="2">
                                    <center> <c:out value="${error.getErrorBody()}"/></center>
                                                    
                                    </th>
                                    <tr>
                                        <td>
                                            garages:<select name="garage" >
                                                <%= Utils.loadAllGaragesAsList("option")%> 
                                            </select>
                                        </td>
                                        <td>
                                            <input type="submit" value="Delete Garage" id="removeMyButton" />
                                        </td>
                                    </tr>
                                </table>
                            </form>
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
