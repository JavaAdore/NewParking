<%-- 
    Document   : contactTable
    Created on : Jul 3, 2014, 8:52:26 PM
    Author     : orcl
--%>
<script src="js/jquery-2.0.3.min.js"/></script>
<style type="text/css">

</style>

<script>


    function  UpdateGarageDescription()
    {
        if ($('#garageDesc').val().trim().length == 0 || $('#garageDesc').val().trim().length > 255)
        {
            alert('Garage descption must be between 1 and 255 character');
            return false;
        }
        $.ajax(
                {url: "UpdateGarageDescription", async: false, data: 'desc=' + $('#garageDesc').val().trim(), success: function(result)
                    {

                        alert(result);
                    }
                });

        $("#garageDesc").attr("readonly", false)

    }

</script>
<center>
    <div class="CSSTableGenerator" >
        <span class="blue"> Garage Description</span>
        <table id="garageDescription" >
            <tr>
                <td colspan="2">

                    <textarea id="garageDesc" cols="100">
                    
                        ${emp.getGarage().getDescription()}
                    </textarea>


                </td>
            </tr>
            <tr>
                <td>
                    <input type="button"   onclick="UpdateGarageDescription()" value="Update" />
                </td>
            </tr>

        </table>

    </div>
</center>