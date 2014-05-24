<%-- 
    Document   : checkingPage
    Created on : May 2, 2014, 4:23:22 AM
    Author     : orcl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="js/jquery-1.5.1.min.js" ></script>

<script type="text/javascript" >
    if (getCookie('seal') == "")
    {
        history.back(-1);
    }
    function getCookie(cname)
    {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++)
        {
            var c = ca[i].trim();
            if (c.indexOf(name) == 0)
                return c.substring(name.length, c.length);
        }
        return "";
    }

</script>




<c:if test="${ empty  emp}">
    <meta http-equiv="refresh" content="0; url= login.jsp" />
</c:if>

