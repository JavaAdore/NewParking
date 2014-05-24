<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<html>
    <head>
        <title>Parking System</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <script src="css/5grid/jquery.js"></script>
        <script src="css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none"></script>
        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
    </head><body>
        <div id="header-wrapper">
            <header id="header">
                <div class="5grid-layout">
                    <div class="row">
                        <div class="12u" id="logo"> <!-- Logo -->
                            <h1><a href="#" class="mobileUI-site-name">Parking System</a></h1>
                            <p>Control Area</p>
                        </div>
                    </div>
                </div>
                <div id="menu-wrapper">
                    <div class="5grid-layout">
                        <div class="row">
                            <div class="12u" id="menu">
                                <nav class="mobileUI-site-nav">
                                    <ul>
                                        <li><a href="login.html">Login</a></li>
                                        <li class="current_page_item"><a href="register.html">Register</a></li>

                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        </div>
        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <div class="row">
                                <div class="12u">
                                    <div id="banner"><a href="#"></a>
                                        <form action="LoginServlet" method="POST" id="loginform">
                                            <table>
                                                <tr>
                                                    <td>Email:</td>
                                                    <td><input type="text" name="email" /></td>
                                                </tr>
                                                <tr>
                                                    <td>Password:</td>
                                                    <td><input type="password" name="pass" /></td>
                                                </tr>
                                                <tr>
                                                    <td>Confirm Password:</td>
                                                    <td><input type="password" name="cpass" /></td>
                                                </tr>
                                                <tr>
                                                    <td>Mobile No:</td>
                                                    <td><input  name="mob" /></td>
                                                </tr>
                                                <tr>
                                                    <td>BirthDay:</td>
                                                    <td><input type="date" name="bday" /></td>
                                                </tr>
                                                <tr>
                                                    <td>Name:</td>
                                                    <td><input type="text" name="name" /></td>
                                                </tr>
                                                <tr>
                                                    <td>Job:</td>
                                                    <td><input type="text" name="job" /></td>
                                                </tr>
                                                <tr>
                                                    <td>Address:</td>
                                                    <td><input type="text" name="address" /></td>
                                                </tr>
                                                <tr>
                                                    <td>Credit:</td>
                                                    <td><input type="text" name="address" /></td>
                                                </tr>
                                                <tr>
                                                    <td><a href="index.html"><input type="button" value="Register" id="register"/></a></td>
                                                </tr>

                                            </table>
                                        </form>
                                    </div>
                                </div>
                            </div>


                        </div>


                    </div>
                </div>
            </div>

        </div>

        <div id="copyright" class="5grid-layout">
            <section>
                <p>&copy; Your Site Name | Images: <a href="http://fotogrph.com/">Fotogrph</a> | Design: <a href="http://html5templates.com/">HTML5Templates.com</a></p>
            </section>
        </div>
    </body>
</html> 