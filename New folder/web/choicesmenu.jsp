<%-- 
    Document   : choicesmenu
    Created on : Apr 30, 2014, 3:31:43 PM
    Author     : orcl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/jQuery.js" ></script>
        <script src="js/reportHandlers.js"></script>
    </head>
    <body>
        <table>
            <tr>
                <td> Welcome To Parking Control Area </td>
                <td>
                    <select id="selection" >

                        <option value="d" selected>Daily</option>
                        <option value="m">Monthly</option>
                        <option value="y">Yearly</option>
                    </select>

                </td>
                <td>
                    <br>

                    <div id="fromDiv">

                    </div>
                    <br>

                    <div id="toDiv">

                    </div>

                </td>
            </tr>

        </table>
    </body>
</html>
