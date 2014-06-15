<jsp:include page="headers/checkingPage.jsp"/>
<jsp:include page="headers/header.jsp"/>
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
            .icons
            {
                width:${emp.getGarage().getSlotWidth()/3}px;
                height:${emp.getGarage().getSlotHeight()/2}px;

            }

        </style>
        <noscript>
        <meta http-equiv="refresh" content="0; url=enablejavascript.jsp"/>
        </noscript> 



        <script type="text/javascript">

            var busy = "${uri}/CustomImages/redCar.png";
            var available = "${uri}/CustomImages/greenCar.png";
            var notEnabled = "${uri}/CustomImages/yellowCar.png";
            var backgroundImage;

            function updateSlotAvailability(object, slotId)
            {


                var slotId = $(object).attr('id');
                var enabled;
                if ($(object).attr('checked'))
                {

                    enabled = 0;
                } else
                {

                    enabled = 1;
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
                        var y = ${slot.getY()} + $('#background').position().top;

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
                        var inner = "<span class='slotSpan' style=' left:${slot.getX()}px;top:" + y + "px ;position:absolute;background-color:yellow;'  ><input  class='checkbox' id=" +${slot.getSlotId()} + " type='checkbox' " + status + " id='${slot.getSlotId()}' onchange='updateStatus(this)'/ ><img  class='icons' src='${uri}/CustomImages/busy.png' />" + '${slot.getSlotName()}' + "<input class = 'checkbox' type='checkbox' " + enabled + " id=" +${slot.getSlotId()} + " onchange='updateSlotAvailability(this," +${slot.getSlotId()} + ")' /><img class = 'icons' src='${uri}/CustomImages/unavailable.png' /></span>";

                        $('#mainDiv').append(inner);

                        if (checked2 == 0)
                        {
                            var temp = '#' + ${slot.getSlotId()};
                            $(temp).prop("disabled", true);


                        }
            </c:forEach>
                    }

                    setActive('#updateSlot');
        </script>
    </head>
    <body onload="myFunction()">

        <span id="mainDiv">
            <img id="background"/>
        </span>










    </body>
</html>