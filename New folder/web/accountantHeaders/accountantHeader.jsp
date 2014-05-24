<%-- 
    Document   : accountantHeader
    Created on : Apr 6, 2014, 11:14:33 PM
    Author     : orcl
--%>

<div id="header-wrapper">
    <header id="header">
        <jsp:include page="../ServiceProviderHeader/checkingPage.jsp"></jsp:include>
        <jsp:include page="../signoutForm.jsp"></jsp:include>

        <div id="menu-wrapper">
            <div class="5grid-layout">
                <div class="row">
                    <div class="12u" id="menu">
                        <nav class="mobileUI-site-nav">
                            <ul>
                                <li class="current_page_item"><a href="accountant.jsp">View Reports</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </header>
</div>