<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="css/CustomStyle.css"/>
<span class="green" style="font-size: 15px;" >you can view reports for your garage from period <fmt:formatDate type="date" value="${fromDate}" /> to  <fmt:formatDate type="date" value="${toDate}" /> </span>
