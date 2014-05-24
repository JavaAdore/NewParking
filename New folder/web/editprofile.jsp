<jsp:include page="adminHeaders/editProfileHeader.jsp"></jsp:include>

<%@page import="utils.EmployeeWrapper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Parking System</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <noscript>
        <meta http-equiv="refresh" content="0; url=enablejavascript.jsp"/>
        </noscript> 
        <script src="css/5grid/jquery.js"></script>
        <script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
        <script type="text/javascript">
            function submitMethod()
            {
                $('#updateProfileForm').submit();
            }
        </script>
    </head><body>

        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <center>
                                <form action ="UpdateProfileHandler" method="post" id="updateProfileForm">
                                    <table>
                                        <th colspan="2">
                                        <center> <c:out value="${error.getErrorBody()}"/></center>
                                        </th>
                                        <tr>
                                            <td>Email:</td>
                                            <td><input name="email" type="email" required readonly value ='${emp.getEmail()}'/> </td>
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

                                            <td><input type="button" id="myButton" value="Update" onclick="submitMethod()" /> </td>
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