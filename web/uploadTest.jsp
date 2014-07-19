<%-- 
    Document   : uploadTest
    Created on : Jul 3, 2014, 7:26:03 AM
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
        <form action = "UploadImageHandler" method = "POST" id = "uploadImageForm" enctype = "multipart/form-data" >
            <input type="file" />
            <input type="submit">
        </form>
    </body>
</html>
