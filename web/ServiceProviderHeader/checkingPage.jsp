<%-- 
    Document   : checkingPage
    Created on : May 2, 2014, 4:23:22 AM
    Author     : orcl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>

        <script type="text/javascript" ></script>
        <script src="js/jquery-1.5.1.min.js" ></script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       

        <c:if test="${ empty  emp}">
            <script>alert('checking');</script>

            <meta http-equiv="refresh" content="0; url= login.jsp" />
        </c:if>
    </head>
    <body>


    </body>
</html>
