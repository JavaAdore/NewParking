<%@page import="java.util.Collection"%>
<%@page import="pojo.GarageStatus"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="adminHeaders\updateslotstatusheader.jsp"/>
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

            function updateSlotAvailability(object, slotId)
            {


                var slotId = $(object).attr('id');
                var enabled;
                if ($(object).attr('checked'))
                {

                    enabled = 1;
                } else
                {

                    enabled = 0;
                }

                $.ajax(
                        {url: "${uri}/rest/update/slotAvailability", async: false, data: 'slotId=' + slotId + "&enabled=" + enabled, success: function(result)
                            {

                                var temp = '#' + slotId;
                                if ($(object).attr('checked'))
                                {
                                    $(temp).attr('checked', false);
                                    $(temp).prop("disabled", true);
                                } else
                                {
                                    $(temp).prop("disabled", false);
                                }
                            }
                        });
            }

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
                        {url: "${uri}/rest/update/slotStatus", async: false, data: 'slotid=' + slotId + "&status=" + status, success: function(result)
                            {
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
                        var checked2 = ${slot.getEnabled()};
                        var status = "";
                        var enabled = "";
                        if (checked == 1)
                        {
                            status = "checked";
                        }
                        if (checked2 == 0)
                        {

                            enabled = "checked";

                        }

                        var inner = "<div style=' left:${slot.getX()}px;top:" + y + "px ;position:absolute;width:100px;height:50px;background-color:yellow;'  ><input id=" +${slot.getSlotId()} + " type='checkbox' " + status + " id='${slot.getSlotId()}' onchange='updateStatus(this)' >busy</input><br><input type='checkbox' " + enabled + " id=" +${slot.getSlotId()} + " onchange='updateSlotAvailability(this," +${slot.getSlotId()} + ")' >Not Available</input>"+${slot.getSlotId()}+"</div>";
                        $('#mainDiv').append(inner);

                        if (checked2 == 0)
                        {
                            var temp = '#' + ${slot.getSlotId()};
                            $(temp).prop("disabled", true);


                        }
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