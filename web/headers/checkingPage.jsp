<%-- 
    Document   : checkingPage
    Created on : May 2, 2014, 4:23:22 AM
    Author     : orcl
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="js/jquery-1.5.1.min.js" ></script>
<link rel="stylesheet" href="css/CustomStyle.css" />
<script src="js/tableHandler.js"></script>


<script type="text/javascript" >
    if (getCookie('seal').length == 0)
    {
        window.location = "login.jsp";

    }

    function getCookie(cname)
    {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++)
        {
            var c = ca[i].trim();
            if (c.indexOf(name) === 0)
                return c.substring(name.length, c.length);
        }
        return "";
    }
    setInterval("checkCookie()", "50");
    function checkCookie()
    {
        if (getCookie('seal').length === 0)
        {
            window.location = "login.jsp";
        }



    }
</script>




<c:if test="${ empty  emp}">
    <meta http-equiv="refresh" content="0; url= login.jsp" />
</c:if>

