<jsp:include page="headers/checkingPage.jsp"/>
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
 <HR>
    <c:choose>
        <c:when test="${report.getHours()!=null}">


            <span id="garageName">
                <c:out value="${emp.getGarage().getTitle()}"/>
            </span>
            <br>
           
            <table cellpadding="10">
                
                               <tr>
                    <td class="blue">
                        Total of consumed hours 
                    </td>
                    <td class="green">
                           <c:out value="${report.getHours()}"/> Hour
                    </td>
                </tr>
                <tr>
                    <td class="blue">
                        Hour rate
                    </td>
                    <td class="green">
                          <c:out value="${emp.getGarage().getHourRateInRush()}"/>  EGP per hour
                    </td>
                </tr>

                <tr>
                    <td class="blue">
                        Total income
                    </td>
                    <td class="green">
                        <c:out value="${report.getIncome()}"/> EGP 
                    </td>
                </tr>
                <tr>
                    <td colspan="2">    
                        <jsp:include page="chart.jsp" />
                    </td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            Sorry No available report for your garage yet
        </c:otherwise>
    </c:choose>

</center>
