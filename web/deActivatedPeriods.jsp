<%-- 
    Document   : deActivatedPeriods
    Created on : Jun 29, 2014, 8:09:23 PM
    Author     : orcl
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


well I'm A test text in deactivation period


<c:if test="${ not empty emp.getGarage().getInActivePeriods()}" >
    ${emp.getGarage().getTitle()} has been deactivated through these periods
    <hr>
    <SELECT NAME="inActive" SIZE="4" MULTIPLE >
        <c:forEach items="${emp.getGarage().getInActivePeriods()}" var="period">
            <option> 
                <fmt:formatDate pattern="yyyy-MM-dd"  value="${period.getFromDate()}" /> - <fmt:formatDate pattern="yyyy-MM-dd"  value="${period.getToDate()}" />
            </option>
        </c:forEach>
    </SELECT>

</c:if>
