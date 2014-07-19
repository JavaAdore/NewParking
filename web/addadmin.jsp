<jsp:include page="headers/checkingPage.jsp"/>
<!DOCTYPE HTML>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="headers/header.jsp"/>

<html>
    <head>
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


        <script type="text/javascript">

            function submitMethod()
            {
                if (isText('#firstName', '#firstNameError') && isText('#lastName', '#lastNameError') && isEmailWithValidation('#email', '#emailError') && isPassword('#password', '#passwordError') && areTheSame('#password', '#confirmPassword', '#confirmPasswordError') && isAdate('#datepicker', '#birthdateError'))
                {

                    $("#addEmployeeForm").submit();
                }


            }

            function checkInput(type) {
                var input = document.createElement("input");
                input.setAttribute("type", type);
                return input.type === type;
            }
            if (!checkInput("date")) {

                //                What if not ...?

                $('input.date').datepicker();
            }


            $(document).ready(function()
            {
                $("#datepicker").datepicker({maxDate: "-192m "});
            });

            setActive('#addAdmin');
        </script>




        <noscript>
        <meta http-equiv="refresh" content="0; url=enablejavascript.jsp"/>
        </noscript> 
        <!--[if IE 9]><link rel="stylesheet" href="css/style-ie9.css" /><![endif]-->
    </head>
    <body>

        <div id="page-wrapper">
            <div id="page-bgtop">
                <div id="page-bgbtm">
                    <div id="page" class="5grid-layout">
                        <div id="page-content-wrapper">
                            <center>

                                <form  method="post" action="addAdminServlet" id="addEmployeeForm" name="myForm">
                                    <table>

                                        <th colspan="2">
                                            <c:out value="${error.getErrorBody()}"/>
                                        </th>
                                        <tr>
                                            <td>First Name:</td>
                                            <td><input id ="firstName" name="firstName" type="text"   onblur="isText('#firstName', '#firstNameError')"></td>


                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><center><span id="firstNameError"></span></center></td>
                                        </tr>
                                        <tr>
                                            <td>Last Name:</td>
                                            <td><input name="lastName" type="text" id="lastName" onblur="isText('#lastName', '#lastNameError')" ></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><center><span id="lastNameError"></span></center></td>
                                        </tr>
                                        <tr>
                                            <td>Email:</td>
                                            <td><input id="email" name="email" type="email" onblur="isEmailWithValidation('#email', '#emailError')" /> </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><center><span id="emailError"></span></center></td>
                                        </tr>
                                        <tr>
                                            <td>Password:</td>
                                            <td><input id="password" name="password" type="password" onblur="isPassword('#password', '#passwordError');
                                                    areTheSame('#password', '#confirmPassword', '#confirmPasswordError')" /> </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><center><span id="passwordError"></span></center></td>
                                        </tr>
                                        <tr>
                                            <td>Confirm Password:</td>
                                            <td><input id="confirmPassword" name="confirmPassword" type="password" onblur="areTheSame('#password', '#confirmPassword', '#confirmPasswordError')"/> </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><center><span id="confirmPasswordError"></span></center></td>
                                        </tr>
                                        <tr>
                                            <td>gender:</td>
                                            <td><input name="gender" type="radio"  value="m" checked />male <br>
                                                <input name="gender" type="radio"  value="f"/> female <br>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Birthday:</td>
                                            <td> <input  id="datepicker" name="birthdate" readonly onchange="isAdate('#datepicker', '#birthdateError')"> </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td><span id="birthdateError"> </span>  </td>
                                        </tr>
                                        <tr>
                                            <td>Role:</td>
                                            <td>
                                                <select name="role" >
                                                    <option value="2">Administrator</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr></tr>
                                        <tr>
                                            <td></td>
                                            <td ><input  class="button" type="button"  value="Register" onclick="submitMethod()" /></td>
                                        </tr>
                                    </table>
                                </form>
                            </center>
                        </div>
                    </div>
                    <div id="copyright" class="5grid-layout">	
                    </div>
                </div>
            </div>
        </div>
    </body>



</html>
