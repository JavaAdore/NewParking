

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
        <script src="css/ScrollableTable/scripts/jquery-makeTableScrollable.js" type="text/javascript"></script>
        <link href="css/ScrollableTable/Css/Westwind.css" rel="stylesheet" type="text/css" />

        <script type="text/javascript">
            setActive('#viewapplicationfeedback');
        </script>
        <script type="text/javascript">
            deleteButtonFormatingClass = "myButton4";
            deleteButtonMethod = "handleFeedback";
            $(document).ready(function() {
                makeTableScrollable();
                setInterval("loadFeedback()", "5000");
            });

            function makeTableScrollable()
            {
                $("#feedbackTable").makeTableScrollable({cssClass: "blackborder"});
            }

            function loadFeedback()
            {
                $.ajax(
                        {url: "GetFeedback", async: false, data: 'deleteButtonFormatingClass=' + deleteButtonFormatingClass + '&deleteButtonMethod=handleFeedback&identifier=p', success: function(result)
                            {
                                
                                
                                if (result.length == 0)
                                {
                                    freeTableRows("#feedbackTable");
                                } else
                                {
                                    freeTableRows("#feedbackTable");
                                    

                                    $("#feedbackTable").append(result);
                                    applyCss();
                                    applyJavascript();
                                }
                            }
                        });
            }
            
            function applyCss()
            {
                var temp = 1;
                $('#feedbackTable > tbody  > tr').each(function()
                {
                    if (temp % 2 == 1)
                    {
                        $(this).addClass("first");
                    }

                    else
                    {
                        $(this).addClass("second");


                    }
                    temp++;
                });

            }
            function applyJavascript()
            {
                $('textArea').attr("readonly","readonly") 
            }
            function loadTableHeader()
            {
                var header = "<thead><tr class='gridheader'><th style='width: 200px' >Feedback</th><th style='width: 100px' >Date</th> <th style='width: 100px' >Sender</th> <th style='width: 200px' >Action</th> </tr></thead>";
                $("#feedbackTable").html(header);
                return header;
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

        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <CENTER>
                                <table id="feedbackTable" style="width: 70%;" class="blackborder"  >
                                    <thead>
                                        <tr class='gridheader'>
                                            <th style='width: 100px' >Feedback</th>
                                            <th style='width: 100px' >Date</th> 
                                            <th style='width: 100px' >Sender</th> 
                                            <th style='width: 100px' >Action</th>
                                        </tr>
                                    </thead>

                                </table>
                            </CENTER>
                        </div>
                    </div>

                </div>
            </div>






