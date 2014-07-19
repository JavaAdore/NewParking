<%-- 
    Document   : contactTable
    Created on : Jul 3, 2014, 8:52:26 PM
    Author     : orcl
--%>
<script src="js/jquery-2.0.3.min.js"/></script>
<style type="text/css">

</style>

<script>


    function  updateGarageWebsite()
    {
        if ($('#garageWebsite').val().trim().length == 0)
        {

            return false;
        }
        $.ajax(
                {url: "UpdateGarageWebsite", async: false, data: 'url=' + $('#garageWebsite').val().trim(), success: function(result)
                    {

                        alert(result);
                    }
                });

    }

</script>
<center>
    <div class="CSSTableGenerator" >
        <span class="blue">  Garage Website</span>
        <table id="garageDescription" >
            <tr>
                <td colspan="2">
                    <input type="text" id="garageWebsite" value="${emp.getGarage().getWebsite()}" />
                    <input type="button"   onclick="updateGarageWebsite()" value="Update" />
                </td>


            </tr>

        </table>

    </div>
</center>