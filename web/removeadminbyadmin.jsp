<jsp:include page="headers/checkingPage.jsp"/>
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
                                <table>
                                    <tr>
                                        <td>
                                    <center> <c:out value="${error.getErrorBody()}"/></center>
                                    </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <select  id='adminInfo' name='adminInfo'>

                                                <c:forEach items="${employees}" var="tempEmp">
                                                    <c:if test="${(tempEmp.getEmployeeId() != emp.getEmployeeId())&&tempEmp.getActive()==1}">
                                                        <option value="${tempEmp.getEmployeeId()}">${tempEmp.getEmail()}</option>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${numberOfInActiveEmployees==numberOfEmployees || (employees.size()==1)}">
                                                    <option value="-1">currently their is no active employees</option>

                                                </c:if>
                                            </select>
                                            <input type="button" id="myButton3" value="Deactivate" onclick="submitMethod()" />


                                        </td>
                                    </tr>                    
                                </table>  
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

