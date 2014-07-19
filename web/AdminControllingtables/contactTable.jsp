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

    //positMe("#addContact", "#contactTable");

</script>

<style type="text/css">
</style>


        
<script>
    deleteButtonFormatingClass = "handleContactNumber";
    deleteButtonMethod = "handleContactNumber";

    $(document).ready(function()
    {

        $("#contactTable").makeTableScrollable({cssClass: "blackborder"});
        loadContactData();
    });



    function loadContactData()
    {

        $.ajax(
                {url: "GetContactList", async: false, data: 'deleteButtonFormatingClass=test&deleteButtonMethod=handleContactNumber&identifier=p', success: function(result)
                    {

                        if (result.length == 0)
                        {
                            freeTableRows("#contactTable");

                        } else
                        {
                            freeTableRows("#contactTable");
                            $("#contactTable").append(result);
                            applyCss("#contactTable");
                            applyJavascript("#contactTable");
                        }
                    }
                });

    }

    function handleContactNumber(x, q)
    {
        if (q == 'a')
        {
            if (!isPhoneNumber(x,8,12))
            {
                return false;

            }

        }
        $.ajax(
                {url: "ContactHandler", async: false, data: 'contact=' + x + '&qualifier=' + q + "&type=phone", success: function(result)
                    {
                        loadContactData();
                    }
                });
    }

    function addContactNumber()
    {
        var result = prompt("Please enter contact number");
        handleContactNumber(result, 'a');
    }




</script>
<div id="page-wrapper">
    <div id="page-bgtop">
        <div id="page-bgbtm">
            <div id="page" class="5grid-layout">
                <div id="page-content-wrapper">
                    <span class="blue"> Garage Phone Numbers</span>
                    <CENTER>
                        <table id="contactTable"   style="width: 60%; " class="blackborder" >
                            <thead>
                                <tr class='gridheader'>
                                    <th style='width: 200px' >Phone Number </th>
                                    <th style='width: 100px' >Action</th> 

                                </tr>
                            </thead>


                        </table>
                        <!--                        <div id="addContact" style="position:absolute;"  ><input class="addButton"  style="width:50px; height:30px; color:white; vertical-align:middle; text-align:center;" type="button"  value="+" onclick="addContactNumber()"/></div>-->
                    </CENTER>
                </div>
            </div>

        </div>
    </div>