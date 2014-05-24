<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>




<%@page import="java.util.Collection"%>
<%@page import="pojo.GarageStatus"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<script src="js/customValidator.js"></script>
<style>
    span{width:100px;}
</style>
<noscript>
<meta http-equiv="refresh" content="0; url=enablejavascript.jsp"/>
</noscript> 


<script type="text/javascript">



    $(document).ready(function()

    {
        var path = '${uri}/images/${imageURL}';
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

            var inner = "<div class=" + itemClass + " style=' left:${slot.key.getX()}px;top:" + y + "px ;position:absolute;width:40px;height:30px; '  >"+${slot.value.getHours()} +"</div>";
            $('#mainDiv').append(inner);

    </c:forEach>




</script>

<div id="mainDiv">

    <img id="background"/>
</div>









