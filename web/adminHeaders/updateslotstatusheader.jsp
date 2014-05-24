<%-- 
    Document   : reportsHeader
    Created on : Apr 6, 2014, 10:47:52 PM
    Author     : orcl
--%>

<%-- 
    Document   : removeemployee
    Created on : Apr 6, 2014, 10:45:56 PM
    Author     : orcl
--%>

<div id="header-wrapper">
    <jsp:include page="../ServiceProviderHeader/checkingPage.jsp"></jsp:include>
        <header id="header">
        <jsp:include page="../signoutForm.jsp"></jsp:include>


        <div id="menu-wrapper">
            <div class="5grid-layout">
                <div class="row">
                    <div class="12u" id="menu">
                        <nav class="mobileUI-site-nav">
                            <ul>
                                <li ><a href="editprofile.jsp">Edit Profile</a></li>
                                <li><a href="addadminbyadmin.jsp">Add Employee</a></li>
                                <li ><a href="RemoveAdminInitializer">Remove Employee</a></li>
                                <li class="current_page_item"><a href="UpdateSlotInitializer">update slots status</a></li>
                                <li><a href="viewdetailedreport.jsp">View Detailed Reports</a></li>

                                <li ><a href="viewreport.jsp">View Reports</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </header>
</div>