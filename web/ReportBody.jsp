
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="js/jQuery.js"></script>
<script type="text/javascript">
    $(function()
    {

        var userMinDate = new Date(getStartDate());
        var userMaxDate = new Date(getToDate());


        if (userMinDate == "Invalid Date")
        {

            userMinDate = new Date('${minDate}');

        }
        if (userMaxDate == "Invalid Date")
        {

            userMaxDate = new Date('${maxDate}');
        }


        date1 = dateSwapper(userMinDate, userMaxDate, 0);

        date2 = dateSwapper(userMinDate, userMaxDate, 1);

      
        if (date1 > new Date(${minDate}));
        {
            
            date1 = new Date(${minDate});

        }
        if (date2 > new Date(${minDate}))
        {
            
            date2 = new Date(${minDate});

        }
        
        $('#minDate').html(dateToString(date1));
        $('#maxDate').html(dateToString(date2));
    });

    function dateSwapper(date1, date2, x)
    {

        switch (x)
        {
            case
                    1:
                if (date1 > date2)
                {

                    return  date1;
                } else
                    return  date2;
                break;
            case 0:
                if (date1 > date2)
                {
                    return date2;
                }
                return  date1;
                break;

        }


    }

    function dateToString(date)
    {

        var formattedDate = new Date(date);
        var d = formattedDate.getDate();
        var m = formattedDate.getMonth();
        m += 1;  // JavaScript months are 0-11
        var y = formattedDate.getFullYear();

        return (d + "-" + m + "-" + y);

    }


</script>
<script src="js/reportHandlers.js"></script>



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
