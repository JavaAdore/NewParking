<%-- 
    Document   : userLogin
    Created on : Apr 3, 2014, 11:43:18 AM
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
        <form action ="clientLoginHandler" method="post">
            <table>
                <tr>
                    <td>
                        userName
                    </td>
                    <td>
                        <input type ="text" name="userName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        password
                    </td>
                    <td>
                        <input type ="password" name="password"/>
                    </td>
                </tr>
                <tr>

                    <td colspan="2"> 
                        <input type ="submit" name="submit"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
