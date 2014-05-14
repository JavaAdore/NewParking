<%@page import="java.util.Collection"%>
<%@page import="pojo.GarageStatus"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="adminHeaders\addemployeeheader.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Parking System</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <script src="css/5grid/jquery.js"></script>
        <script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
        <script src="js/customValidator.js"></script>
        <style>
            span{width:100px;}
        </style>
        <noscript>
        <meta http-equiv="refresh" content="0; url=enablejavascript.jsp"/>
        </noscript> 

        <style>
            .slot
            {
                position:absolute;
                color:red;
            }
        </style>

        <script type="text/javascript">


            function updateStatus(object)
            {
                var slotId = $(object).attr('id');
                var status;
                if ($(object).attr('checked'))
                {

                    status = 1;
                } else
                {

                    status = 0;
                }

                $.ajax(
                        {url: "${uri}/rest/slotStatus", async: false, data: 'slotid=' + slotId + "&status=" + status, success: function(result)
                            {

                                alert(result);

                            }
                        });

            }
            $(document).ready(function()

            {
                var path = '${uri}/images/${imageURL}';
                        $('#background').attr('src', path);
                    });

                    function myFunction()
                    {
            <c:forEach items="${garageSlots}" var="slot">
                        var y = ${slot.getY()} + $('#mainDiv').position().top;

                        var checked = ${slot.getStatus()};
                        alert(checked);
                        var status = "";
                        if (checked == 1)
                        {
                            status="checked";

                        }

                        var inner = "<div style=' left:${slot.getX()}px;top:" + y + "px ;position:absolute;' ><input type='checkbox' "+status+" id='${slot.getSlotId()}' onchange='updateStatus(this)' ></input></div>";

                        $('#mainDiv').append(inner);
            </c:forEach>
                    }

        </script>
    </head>
    <body onload="myFunction()">

        <div id="mainDiv">
            <img id="background"/>
        </div>










    </body>
</html>