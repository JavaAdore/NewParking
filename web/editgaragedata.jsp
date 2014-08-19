

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>

        <jsp:include page="headers/checkingPage.jsp"/>
        <jsp:include page="headers/header.jsp"/>
        <noscript>
        <meta http-equiv="refresh" content="0; url=enablejavascript.jsp" />
        </noscript>

        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <script src="css/5grid/jquery.js"></script>
        <script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
        <script src="js/validators.js"></script>
        <script src="js/customValidator.js"></script>
        <script src = "js/jquery-1.8.3.js" ></script>
        <script src="js/ui/jquery.ui.core.js"></script>
        <script src="js/ui/jquery.ui.widget.js"></script>
        <script src="js/ui/jquery.ui.datepicker.js"></script>
        <link rel = "stylesheet" href = "css/mycss/jquery.ui.all.css" >
        <link rel="stylesheet" href="css/mycss/demos.css">
        <script type="text/javascript">
            setActive('#editGarageData');
        </script>
    </head>
    <body>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <center>
                                <table cellspacing="100" >

                                    <tr>
                                        <td>
                                            <jsp:include page="AdminControllingtables/contactTable.jsp" />
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <jsp:include page="AdminControllingtables/faxContact.jsp" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <jsp:include page="AdminControllingtables/emailContact.jsp" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <jsp:include page="AdminControllingtables/garagedescreption.jsp" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <jsp:include page="AdminControllingtables/garageWebsite.jsp" />
                                        </td>
                                        
                                    </tr>
                                     <tr>
                                        <td>
                                            <jsp:include page="AdminControllingtables/garageWebsite.jsp" />
                                        </td>
                                        
                                    </tr>
                                    
                                </table>
                            </center>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>




</html>