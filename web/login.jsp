
<!DOCTYPE html>
<html>


    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <head>
        <script>
            var toPage = '${defaultPage}';
            if (toPage.length > 0)
            {
                window.location.replace(toPage);
            }
        </script>
        <noscript>
        <meta http-equiv="refresh" content="0; url=enablejavascript.jsp"/>
        </noscript> 
        <title>Parking System</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />

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
        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
        <script src="js/customValidator.js"></script>
        <script type="text/javascript">
            function submitMethod()
            {
                if (validateUserNameAndPassword())
                {
                    $('#loginform').submit();
                }
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

            $(document).ready(function()
            {


                if (getCookie('seal') != "")
                {
                    history.forward();

                }

                document.cookie = "cookie=cookie";
                if (getCookie('cookie') == "")
                {

                    $('#enableCoockies').html('Cookies Required <hr>Cookies are not enabled on your browser.Please enable cookies in your browser preferences to continue.<hr/>')

                }
            });


            function validateUserNameAndPassword()
            {

                if (isEmail('email', 'emailError') && validateLength('password', 'passwordError'))
                {
                    return true;

                }

                return false;
            }

        </script>


    </head>
    <BODY>
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
                                        <li class="current_page_item"><a href="login.jsp">Login</a></li>


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

                                                <th colspan="2">
                                                    <c:out value="${error.getErrorBody()}"/>
                                                    <span id  = "enableCoockies"></span>
                                                </th>
                                                <tr>

                                                    <td>Email:</td>
                                                    <td><input id="email" type="text" name="email"  onblur="isEmail('email', 'emailError')" ></td>
                                                    <td><span id="emailError"></span></td>
                                                </tr>

                                                <tr>

                                                    <td>Password:</td>
                                                    <td><input id="password" type="password" name="pass" required onblur="validateLength('password', 'passwordError')" /></td>
                                                    <td><span id="passwordError"></span></td>

                                                </tr>
                                                <tr>
                                                    <td><input type="button" value="Sign in" id="myButton4" onclick="submitMethod()"/></td>
                                                    <td><input type="reset" value="Cancel" id="myButton3"/></td>
                                                </tr>

                                            </table><br>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <script src ="js/cookieValidator.js"></script>
                            <script type="text/javascript">



                            </script>  
                        </div>


                    </div>
                </div>
            </div>

        </div>

        <div id="copyright" class="5grid-layout">
            <section>
            </section>
        </div>
    </body>
</html>

