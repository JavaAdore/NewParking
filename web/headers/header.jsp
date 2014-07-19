<%-- 
    Document   : Home
    Created on : Jun 1, 2014, 8:22:14 AM
    Author     : orcl
--%>
<link rel="shortcut icon" type="image/x-icon" href="js/icon.png" />`

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="css/CustomStyle.css" />

<noscript>
<meta http-equiv="refresh" content="0; url=enablejavascript.jsp" />
</noscript>
<link rel = "stylesheet" href = "css/mycss/jquery.ui.all.css" >
<link rel="stylesheet" href="css/mycss/demos.css">
<title>Parking System</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<script src="css/5grid/jquery.js"></script>
<script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
<script src="js/validators.js"></script>
<script src="js/customValidator.js"></script>
<script src = "js/jquery-1.8.3.js" ></script>
<script src="js/ui/jquery.ui.core.js"></script>
<script src="js/ui/jquery.ui.widget.js"></script>
<script src="js/ui/jquery.ui.datepicker.js"></script>
<script src="js/reportHandlers.js"></script>
<script src="js/jQuery.js"></script>
<link rel = "stylesheet" href = "css/mycss/jquery.ui.all.css" >
<link rel="stylesheet" href="css/mycss/demos.css">
<noscript>
<meta http-equiv="refresh" content="0; url=enablejavascript.jsp" />
</noscript>
<link rel = "stylesheet" href = "css/mycss/jquery.ui.all.css" >
<link rel="stylesheet" href="css/mycss/demos.css">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<script src="css/5grid/jquery.js"></script>
<script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
<script src="js/validators.js"></script>
<script src="js/customValidator.js"></script>
<script src = "js/jquery-1.8.3.js" ></script>
<script src="js/ui/jquery.ui.core.js"></script>
<script src="js/ui/jquery.ui.widget.js"></script>
<script src="js/ui/jquery.ui.datepicker.js"></script>
<script src="js/customValidator.js"></script>
<script src="css/ScrollableTable/scripts/jquery.min.js" type="text/javascript"></script>
<script src="css/ScrollableTable/scripts/jquery-makeTableScrollable.js" type="text/javascript"></script>

<noscript>
<link rel="stylesheet" href="css/5grid/core.css" />
<link rel="stylesheet" href="css/5grid/core-desktop.css" />
<link rel="stylesheet" href="css/5grid/core-1200px.css" />
<link rel="stylesheet" href="css/5grid/core-noscript.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/style-desktop.css" />
</noscript>

<script src="css/5grid/jquery.js"></script>
<script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
<style>
    .active
    {
        background-color:red;


    }
</style>

<script>
    function setActive(x)
    {
        $(x).parent().addClass('current_page_item');
    }
</script>




<div id="header-wrapper">
    <header id="header">
        <div class="5grid-layout">
            <div class="row">
                <div class="12u" id="logo"> <!-- Logo -->
                    <jsp:include page="../signoutForm.jsp"></jsp:include>

                        <h1><a href="#" class="mobileUI-site-name">Parking System</a></h1>

                    </div>
                </div>
            </div>
            <div id="menu-wrapper">
                <div class="5grid-layout">
                    <div class="row">
                        <div class="12u" id="menu">
                            <nav class="mobileUI-site-nav">
                                <ul>
                                <c:choose>
                                    <c:when test="${emp.getRoles().getRoleName()=='SP'}">
                                        <c:forEach items="${serviceProviderHeader}" var="myHeader">
                                            <li ><a  id="${myHeader.getId()}" href="${myHeader.getToPage()}" >${myHeader.getTitle()}</a></li>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${emp.getRoles().getRoleName()=='AD'}">
                                            <c:forEach items="${adminHeader}" var="myHeader">
                                            <li ><a  id="${myHeader.getId()}" href="${myHeader.getToPage()}"  >${myHeader.getTitle()}</a></li>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${emp.getRoles().getRoleName()=='AC'}">
                                            <c:forEach items="${accountantHeader}" var="myHeader">
                                            <li ><a  id="${myHeader.getId()}" href="${myHeader.getToPage()}">${myHeader.getTitle()}</a></li>
                                            </c:forEach>
                                        </c:when>

                                </c:choose>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </header>
</div>



