        <jsp:include page="headers/header.jsp"/>

<%@page import="utils.EmployeeWrapper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>
        <title>Parking System</title>

        <script>
            function submitMethod()
            {

                if (areYouSure('#adminInfo', 'Are you sure that you want to delete ?'))
                {

                    $('#removeadminform').submit();

                }
            }
            setActive('#removeEmp');

        </script>
    </head><body class="column1">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <form method="post" action="RemoveAdminServlet" id="removeadminform">


                                <select  id='adminInfo' name='adminInfo'>

                                    <c:forEach items="${employees}" var="tempEmp">
                                        <c:if test="${tempEmp.getEmployeeId() != emp.getEmployeeId()}">
                                            <option value="${tempEmp.getEmployeeId()}">${tempEmp.getEmail()}</option>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${employees.size()==1}">
                                        <option value="-1">Currently their is no employees</option>
                                    </c:if>
                                </select>
                                <input type="button" id="myButton3" value="Delete Employee" onclick="submitMethod()" />

                            </form>
                            <center> <c:out value="${error.getErrorBody()}"/></center>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

