
<jsp:include page="headers/checkingPage.jsp"/>

<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="headers/header.jsp"/>

<html>
    <head>

        <title>Parking System</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <script src="css/5grid/jquery.js"></script>
        <script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
        <script src="js/validators.js"></script>
        <script src="js/customValidator.js"></script>
        <script type="text/javascript">
            setActive('#drawingTool');
        </script>
        <noscript>
        <meta http-equiv="refresh" content="0; url=enablejavascript.jsp"/>
        </noscript> 
        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
    </head>
    <body>

    <center>
        <applet code="applets/DrawingTool.class" archive="Drawing_Tool_2.jar" width="1200"  height=650>
            <param name="ip" value="localhost" />
            <param name="parking" value="NewParking" />
            <param name="port" value="9999" />

        </applet>
    </center>
</body>
</html>



</html>






</html>



