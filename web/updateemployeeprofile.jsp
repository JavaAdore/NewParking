<%@page import="pojo.Employees"%>
<%@page import="utils.EmployeeWrapper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<!--
        Aqueous: A responsive HTML5 website template by HTML5Templates.com
        Released for free under the Creative Commons Attribution 3.0 license (html5templates.com/license)
        Visit http://html5templates.com for more great templates or follow us on Twitter @HTML5T
-->
<html>
    <head>
        <title>Parking System</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <noscript>
        <meta http-equiv="refresh" content="0; url=enablejavascript.jsp"/>
        </noscript> 
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <script src="css/5grid/jquery.js"></script>
        <script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
        <script type="text/javascript">
            jQuery(document).ready(function($) {
                $(".scroll").click(function(event) {
                    event.preventDefault();
                    $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1200);
                });
            });

            function myValidator()
            {
                var pass = document.getElementById("password").value;
                var confirm = document.getElementById("confirmPassword").value;
                if (pass == confirm)
                {
                    return true;
                }
                alert("please confirm password");
                return false;
            }
            function submitMethod()
            {

                $(updateProfileForm).submit();

            }
        </script>
        <script src="js/customValidator.js"></script>
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
                                        <li class="current_page_item" ><a href="addadmin.jsp">Control Area</a></li>
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
                                <form method="post" action="UpdateProfileHandler" id="updateProfileForm"  onsubmit="return myValidator()">
                                    <table>
                                        <tr>
                                            <td>Email:</td>
                                            <td><input name="email" type="email" readonly required class="textbox" value="${currentEmployee.getEmail()}"  ></td>
                                        </tr>

                                        <tr>
                                            <td>Password:</td>
                                            <td><input id="password" name="password" type="password" required class="textbox" value="${currentEmployee.getPassword()}" onblur="isPassword('#password', '#passwordError')" ></td>
                                            <td><span id="passwordError"></span></td>

                                        </tr>
                                        <tr>
                                            <td>Confirm Password:</td>
                                            <td><input id ="confirmPassword" name="confirmPassword" type="password" required value="${currentEmployee.getPassword()}" class="textbox" onblur="areTheSame('#password', '#confirmPassword', '#confirmPasswordError')" ></td>
                                            <td><span id="confirmPasswordError"></span></td>
                                        </tr>
                                        <c:if test="${currentEmployee.getRoles().getRoleId()!=1}">

                                            <td>
                                                Garage 
                                            </td>
                                            <td>

                                                <select name="garage" id="garage">
                                                    <option value="-2" >No Garage</option>
                                                    <c:forEach items="${allGarages}" var="garage">
                                                        <option value="${garage.getGarageId()}" <c:if test="${currentEmployee.getGarage().getGarageId()==garage.getGarageId()}" > selected </c:if> >${garage.getTitle()}</option>
                                                    </c:forEach>
                                            </td>
                                            <tr>
                                                <td>
                                                    Active
                                                </td>
                                                <td>
                                                    <select name="isActive"  >
                                                        <option value="1">Active</option>
                                                        <option value="0"<c:if test="${currentEmployee.getActive()==0}" > selected </c:if> >Not Active</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                        </c:if>
                                        <tr>
                                            <td><input type="button"  value="Save Changes" onclick="submitMethod()"/></td>
                                        </tr>
                                    </table>
                                </form>





                            </center>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
