<%-- 
    Document   : reportsHeader
    Created on : Apr 6, 2014, 10:47:52 PM
    Author     : orcl
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../ServiceProviderHeader/checkingPage.jsp"></jsp:include>
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
                                <li class="current_page_item"><a href="editprofile.jsp" >Edit Profile</a></li>
                                <li><a href="addadminbyadmin.jsp">Add Emp</a></li>
                                <li><a href="RemoveAdminInitializer">Remove Emp</a></li>
                                <li><a href="UpdateSlotInitializer">update slots status</a></li>
                                <li><a href="viewdetailedreport.jsp">View Detailed Reports</a></li>
                                <li><a href="viewreport.jsp">View Reports</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </header>
</div>