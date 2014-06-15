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
    .highlyConsumed
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
            var itemClass;


        <c:choose>
            <c:when test="${slot.value.getAvrageOrConsumption()>.08}">
            itemClass = "highlyConsumed";
            </c:when>
            <c:when test="${slot.value.getAvrageOrConsumption()>.06}">
            itemClass = "mediumConsumed";
            </c:when>
            <c:otherwise>
            itemClass = "rearlyConsumed";
            </c:otherwise>
        </c:choose>

            var status = "";
            var inner = "<div class=" + itemClass + " style=' left:${slot.key.getX()}px;top:" + y + "px ;position:absolute;  '  >" +${slot.value.getHours()} + "</div>";
            $('#mainDiv').append(inner);

    </c:forEach>


            setActive('#viewDetailedReport');

</script>
<div class='highlyConsumed' style='position:relative;font-size: 20px; width:100%x;height:40;'>Highly Consumed</div>
<div class="mediumConsumed" style='position:relative;font-size: 20px;width:100%px;height:40;'>Medium Consumed</div>
<div class="rearlyConsumed" style='position:relative;font-size: 20px;width:100%px; height:40; '>Rear Consumed</div>
<div id="mainDiv">

    <img id="background"/>
</div>









