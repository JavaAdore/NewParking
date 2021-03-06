<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="headers/checkingPage.jsp"/>
<head>
    <jsp:include page="headers/header.jsp"/>
    <noscript>
    <link rel="stylesheet" href="css/5grid/core.css" />
    <link rel="stylesheet" href="css/5grid/core-desktop.css" />
    <link rel="stylesheet" href="css/5grid/core-1200px.css" />
    <link rel="stylesheet" href="css/5grid/core-noscript.css" />
    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="css/style-desktop.css" />
    </noscript>
    <noscript>
    <meta http-equiv="refresh" content="0; url=enablejavascript.jsp" />
    </noscript>
    <link rel = "stylesheet" href = "css/mycss/jquery.ui.all.css" >
    <link rel="stylesheet" href="css/mycss/demos.css">
    <title>Parking System</title>
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
    <script src="js/reportHandlers.js"></script>
    <script src="js/jQuery.js"></script>
    <link rel = "stylesheet" href = "css/mycss/jquery.ui.all.css" >
    <link rel="stylesheet" href="css/mycss/demos.css">
    <noscript>
    <meta http-equiv="refresh" content="0; url=enablejavascript.jsp" />
    </noscript>
    <link rel = "stylesheet" href = "css/mycss/jquery.ui.all.css" >
    <link rel="stylesheet" href="css/mycss/demos.css">
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
    <script src="js/customValidator.js"></script>
    <script>


        $(document).ready(function()
        {


            $.ajax({url: "InitializeReportDates", async: false, success: function(result)
                {

                    if (result.length > 1)
                    {
                        dateInitializer();
                       $("#feedback").load("minMaxDate.jsp");

                    } else
                    {
                        $("#feedback").html(" Sorry , No available reports related to your garage yet");
                    }
                }});


        }

        );
        function dateInitializer()
        {

            $("#fromPeriod").datepicker({maxDate: new Date('${maxDate.getYear()},${maxDate.getMonth()},${maxDate.getDay()}'), minDate: new Date('${minDate.getYear()},${minDate.getMonth()},${minDate.getDay()}')});
                    $("#toPeriod").datepicker({maxDate: new Date('${maxDate.getYear()},${maxDate.getMonth()},${maxDate.getDay()}'), minDate: new Date('${minDate.getYear()},${minDate.getMonth()},${minDate.getDay()}')});
                        }

                        setActive('#viewReport');
    </script>
</head>
<body>
    <div id="page-wrapper">
        <div id="page-bgtop">   
            <div id="page-bgbtm">
                <div id="page" class="grid-layout">
                    <center>
                        <div id="page-content-wrapper">

                            <span id="feedback"></span>


                            <table>
                                <tr>
                                    <td rowspan="3">
                                        <div id="inActive">

                                        </div>
                                    </td>
                                    <td>
                                        From
                                    </td>
                                    <td>
                                        <input  readonly id='fromPeriod' type='text' onchange="isAReportDate('#fromPeriod', '#fromDateError')" />
                                    </td>    

                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <span id="fromDateError"></span> 
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        To 
                                    </td>
                                    <td>
                                        <input readonly id='toPeriod' type='text' onchange="isAReportDate('#toPeriod', '#toDateError')" />
                                    </td>  

                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <span id="toDateError"></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3">

                                <center>  <input type = 'button'  id="myButton4" value = 'view report' onclick="viewReport('#fromPeriod', '#toPeriod', '#fromDateError', '#toDateError')" /></center>

                                </td>
                                </tr>
                            </table>
                           


                            <div id="reportBodyDiv">

                            </div>

                            <div id="theChart"></div>

                    </center>
                </div>
            </div>
        </div>
    </div>
