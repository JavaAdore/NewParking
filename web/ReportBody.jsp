
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="js/jQuery.js"></script>
<script type="text/javascript">



</script>
<script src="js/reportHandlers.js"></script>
<script>
    $('#minDate').html($('#from').val());
    $('#maxDate').html($('#to').val());
</script>


<center>


    <c:out value="${emp.getGarage().getTitle()}"/>
    <HR>
    <table>
        Report of garage utilization

        <tr>
            <td>
                start date
            </td>
            <td>
                <span id="minDate"></span>
            </td>

        </tr>
        <tr>
            <td>
                max date
            </td>
            <td>
                <span id="maxDate"></span>
            </td>

        </tr>
        <tr>

        </tr>
        <tr>
            <td>
                Total of consumed hours 
            </td>
            <td>
                <c:out value="${report.getHours()}"/> Hour
            </td>
        </tr>
        <tr>
            <td>
                Hour rate
            </td>
            <td>
                <c:out value="${emp.getGarage().getHourRateInRush()}"/>  EGP per hour
            </td>
        </tr>
        <tr>
            <td>
                Average of consumption  
            </td>
            <td>
                <fmt:formatNumber type="percent" maxFractionDigits="2" value="${report.getAvrageOrConsumption()}" />  of total available hours 
            </td>
        </tr>
        <tr>
            <td>
                Total income
            </td>
            <td>
                <c:out value="${report.getIncome()}"/> EGP 
            </td>
        </tr>
        <tr>
            <td colspan="2">    
                <jsp:include page="chart.jsp" />
            </td>
        </tr>
    </table>

</center>
