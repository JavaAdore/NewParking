<%-- 
    Document   : controlgarage
    Created on : Jun 30, 2014, 8:25:12 AM
    Author     : orcl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script src="css/repeatable-fields-master/repeatable-fields.js"></script>
        
        <script type="text/javascript" >

            (document).ready(
                    function()
                    {
                        jQuery(function() {
                            jQuery('.repeat').each(function() {
                                jQuery(this).repeatable_fields();
                            });
                        });
                    });
        </script>

        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="AdminControllingtables/viewfeedback.jsp"/>
    </body>
</html>
