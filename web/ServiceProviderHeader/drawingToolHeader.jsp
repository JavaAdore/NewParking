<%-- 
    Document   : addAdminHeader
    Created on : Apr 6, 2014, 10:15:06 PM
    Author     : orcl
--%>
<noscript>
<meta http-equiv="refresh" content="0; url=enablejavascript.jsp" />
</noscript>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="checkingPage.jsp"></jsp:include>
    <div id="header-wrapper">

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
                            <li ><a href="addadmin.jsp">Add Emp</a></li>
                                <li ><a href="LoadAllEmployeesInitializer?toPage=removeadmin.jsp">Remove Emp</a></li>
                            <li><a href="addgarage.jsp">Add Garage</a></li>
                            <li><a href="LoadAllGaragesInitializer?toPage=updategarageinfo.jsp">Update Garage</a></li>
                            <li><a class="current_page_item" href="drawgaragemap.jsp">Drawing Tool</a></li>
                            <li><a href="LoadAllGaragesInitializer?toPage=removegarage.jsp">Remove Garage</a></li>
                            <li><a href="AssignAdminHandler">Assign Emp To Garage</a></li>
                            <li><a href="LoadAllEmployeesInitializer?toPage=update.jsp">Update Profile</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
