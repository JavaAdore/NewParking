
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Parking System</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <script src="css/5grid/jquery.js"></script>
        <script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
        </script>
        <script src="js/reportHandlers.js"></script>
        <link rel="stylesheet" href="css/BeatPicker.min.css"/>
        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/BeatPicker.min.js"></script>



    <div id="page-wrapper">
        <div id="page-bgtop">
            <div id="page-bgbtm">
                <div id="page" class="5grid-layout">
                    <center>
                        <div id="page-content-wrapper">


                            from :         <input id="from" type="date"  />  <br/>
                            to   :         <input id="to" type="date"/> <br/>
                            <input type="button" value="view report" onclick="viewReport()">

                            <div id="reportBodyDiv">

                            </div>
                            <div id="chartDiv">

                            </div>
                            <div id="theChart"></div>
                        </div>
                    </center>
                </div>
                <div id="copyright" class="5grid-layout">	
                </div>
            </div>
        </div>
    </div>