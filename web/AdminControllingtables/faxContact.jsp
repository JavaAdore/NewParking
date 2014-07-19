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
//positMe("#addFax", "#faxTable");

</script>

<style type="text/css">
</style>

<script>
    deleteButtonFormatingClass = "handleFaxNumber";
    deleteButtonMethod = "handleFaxNumber";
    $(document).ready(function()

    {
        $("#faxTable").makeTableScrollable({cssClass: "blackborder"});
        loadFaxData();
    });





    function loadFaxData()
    {

        $.ajax(
                {url: "GetContactList", async: false, data: 'deleteButtonFormatingClass=test&deleteButtonMethod=handleFaxNumber&identifier=f', success: function(result)
                    {

                        if (result.length == 0)
                        {
                            freeTableRows("#faxTable");
                        } else
                        {
                            freeTableRows("#faxTable");
                            $("#faxTable").append(result);
                            applyCss("#faxTable");
                            applyJavascript("#faxTable");
                        }

                    }
                });

    }

    function handleFaxNumber(x, q)
    {

        if (q == 'a')
        {
            if (!isPhoneNumber(x, 8, 12))
            {
                return false;

            }

        }
        $.ajax(
                {url: "ContactHandler", async: false, data: 'contact=' + x + '&qualifier=' + q + "&type=fax", success: function(result)
                    {
                        loadFaxData();
                    }
                });
    }

    function addFaxNumber()
    {


        var result = prompt("Please enter contact number");

        handleFaxNumber(result, 'a');

    }




</script>
<div id="page-wrapper">
    <div id="page-bgtop">
        <div id="page-bgbtm">
            <div id="page" class="5grid-layout">
                <div id="page-content-wrapper">
                    <CENTER>
                        <span class="blue"> Garage Fax Numbers </span>

                        <table id="faxTable"  style="width: 60%; "  class="blackborder" >
                            <thead>
                                <tr class='gridheader'>
                                    <th style='width: 100px' >Fax Number </th>
                                    <th style='width: 100px' >Action</th> 

                                </tr>
                            </thead>


                        </table>
                        <!--                        <div id="addFax" style="position:absolute;"  ><input class="addButton"  style="width:50px; height:30px; color:white; vertical-align:middle; text-align:center;" type="button"  value="+" onclick="addFaxNumber()"/></div>-->
                    </CENTER>
                </div>
            </div>

        </div>
    </div>