

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
        <script src="css/ScrollableTable/scripts/jquery-makeTableScrollable.js" type="text/javascript"></script>
        <link href="css/ScrollableTable/Css/Westwind.css" rel="stylesheet" type="text/css" />
        <script src ="js/tableHandler.js"></script>
        <script type="text/javascript">
            $(document).ready(function()
            {

                $("#contact").load("AdminControllingtables/contactTable.jsp");
                $("#fax").load("AdminControllingtables/faxContact.jsp");
                $("#email").load("AdminControllingtables/emailContact.jsp");
                $("#feedback").load("AdminControllingtables/feedback.jsp");
                $("#desc").load("AdminControllingtables/garagedescreption.jsp");
                $("#website").load("AdminControllingtables/garageWebsite.jsp");


                setActive('#editGarageData');

               
                initialize();
            });

             var tempFlag = false;
             var timeout;
            function initialize()
            {
                timeout = setTimeout("loadTables()", 7000);
            }
            function loadTables()
            {
                
                $("#contactTable").makeTableScrollable({cssClass: "blackborder"});
                $("#faxTable").makeTableScrollable({cssClass: "blackborder"});
                $("#emailTable").makeTableScrollable({cssClass: "blackborder"});

                //clearTimeout(timeout);
            }

        </script>
    </head>
    <body>

        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <center>
                                <table cellspacing="100" >
                                    <tr>
                                        <td>
                                            <input  class="button"   type="button"  value="Add Phone" onclick="addContactNumber()"/>
                                            <input  class="button" type="button"  value="Add Fax" onclick="addFaxNumber()"/>                                      
                                            <input  class="button"  type="button"  value="Add Email" onclick="addEmail()"/>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div id="contact">

                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <div id="fax">


                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div id="email">

                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div id="desc">


                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div id="website">

                                            </div>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td>
                                            <div id="feedback">

                                            </div>
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