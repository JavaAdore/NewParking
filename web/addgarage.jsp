<%-- 
    Document   : addgarage
    Created on : Mar 29, 2014, 9:09:49 PM
    Author     : orcl
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="utils.Utils"%>

<html>
    <head>
        <title>Parking System</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <noscript>
        <link rel="stylesheet" href="css/5grid/core.css" />
        <link rel="stylesheet" href="css/5grid/core-desktop.css" />
        <link rel="stylesheet" href="css/5grid/core-1200px.css" />
        <link rel="stylesheet" href="css/5grid/core-noscript.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-desktop.css" />
        </noscript>
        <script src="css/5grid/jquery.js"></script>
        <script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none">
            

        </script>
        <script>
            function submitMethod()
            {
                $('#loginform').submit();
            }
        </script>
        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
    </head>
    <body class="column2">
        <jsp:include page="ServiceProviderHeader\addGarageHeader.jsp"/>
        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <form action="AddGarageHandler" method="POST" id="loginform" enctype="multipart/form-data">

                                <table>
                                    <th colspan="2">
                                        <c:out value="${error.getErrorBody()}"/>
                                    </th>
                                    <tr>
                                        <td>Garage Title</td>
                                        <td><input type="text" name="title" /></td>
                                    </tr>
                                    <tr>
                                        <td>country</td>
                                        <td><input type="text" name="country"  value ="Egypt" readonly/></td>
                                    </tr>
                                    <tr>
                                        <td>city</td>
                                        <td><input type="text" name="city"  value ="Cairo" readonly/></td>
                                    </tr>

                                    <tr>
                                        <td>Hour rate in rush hours</td>
                                        <td><input type="number" name="slots"  value ="1" min="1"/></td>
                                    </tr>
                                    <tr>
                                        <td>Hour rate out of  rush hours</td>
                                        <td><input type="number" name="doors"  value ="1" min="1" readonly/></td>
                                    </tr>
                                    <tr>
                                        <td>Map</td>
                                        <td>
                                            <input type ="file" name="file"  required/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Ratio</td>
                                        <td>
                                            <input type ="text" name="ratio" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>width</td>
                                        <td>
                                            <input type ="number" name="width" min="1" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Height</td>
                                        <td>
                                            <input type ="number" name="height" min="1"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Unit</td>
                                        <td>
                                            <select name="unit">
                                                <option value="m" >Meter</option>
                                                <option value="f" >Feet</option>
                                                <option value="i" >Inch</option>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>

                                        <td>lon</td>
                                        <td><input type="text" name="lon"  /></td>
                                    </tr>
                                    <tr>
                                        <td>lat</td>
                                        <td><input type="text" name="lat"  /></td>
                                    </tr>
                                    <tr>
                                        <td><input type="button" value="Add" id="myButton1" onclick="submitMethod()"/></td>
                                        <td><input type="reset" value="Cancel" id="myButton2"/></td>
                                    </tr>
                                </table>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="copyright" class="5grid-layout">
            <section>
            </section>
        </div>
    </body>
</html>
