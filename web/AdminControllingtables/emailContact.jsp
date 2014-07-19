<%-- 
    Document   : contactTable
    Created on : Jul 3, 2014, 8:52:26 PM
    Author     : orcl
--%>
<script src="js/jquery-2.0.3.min.js"/></script>

<script src="css/ScrollableTable/scripts/jquery-makeTableScrollable.js" type="text/javascript"></script>
<link href="css/ScrollableTable/Css/Westwind.css" rel="stylesheet" type="text/css" />
<script src ="js/tableHandler.js"></script>
<script>

    //positMe("#addEmail", "#emailTable");

</script>

<style type="text/css">
</style>


<script>
    deleteButtonFormatingClass = "handleFaxNumber";
    deleteButtonMethod = "deleteButtonClass";
    $(document).ready(function()

    {
        $("#emailTable").makeTableScrollable({cssClass: "blackborder"});
        loadEmailData();
    });





    function loadEmailData()
    {

        $.ajax(
                {url: "GetContactList", async: false, data: 'deleteButtonFormatingClass=test&deleteButtonMethod=handleEmailAddress&identifier=e', success: function(result)
                    {

                        if (result.length == 0)
                        {
                            freeTableRows("#emailTable");

                        } else
                        {
                            freeTableRows("#emailTable");
                            $("#emailTable").append(result);
                            applyCss("#emailTable");
                            applyJavascript("#emailTable");
                        }

                    }
                });


    }

    function handleEmailAddress(x, q)
    {
        if (q == 'a')
        {
            if (!isAnEmail(x))
            {
                alert('Please enter correct email address');
                return false;

            }

        }

        $.ajax(
                {url: "ContactHandler", async: false, data: 'contact=' + x + '&qualifier=' + q + "&type=email", success: function(result)
                    {
                        loadEmailData();
                    }
                });
    }

    function addEmail()
    {


        var result = prompt("Please enter contact number");

        handleEmailAddress(result, 'a');

    }




</script>
<div id="page-wrapper">
    <div id="page-bgtop">
        <div id="page-bgbtm">
            <div id="page" class="5grid-layout">
                <div id="page-content-wrapper">
                    <CENTER>
                        <span class="blue"> Garage Email Addresses </span>

                        <table id="emailTable"  style="width: 60%; "  class="blackborder" >
                            <thead>
                                <tr class='gridheader'>
                                    <th style='width: 100px' >Email Address </th>
                                    <th style='width: 100px' >Action</th> 

                                </tr>
                            </thead>


                        </table>
                        <!--                        <div id="addEmail" style="position:absolute;"  ><input class="addButton"  style="width:50px; height:30px; color:white; vertical-align:middle; text-align:center;" type="button"  value="+" onclick="addEmail()"/></div>-->
                    </CENTER>
                </div>
            </div>

        </div>
    </div>