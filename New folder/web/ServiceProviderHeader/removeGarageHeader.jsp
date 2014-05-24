<%-- 
    Document   : removeGarageHeader
    Created on : Apr 6, 2014, 10:17:25 PM
    Author     : orcl
--%>

<noscript>
<meta http-equiv="refresh" content="0; url=enablejavascript.jsp" />
</noscript>

<div id="header-wrapper">
    <jsp:include page="checkingPage.jsp"></jsp:include>
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
                                <li ><a href="addadmin.jsp">Add Employee</a></li>
                                <li ><a href="removeadmin.jsp">Remove Employee</a></li>
                                <li><a href="addgarage.jsp">Add Garage</a></li>
                                <li><a href="drawgaragemap.jsp">Drawing Tool</a></li>
                                <li class="current_page_item"><a href="RemoveGarageHandler">Remove Garage</a></li>
                                <li><a href="AssignAdminHandler">Assign Employee To Garage</a></li>
                                <li><a href="update.jsp">Update Employees Profiles</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </header>
</div>
