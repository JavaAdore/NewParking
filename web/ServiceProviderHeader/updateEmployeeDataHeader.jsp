<%-- 
    Document   : updateEmployeeDataHeader
    Created on : Apr 6, 2014, 10:19:23 PM
    Author     : orcl
--%>


<div id="header-wrapper>
     <jsp:include page="checkingPage.jsp"></jsp:include>

         <div class="grid-layout">
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
                        <li><a href="addadmin.jsp">Add Emp</a></li>
                                <li ><a href="LoadAllEmployeesInitializer?toPage=removeadmin.jsp">Remove Emp</a></li>
                        <li><a href="addgarage.jsp">Add Garage</a></li>
                        <li><a href="LoadAllGaragesInitializer?toPage=updategarageinfo.jsp">Update Garage</a></li>
                        <li><a href="drawgaragemap.jsp">Drawing Tool</a></li>
                        <li><a href="LoadAllGaragesInitializer?toPage=removegarage.jsp">Remove Garage</a></li>
                        <li><a href="AssignAdminHandler">Assign Emp To Garage</a></li>
                        <li class="current_page_item"><a href="LoadAllEmployeesInitializer?toPage=update.jsp">Update Profile</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
