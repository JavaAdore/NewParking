<jsp:include page="headers/checkingPage.jsp"/>
<body>

    <script src="js/circle.js"></script>
    <script>

    function myFunction()
    {
        Circles.create({
            id: 'circles-1',
            percentage: '${report.getAvrageOrConsumption()}'*100,
            radius: 150,
            width: 10,
            number: '${report.getAvrageOrConsumption()}'*100,
            text: '%',
            colors: ['#D3B6C6', '#4B253A'],
            duration: 400
        });
    }

    </script>
<center>
    <br><br>
    <div id="canvas">

        <div class="circle" id="circles-1"></div>

    </div>
</center>
<script>
    myFunction();
</script>