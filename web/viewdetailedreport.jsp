<jsp:include page="adminHeaders/detaildreportheader.jsp"/>

<script src="css/5grid/jquery.js"></script>
<script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="js/reportHandlers.js"></script>
<script src="js/jQuery.js"></script>
<noscript>
<meta http-equiv="refresh" content="0; url=enablejavascript.jsp"/>
</noscript> 
<script>
    $(document).ready(function()
    {
        $.ajax({url: "ReportHandler", async: false, success: function(result)
            {
                if (result.length > 1)
                {
                    $("#fromSpan").html(" from :         <input id='from' type='date' " + result + " />");
                    $("#toSpan").html(" to :         <input id='to' type='date' " + result + " />");
                    $("#buttonSpan").html(" <input type = 'button' value = 'view report' />");
                    $('#buttonSpan').click(function()
                    {

                        viewDetailedReport($('#from').val(), $('#to').val());
                    });
                } else
                {
                    $("#fromSpan").html(" Sorry , No available reports related to your garage yet");
                }
            }});


    });
</script>
<style>
    .highlyConsumed
    {
        position:absolute;
        background-color:greenyellow;
    }
    .mediumConsumed
    {
        position:absolute;
        background-color:plum;
    }
    .rearlyConsumed
    {
        position:absolute;
        background-color:#99ccff;
    }


</style>

<title>JSP Page</title> 
</head>
<body>
    <div id="page-wrapper">
        <div id="page-bgtop">
            <div id="page-bgbtm">
                <div id="page" class="5grid-layout">
                    <center>
                        <div id="page-content-wrapper">

                            <span id="fromSpan">

                            </span>
                            <span id="toSpan">

                            </span>
                            <span id="buttonSpan">

                            </span>

                    </center>




                </div>

            </div>
            <div class='highlyConsumed' style='position:relative;font-size: 20px;'>Highly Consumed</div>
            <div class="mediumConsumed" style='position:relative;font-size: 20px;'>Medium Consumed</div>
            <div class="rearlyConsumed" style='position:relative;font-size: 20px;'>Rear Consumed</div>

        </div>
        <div id="reportBodyDiv">

        </div>
        <div id="chartDiv">

        </div>
        <div id="theChart"></div>
    </div>