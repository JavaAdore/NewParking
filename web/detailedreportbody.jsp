<jsp:include page="headers/checkingPage.jsp"/>
<noscript>
<meta http-equiv="refresh" content="0; url=enablejavascript.jsp" />
</noscript>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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

<script src="js/customValidator.js"></script>

<noscript>
<meta http-equiv="refresh" content="0; url=enablejavascript.jsp"/>
</noscript> 
<style>
    /*    .highlyConsumed
        {
            position:absolute;
            background-color:greenyellow;
            height:${emp.getGarage().getSlotHeight()}px;
            width:${emp.getGarage().getSlotWidth()}px;
        }
        .mediumConsumed
        {
            position:absolute;
            background-color:plum;
            height:${emp.getGarage().getSlotHeight()}px;
            width:${emp.getGarage().getSlotWidth()}px;
        }
        .rearlyConsumed
        {
            position:absolute;
            background-color:#99ccff;
            height:${emp.getGarage().getSlotHeight()}px;
            width:${emp.getGarage().getSlotWidth()}px;
        }*/
    .slotSpan {
        -moz-box-shadow:inset 0px 4px 0px 0px #cae3fc;
        -webkit-box-shadow:inset 0px 4px 0px 0px #cae3fc;
        box-shadow:inset 0px 4px 0px 0px #cae3fc;
        background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #79bbff), color-stop(1, #4197ee) );
        background:-moz-linear-gradient( center top, #79bbff 5%, #4197ee 100% );
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#79bbff', endColorstr='#4197ee');
        background-color:#79bbff;
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
        border:1px solid #469df5;
        display:inline-block;
        color:#ffffff;
        font-family:Arial;
        font-size:15px;
        font-weight:bold;
        font-style:normal;

        height:${emp.getGarage().getSlotHeight()}px;
        width:${emp.getGarage().getSlotWidth()}px;
        text-decoration:none;
        text-shadow:1px 1px 0px #287ace;
    }
    .hourSpan
    {
        position:absolute;
        top:45%;
        left:45%;
        color:red;
    }

</style>

<script type="text/javascript">



    $(document).ready(function()

    {
        var path = '${uri}/images/${emp.getGarage().getMap().getMapUrl()}';
                $('#background').attr('src', path);
            });


    <c:forEach items="${detailedReport}" var="slot">

            var y = ${slot.key.getY()} + $('#mainDiv').position().top;

            var checked = ${slot.key.getStatus()};
            var itemClass = "slotSpan";


        
            var status = "";
            var inner = "<div class=" + itemClass + " style=' left:${slot.key.getX()}px;top:" + y + "px ;position:absolute;  '  ><span class='hourSpan'>"+${slot.value.getHours()}  + " Hour(s) </span></div>";
            $('#mainDiv').append(inner);

    </c:forEach>


            setActive('#viewDetailedReport');

</script>

<div id="mainDiv">

    <img id="background"/>
</div>









