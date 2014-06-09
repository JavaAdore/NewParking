
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Parking System</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <script src="css/5grid/jquery.js"></script>
        <script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
    </head><body>
        <jsp:include page="adminHeaders\addemployeeheader.jsp"/>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <center>
                                <form action ="addAdminServlet" method="post">
                                    <table>
                                        <th colspan="2">
                                        <center> <c:out value="${error.getErrorBody()}" /></center>
                                        </th>
                                        <tr>
                                            <td>First Name:</td>
                                            <td><input name="firstName" type="text"   ></td>
                                        </tr>
                                        <tr>
                                            <td>Last Name:</td>
                                            <td><input name="lastName" type="text"   ></td>
                                        </tr>
                                        <tr>
                                            <td>Email:</td>
                                            <td><input name="email" type="email" required/> </td>
                                        </tr>
                                        <tr>
                                            <td>Password:</td>
                                            <td><input name="password" type="password" required /> </td>
                                        </tr>
                                        <tr>
                                            <td>Confirm Password:</td>
                                            <td><input name="confirmPassword" type="password" required /> </td>
                                        </tr>
                                        <tr>
                                            <td>gender:</td>
                                            <td><input name="gender" type="radio"  value="m"checked />male <br>
                                                <input name="gender" type="radio"  value="f"/> female <br>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Birthday:</td>
                                            <td><input name="birthdate" type="date" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" placeholder="yyyy-mm-dd" required /> </td>
                                        </tr>
                                        <tr>
                                            <td>Job:</td>
                                            <td><input name="userJob" type="text"  maxlength="20" /> </td>
                                        </tr>
                                        <tr>
                                            <td>Role:</td>
                                            <td>
                                                <select name="role">
                                                    <option value="2">Adminstrator</option>
                                                    <option value="3">Accountant</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="submit" id="myButton" value="Register" /> </td>
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