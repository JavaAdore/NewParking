<jsp:include page="headers/checkingPage.jsp"/>
<jsp:include page="headers/header.jsp"/>
<%-- 
    Document   : addgarage
    Created on : Mar 29, 2014, 9:09:49 PM
    Author     : orcl
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="utils.Utils"%>

<html>
    <head>
        <title>Parking System</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <noscript>
        <link rel="stylesheet" href="css/5grid/core.css" />
        <link rel="stylesheet" href="css/5grid/core-desktop.css" />
        <link rel="stylesheet" href="css/5grid/core-1200px.css" />
        <link rel="stylesheet" href="css/5grid/core-noscript.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-desktop.css" />
        </noscript>
        <noscript>
        <meta http-equiv="refresh" content="0; url=enablejavascript.jsp"/>
        </noscript> 
        <script src="css/5grid/jquery.js"></script>
        <!--        <script src="js/theMap.js"></script>-->
        <script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>

        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>

        <script src="js/customValidator.js"></script>
        <style>

            span
            {

                width: 200px ;
                height: 40px;
            }
            .formTextField
            {
                width:200px;

            }
            html, body, #map-canvas {
                height: 100%;
                margin: 0px;
                padding: 0px
            }
            .controls {
                margin-top: 16px;
                border: 1px solid transparent;
                border-radius: 2px 0 0 2px;
                box-sizing: border-box;
                -moz-box-sizing: border-box;
                height: 32px;
                outline: none;
                box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
            }

            #city {
                background-color: #fff;
                padding: 0 11px 0 13px;
                width: 400px;
                font-family: Roboto;
                font-size: 15px;
                font-weight: 300;
                text-overflow: ellipsis;
            }

            #city:focus {
                border-color: #4d90fe;
                margin-left: -1px;
                padding-left: 14px;  /* Regular padding-left + 1. */
                width: 401px;
            }

            .pac-container {
                font-family: Roboto;
            }

            #type-selector {
                color: #fff;
                background-color: #4d90fe;
                padding: 5px 11px 0px 11px;
            }

            #type-selector label {
                font-family: Roboto;
                font-size: 13px;
                font-weight: 300;
            }


        </style>


        <script>

            var globalMap;
            var map;
            var mapOptions;
            var lat = ${currentGarage.getLat()};
            var lon = ${currentGarage.getLon()};
            var myMap = new google.maps.LatLng(lat, lon);
            function initialize() {

                mapOptions = {
                    zoom: 19,
                    center: new google.maps.LatLng(lat + .0009, lon - .0025),
                    mapTypeId: 'satellite'
                };
                map = new google.maps.Map(document.getElementById('map-canvas'),
                        mapOptions);

                var input = /** @type {HTMLInputElement} */(
                        document.getElementById('pac-input'));

                var types = document.getElementById('type-selector');
                map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
                map.controls[google.maps.ControlPosition.TOP_LEFT].push(types);

                var autocomplete = new google.maps.places.Autocomplete(input);
                autocomplete.bindTo('bounds', map);

                var infowindow = new google.maps.InfoWindow();
                var marker = new google.maps.Marker({
                    map: map,
                    anchorPoint: new google.maps.Point(0, -29)
                });

                google.maps.event.addListener(autocomplete, 'place_changed', function() {
                    infowindow.close();
                    marker.setVisible(false);
                    var place = autocomplete.getPlace();
                    if (!place.geometry) {
                        return;

                    }

                    // If the place has a geometry, then present it on a map.
                    if (place.geometry.viewport) {
                        map.fitBounds(place.geometry.viewport);
                    } else {
                        map.setCenter(place.geometry.location);
                        map.setZoom(17);  // Why 17? Because it looks good.
                    }
                    marker.setIcon(/** @type {google.maps.Icon} */({
                        url: place.icon,
                        size: new google.maps.Size(71, 71),
                        origin: new google.maps.Point(0, 0),
                        anchor: new google.maps.Point(17, 34),
                        scaledSize: new google.maps.Size(35, 35)
                    }));
                    marker.setPosition(place.geometry.location);
                    marker.setVisible(true);

                    var address = '';
                    if (place.address_components) {
                        address = [
                            (place.address_components[0] && place.address_components[0].short_name || ''),
                            (place.address_components[1] && place.address_components[1].short_name || ''),
                            (place.address_components[2] && place.address_components[2].short_name || '')
                        ].join(' ');
                    }
                    infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
                    infowindow.open(map, marker);
                });
                google.maps.event.addListener(map, 'zoom_changed', function() {
                    var zoomLevel = map.getZoom();

                    $('#latMap').val(map.getCenter().lat());
                    $('#lonMap').val(map.getCenter().lng());
                    infowindow.setContent('Zoom: ' + zoomLevel);
                });


                function setupClickListener(id, types) {
                    var radioButton = document.getElementById(id);
                    google.maps.event.addDomListener(radioButton, 'click', function() {
                        autocomplete.setTypes(types);
                    });

                }

                setupClickListener('changetype-all', []);
                setupClickListener('changetype-establishment', ['establishment']);
                setupClickListener('changetype-geocode', ['geocode']);
            }
            function mapDivClicked(event) {
                var target = document.getElementById('map-canvas'),
                        posx = event.pageX - target.offsetLeft,
                        posy = event.pageY - target.offsetTop,
                        bounds = map.getBounds(),
                        neLatlng = bounds.getNorthEast(),
                        swLatlng = bounds.getSouthWest(),
                        startLat = neLatlng.lat(),
                        endLng = neLatlng.lng(),
                        endLat = swLatlng.lat(),
                        startLng = swLatlng.lng();
                document.getElementById('latMap').value = startLat + ((posy / 350) * (endLat - startLat));
                document.getElementById('lngMap').value = startLng + ((posx / 500) * (endLng - startLng));
            }

            google.maps.event.addDomListener(window, 'load', initialize);

            function submitMethod()
            {

                if (isGarageNameAvailableForUpdate('#garageTitle',${currentGarage.getGarageId()}, '#garageTitleError') && isAnumber('#hourRateInRushHours', '#hourRateInRushHoursError', 0, 1000) && isImageAcceptNull('#file', '#fileError') && isAnumber('#width', '#widthError', 0, 3000) && isAnumber('#height', '#heightError', 0, 3000) && isAnumber('#ratio', '#ratioError', 0, 1) && isAnumber('#lngMap', '#lngMapError', -90, 90) && isAnumber('#latMap', '#latMapError', -90, 90))
                {
                    if (map.getMapTypeId() == "satellite")
                    {

                        if (map.getZoom() !== 19)
                        {

                            return false;
                        } else
                        {
                            $("#editGarageForm").submit();

                        }
                    } else
                    {
                        if (map.getZoom() !== 21)
                        {
                            return false;
                        } else
                        {
                            $("#editGarageForm").submit();

                        }

                    }

                }


            }


            setActive('#updateGarage');

        </script>

    </head>

    <body class = "column2" >



        <div id = "page-wrapper" >
            <div id = "page-bgtop" >
                <div id = "page-bgbtm" >
                    <div id = "page" class = "5grid-layout" >
                        <div id = "page-content-wrapper" >




                            <div id="map-canvas" onclick="mapDivClicked(event)">

                            </div>
                            <input id="pac-input"  class="controls" type="text"  placeholder="Enter a location"/>

                            <center>

                                <form action = "UpdateGarageHandler" method = "POST" id = "editGarageForm" enctype = "multipart/form-data" > 
                                    <table>
                                        <tr>
                                            <th colspan = "2" >
                                                <c:out value="${error.getErrorBody()}"/>
                                            </th>
                                        </tr>
                                        <tr>
                                            <td colspan = "2" >
                                            </td>
                                        </tr>
                                        <tr>
                                            <td > Garage Title </td>
                                            <td > <input id="garageTitle" required  type = "text"  value="${currentGarage.getTitle()}" name = "title" onblur="isGarageNameAvailableForUpdate('#garageTitle',${currentGarage.getGarageId()}, '#garageTitleError')" /> </td>
                                            <td>  <span id="garageTitleError" > </span></td>


                                        </tr>
                                        <tr>                                            <td> Hour rate in rush hours </td>                                            <td> <input id="hourRateInRushHours" required type = "text" value="${currentGarage.getHourRateInRush()}" name = "hourRateInRushHours" onblur="isAnumber('#hourRateInRushHours', '#hourRateInRushHoursError', 0, 1000)"/> </td>
                                            <td>  <span id="hourRateInRushHoursError" > </span></td>
                                        </tr>

                                        <tr>
                                            <td > Map </td>
                                            <td >
                                                <input type = "file" name = "file" id="file" required onchange="isImageAcceptNull('#file', '#fileError')" />

                                            </td>
                                            <td>
                                                <span id="fileError"></span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td > Ratio </td>
                                            <td >
                                                <input type = "text" name = "ratio"  id="ratio" value="${currentGarage.getMap().getRatio()}" required onblur="isAnumber('#ratio', '#ratioError', 0, 1)"/>
                                            </td>
                                            <td>  <span id="ratioError" > </span></td>

                                        </tr>
                                        <tr>
                                            <td > width </td>
                                            <td >
                                                <input id="width" required type = "text" name = "width" value="${currentGarage.getMap().getWidth()}" onblur="isAnumber('#width', '#widthError', 0, 3000)"/>
                                            </td>
                                            <td>
                                                <span id="widthError"></span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td > Height </td>
                                            <td >
                                                <input id="height" type = "text" required name = "height" value="1" onblur="isAnumber('#height', '#heightError', 0, 3000)"/>
                                            </td>
                                            <td> <span id="heightError"> </span></td>
                                        </tr>
                                        <tr>
                                            <td > Unit </td>
                                            <td >
                                                <select name = "unit" >
                                                    <option value = "m" > Meter </option>
                                                    <option value = "f" > Feet </option>
                                                    <option value = "i" > Inch </option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td > Latitude </td>
                                            <td > <input  type = "text"   name = "lat" id = "latMap" value="${currentGarage.getLat()}" readonly  onblur="isAnumber('#latMap', '#latMapError', -90, 90)"/> </td>
                                            <td> <span id="latMapError"> </span></td>

                                        </tr>
                                        <tr>
                                            <td > Longitude </td>
                                            <td > <input type = "text"  name = "lon" id = "lngMap" value="${currentGarage.getLon()}" readonly onblur="isAnumber('#lngMap', '#lngMapError', -90, 90)"/> </td>
                                            <td> <span id="lngMapError"> </span></td>

                                        </tr>
                                        <tr>
                                            <td>
                                                Active
                                            </td>
                                            <td>
                                                <select name="isActive"  >
                                                    <option value="1">Active</option>
                                                    <option value="0"<c:if test="${currentGarage.getEnabled()==0}" >selected</c:if>>Not Active</option>

                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td > <input type = "button" value = "update" id = "myButton1" onclick = "submitMethod()" /> </td>
                                        </tr>

                                    </table>
                                </form>
                            </center>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
