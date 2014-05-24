4<%-- 
    Document   : clientRegistration
    Created on : Apr 2, 2014, 9:57:42 PM
    Author     : orcl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="UserRegistrationHandler">
            <table>
                <tr>
                    <td>
                        userName
                    </td>
                    <td>
                        <input type="text" name="userName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        firstName
                    </td>
                    <td>
                        <input type="text" name="firstName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        lastName
                    </td>
                    <td>
                        <input type="text" name="lastName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        email
                    </td>
                    <td>
                        <input type="email" name="email"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        password
                    </td>
                    <td>
                        <input type="password" name="password"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        confirm password
                    </td>
                    <td>
                        <input type="password" name="confirmPassword"/>
                    </td>
                </tr>
                <tr>

                    <td>
                        gender
                    </td>
                    <td>
                        <input type="radio" name="gender" value="male">Male<br>
                        <input type="radio" name="gender" value="female">Female

                    </td>
                </tr>
                <tr>

                    <td>
                        birthdate
                    </td>
                    <td>
                        <input type="date" name="birthDate" >


                    </td>
                </tr>
                <tr>

                    <td>
                        mobile
                    </td>
                    <td>
                        <input type="phone" name="mobileNumber"/>

                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type ="submit"/>
                    </td>
                </tr>


        </form>
    </table>
</body>
</html>
