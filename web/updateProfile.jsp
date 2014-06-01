<%@page import="pojo.Employees"%>
<%@page import="utils.EmployeeWrapper"%>
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
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <script src="css/5grid/jquery.js"></script>
        <script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
        <noscript>
        <meta http-equiv="refresh" content="0; url=enablejavascript.jsp"/>
        </noscript> 
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
        </script>
    </head><body>
        <jsp:include page="headers/header.jsp"/>
        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">


                           
                            <center>   <form method="post" action="UpdateProfileHandler"  onsubmit="return myValidator()">
                                    <table>
                                        <th colspan="2">
                                        <center> <c:out value="${error.getErrorBody()}"/></center>
                                        </th>
                                        <tr>
                                            <td>Email:</td>
                                            <td><input name="email" type="email" readonly required class="textbox" value="${employee.getEmail()}"  ></td>
                                        </tr>

                                        <tr>
                                            <td>Password:</td>
                                            <td><input id="password" name="password" type="text" required class="textbox" value="${employee.getPassword()}"></td>
                                        </tr>
                                        <tr>
                                            <td>Confirm Password:</td>
                                            <td><input id ="confirmPassword" name="confirmPassword" type="text" required  class="textbox" value="${employee.getPassword()}"></td>
                                        </tr>
                                        <tr>
                                            <td>Mobile Number:</td>
                                            <td><input name="mobileNumber" type="tel"  required class="textbox"></td>
                                        </tr>
                                        <tr>
                                            <td><a href="update.jsp"><input type="submit"  value="Save Changes" id="savechange"/></a></td>
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
