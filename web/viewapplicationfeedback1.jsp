

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
        <noscript>
        <link rel="stylesheet" href="css/5grid/core.css" />
        <link rel="stylesheet" href="css/5grid/core-desktop.css" />
        <link rel="stylesheet" href="css/5grid/core-1200px.css" />
        <link rel="stylesheet" href="css/5grid/core-noscript.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-desktop.css" />
        </noscript>
        <link rel="stylesheet" href="css/mycss/demos.css">
        <script type="text/javascript">
            setActive('#viewapplicationfeedback');
        </script>
        <style type="text/css">
            .CSSTableGenerator {
                margin:0px;padding:0px;
                width:100%;
                box-shadow: 10px 10px 5px #888888;
                border:1px solid #000000;

                -moz-border-radius-bottomleft:14px;
                -webkit-border-bottom-left-radius:14px;
                border-bottom-left-radius:14px;

                -moz-border-radius-bottomright:14px;
                -webkit-border-bottom-right-radius:14px;
                border-bottom-right-radius:14px;

                -moz-border-radius-topright:14px;
                -webkit-border-top-right-radius:14px;
                border-top-right-radius:14px;

                -moz-border-radius-topleft:14px;
                -webkit-border-top-left-radius:14px;
                border-top-left-radius:14px;

            }.CSSTableGenerator table{
                border-collapse: collapse;
                border-spacing: 0;
                width:100%;
               
                margin:0px;padding:0px;
            }.CSSTableGenerator tr:last-child td:last-child {
                -moz-border-radius-bottomright:14px;
                -webkit-border-bottom-right-radius:14px;
                border-bottom-right-radius:14px;
            }
            .CSSTableGenerator table tr:first-child td:first-child {
                -moz-border-radius-topleft:14px;
                -webkit-border-top-left-radius:14px;
                border-top-left-radius:14px;
            }
            .CSSTableGenerator table tr:first-child td:last-child {
                -moz-border-radius-topright:14px;
                -webkit-border-top-right-radius:14px;
                border-top-right-radius:14px;
            }.CSSTableGenerator tr:last-child td:first-child{
                -moz-border-radius-bottomleft:14px;
                -webkit-border-bottom-left-radius:14px;
                border-bottom-left-radius:14px;
            }.CSSTableGenerator tr:hover td{

            }
            .CSSTableGenerator tr:nth-child(odd){ background-color:#aad4ff; }
            .CSSTableGenerator tr:nth-child(even)    { background-color:#ffffff; }.CSSTableGenerator td{
                vertical-align:middle;


                border:1px solid #000000;
                border-width:0px 1px 1px 0px;
                text-align:left;
                padding:7px;
                font-size:10px;
                font-family:Arial;
                font-weight:normal;
                color:#000000;
            }.CSSTableGenerator tr:last-child td{
                border-width:0px 1px 0px 0px;
            }.CSSTableGenerator tr td:last-child{
                border-width:0px 0px 1px 0px;
            }.CSSTableGenerator tr:last-child td:last-child{
                border-width:0px 0px 0px 0px;
            }
            .CSSTableGenerator tr:first-child td{
                background:-o-linear-gradient(bottom, #005fbf 5%, #003f7f 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #005fbf), color-stop(1, #003f7f) );
                background:-moz-linear-gradient( center top, #005fbf 5%, #003f7f 100% );
                filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#005fbf", endColorstr="#003f7f");	background: -o-linear-gradient(top,#005fbf,003f7f);

                background-color:#005fbf;
                border:0px solid #000000;
                text-align:center;
                border-width:0px 0px 1px 1px;
                font-size:14px;
                font-family:Arial;
                font-weight:bold;
                color:#ffffff;
            }
            .CSSTableGenerator tr:first-child:hover td{
                background:-o-linear-gradient(bottom, #005fbf 5%, #003f7f 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #005fbf), color-stop(1, #003f7f) );
                background:-moz-linear-gradient( center top, #005fbf 5%, #003f7f 100% );
                filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#005fbf", endColorstr="#003f7f");	background: -o-linear-gradient(top,#005fbf,003f7f);

                background-color:#005fbf;
            }
            .CSSTableGenerator tr:first-child td:first-child{
                border-width:0px 0px 1px 0px;
            }
            .CSSTableGenerator tr:first-child td:last-child{
                border-width:0px 0px 1px 1px;
            }

            .addButton {
                -moz-box-shadow:inset 0px 1px 0px 0px #caefab;
                -webkit-box-shadow:inset 0px 1px 0px 0px #caefab;
                box-shadow:inset 0px 1px 0px 0px #caefab;
                background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #77d42a), color-stop(1, #73ff00) );
                background:-moz-linear-gradient( center top, #77d42a 5%, #73ff00 100% );
                filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#77d42a', endColorstr='#73ff00');
                background-color:#77d42a;
                -webkit-border-top-left-radius:20px;
                -moz-border-radius-topleft:20px;
                border-top-left-radius:20px;
                -webkit-border-top-right-radius:20px;
                -moz-border-radius-topright:20px;
                border-top-right-radius:20px;
                -webkit-border-bottom-right-radius:20px;
                -moz-border-radius-bottomright:20px;
                border-bottom-right-radius:20px;
                -webkit-border-bottom-left-radius:20px;
                -moz-border-radius-bottomleft:20px;
                border-bottom-left-radius:20px;
                text-indent:0;
                border:1px solid #268a16;
                display:inline-block;
                color:#306108;
                font-family:Arial;
                font-size:15px;
                font-weight:bold;
                font-style:normal;
                height:50px;
                line-height:50px;
                width:100px;
                text-decoration:none;
                text-align:center;
                text-shadow:1px 1px 0px #aade7c;
            }
            .addButton:hover {
                background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #73ff00), color-stop(1, #77d42a) );
                background:-moz-linear-gradient( center top, #73ff00 5%, #77d42a 100% );
                filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#73ff00', endColorstr='#77d42a');
                background-color:#73ff00;
            }.addButton:active {
                position:relative;
                top:1px;
            }


            .deleteButton {
                -moz-box-shadow:inset 0px 1px 0px 0px #97c4fe;
                -webkit-box-shadow:inset 0px 1px 0px 0px #97c4fe;
                box-shadow:inset 0px 1px 0px 0px #97c4fe;
                background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #3d94f6), color-stop(1, #1e62d0));
                background:-moz-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
                background:-webkit-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
                background:-o-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
                background:-ms-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
                background:linear-gradient(to bottom, #3d94f6 5%, #1e62d0 100%);
                background-color:#4AA0E4; /*required for browsers that don't support gradients*/
                filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#3d94f6', endColorstr='#1e62d0',GradientType=0);
                background-color:#3d94f6;
                -moz-border-radius:6px;
                -webkit-border-radius:6px;
                border-radius:6px;
                border:1px solid #337fed;
                display:inline-block;
                cursor:pointer;
                color:#ffffff;
                font-family:arial;
                font-size:13px;
                font-weight:bold;
                padding:6px 24px;
                text-decoration:none;
                text-shadow:0px 1px 0px #1570cd;       
                top:30%;
                transition: all 1s ease-in-out;
            }
            .deleteButton:hover {
                background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #1e62d0), color-stop(1, #3d94f6));
                background:-moz-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
                background:-webkit-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
                background:-o-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
                background:-ms-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
                background:linear-gradient(to bottom, #1e62d0 5%, #3d94f6 100%);
                filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#1e62d0', endColorstr='#3d94f6',GradientType=0);
                background-color:#1e62d0;
                transform: rotateY(180deg);
            }
            .deleteButton:active {
                position:relative;
                top:1px;
                transform: translateY(7px);
            }

        </style>

        <script>
            deleteButtonFormatingClass = "deleteButton";
            deleteButtonMethod = "handleFeedback";
            $(document).ready(function()

            {
                setInterval("loadFeedback()", "5000");

            });

            function loadFeedback()
            {

                $.ajax(
                        {url: "GetFeedback", async: false, data: 'deleteButtonFormatingClass=' + deleteButtonFormatingClass + '&deleteButtonMethod=handleFeedback&identifier=p', success: function(result)
                            {
                                if (result.length > 2)
                                {
                                    $('#feedbackTable').html("<tr><td width=100px >Feedback</td><td>Date</td><td>Sender</td><td>Action</td></tr>" + result);
                                } else
                                {

                                    $('#feedbackTable').html("<tr><td>FeedBack</td><td>Date</td><td>Sender</td><td>Action</td></tr><tr><td><textarea readonly cols='100'>This Area specified to load application feedback</textarea></td><td></td><td></td><td></td></tr>");
                                }
                            }
                        });
            }

            function handleFeedback(x)
            {
                var result = window.confirm("Are you sure?")
                if (result)
                {

                    $.ajax(
                            {url: "FeedbackHandler", async: false, data: 'feedback=' + x, success: function(result)
                                {
                                    loadFeedback();

                                }
                            });
                }
            }
            loadFeedback();

        </script>
    </head>


    <body>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">

                            <div class="CSSTableGenerator" style="overflow:auto; height:300px;" >
                                

                                    <table id="feedbackTable" >
                                        <tr>
                                            <td>
                                                FeedBack

                                            </td>
                                            <td>
                                                Date
                                            </td>
                                            <td>
                                                Sender
                                            </td>
                                            <td>
                                                Action
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <textarea readonly cols="100">This Area specified to load application feedback</textarea>

                                            </td>
                                            <td>

                                            </td>
                                            <td>

                                            </td>
                                            <td>

                                            </td>
                                        </tr>
                                    </table>

                                </div> 

                            </div>
                        </div>

                    </div>
                </div>






