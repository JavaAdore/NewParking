<%-- 
    Document   : contactTable
    Created on : Jul 3, 2014, 8:52:26 PM
    Author     : orcl
--%>
<script src="js/jquery-2.0.3.min.js"/></script>
<style type="text/css">
    .CSSTableGenerator {
        margin:0px;padding:0px;
        width:100%;
        box-shadow: 10px 10px 5px #888888;
        border:1px solid #000000;

        -moz-border-radius-bottomleft:14px;
        -webkit-border-bottom-left-radius:14px;
        border-bottom-left-radius:14px;

        -moz-border-radius-bottomright:14px;
        -webkit-border-bottom-right-radius:14px;
        border-bottom-right-radius:14px;

        -moz-border-radius-topright:14px;
        -webkit-border-top-right-radius:14px;
        border-top-right-radius:14px;

        -moz-border-radius-topleft:14px;
        -webkit-border-top-left-radius:14px;
        border-top-left-radius:14px;
    }.CSSTableGenerator table{
        border-collapse: collapse;
        border-spacing: 0;
        width:100%;
        height:100%;
        margin:0px;padding:0px;
    }.CSSTableGenerator tr:last-child td:last-child {
        -moz-border-radius-bottomright:14px;
        -webkit-border-bottom-right-radius:14px;
        border-bottom-right-radius:14px;
    }
    .CSSTableGenerator table tr:first-child td:first-child {
        -moz-border-radius-topleft:14px;
        -webkit-border-top-left-radius:14px;
        border-top-left-radius:14px;
    }
    .CSSTableGenerator table tr:first-child td:last-child {
        -moz-border-radius-topright:14px;
        -webkit-border-top-right-radius:14px;
        border-top-right-radius:14px;
    }.CSSTableGenerator tr:last-child td:first-child{
        -moz-border-radius-bottomleft:14px;
        -webkit-border-bottom-left-radius:14px;
        border-bottom-left-radius:14px;
    }.CSSTableGenerator tr:hover td{

    }
    .CSSTableGenerator tr:nth-child(odd){ background-color:#aad4ff; }
    .CSSTableGenerator tr:nth-child(even)    { background-color:#ffffff; }.CSSTableGenerator td{
        vertical-align:middle;


        border:1px solid #000000;
        border-width:0px 1px 1px 0px;
        text-align:left;
        padding:7px;
        font-size:10px;
        font-family:Arial;
        font-weight:normal;
        color:#000000;
    }.CSSTableGenerator tr:last-child td{
        border-width:0px 1px 0px 0px;
    }.CSSTableGenerator tr td:last-child{
        border-width:0px 0px 1px 0px;
    }.CSSTableGenerator tr:last-child td:last-child{
        border-width:0px 0px 0px 0px;
    }
    .CSSTableGenerator tr:first-child td{
        background:-o-linear-gradient(bottom, #005fbf 5%, #003f7f 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #005fbf), color-stop(1, #003f7f) );
        background:-moz-linear-gradient( center top, #005fbf 5%, #003f7f 100% );
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#005fbf", endColorstr="#003f7f");	background: -o-linear-gradient(top,#005fbf,003f7f);

        background-color:#005fbf;
        border:0px solid #000000;
        text-align:center;
        border-width:0px 0px 1px 1px;
        font-size:14px;
        font-family:Arial;
        font-weight:bold;
        color:#ffffff;
    }
    .CSSTableGenerator tr:first-child:hover td{
        background:-o-linear-gradient(bottom, #005fbf 5%, #003f7f 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #005fbf), color-stop(1, #003f7f) );
        background:-moz-linear-gradient( center top, #005fbf 5%, #003f7f 100% );
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#005fbf", endColorstr="#003f7f");	background: -o-linear-gradient(top,#005fbf,003f7f);

        background-color:#005fbf;
    }
    .CSSTableGenerator tr:first-child td:first-child{
        border-width:0px 0px 1px 0px;
    }
    .CSSTableGenerator tr:first-child td:last-child{
        border-width:0px 0px 1px 1px;
    }

    .addButton {
        -moz-box-shadow:inset 0px 1px 0px 0px #caefab;
        -webkit-box-shadow:inset 0px 1px 0px 0px #caefab;
        box-shadow:inset 0px 1px 0px 0px #caefab;
        background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #77d42a), color-stop(1, #73ff00) );
        background:-moz-linear-gradient( center top, #77d42a 5%, #73ff00 100% );
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#77d42a', endColorstr='#73ff00');
        background-color:#77d42a;
        -webkit-border-top-left-radius:20px;
        -moz-border-radius-topleft:20px;
        border-top-left-radius:20px;
        -webkit-border-top-right-radius:20px;
        -moz-border-radius-topright:20px;
        border-top-right-radius:20px;
        -webkit-border-bottom-right-radius:20px;
        -moz-border-radius-bottomright:20px;
        border-bottom-right-radius:20px;
        -webkit-border-bottom-left-radius:20px;
        -moz-border-radius-bottomleft:20px;
        border-bottom-left-radius:20px;
        text-indent:0;
        border:1px solid #268a16;
        display:inline-block;
        color:#306108;
        font-family:Arial;
        font-size:15px;
        font-weight:bold;
        font-style:normal;
        height:50px;
        line-height:50px;
        width:100px;
        text-decoration:none;
        text-align:center;
        text-shadow:1px 1px 0px #aade7c;
    }
    .addButton:hover {
        background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #73ff00), color-stop(1, #77d42a) );
        background:-moz-linear-gradient( center top, #73ff00 5%, #77d42a 100% );
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#73ff00', endColorstr='#77d42a');
        background-color:#73ff00;
    }.addButton:active {
        position:relative;
        top:1px;
    }


    .deleteButton {
        -moz-box-shadow:inset 0px 1px 0px 0px #f29c93;
        -webkit-box-shadow:inset 0px 1px 0px 0px #f29c93;
        box-shadow:inset 0px 1px 0px 0px #f29c93;
        background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #fe1a00), color-stop(1, #ce0100) );
        background:-moz-linear-gradient( center top, #fe1a00 5%, #ce0100 100% );
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#fe1a00', endColorstr='#ce0100');
        background-color:#fe1a00;
        -webkit-border-top-left-radius:20px;
        -moz-border-radius-topleft:20px;
        border-top-left-radius:20px;
        -webkit-border-top-right-radius:20px;
        -moz-border-radius-topright:20px;
        border-top-right-radius:20px;
        -webkit-border-bottom-right-radius:20px;
        -moz-border-radius-bottomright:20px;
        border-bottom-right-radius:20px;
        -webkit-border-bottom-left-radius:20px;
        -moz-border-radius-bottomleft:20px;
        border-bottom-left-radius:20px;
        text-indent:0;
        border:1px solid #d83526;
        display:inline-block;
        color:#ffffff;
        font-family:Arial;
        font-size:15px;
        font-weight:bold;
        font-style:normal;
        height:50px;
        line-height:50px;
        width:100px;
        text-decoration:none;
        text-align:center;
        text-shadow:1px 1px 0px #b23e35;
    }
    .deleteButton:hover {
        background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #ce0100), color-stop(1, #fe1a00) );
        background:-moz-linear-gradient( center top, #ce0100 5%, #fe1a00 100% );
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ce0100', endColorstr='#fe1a00');
        background-color:#ce0100;
    }.deleteButton:active {
        position:relative;
        top:1px;
    }
</style>

<script>


    function  updateGarageWebsite()
    {
        if($('#garageWebsite').val().trim().length==0)
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
<div class="CSSTableGenerator" >
    <table id="garageDescription" >
        <tr>
            <td colspan="2">
                <input type="text" id="garageWebsite" value="${emp.getGarage().getWebsite()}" />
                    
                   
                

                <input type="button"   onclick="updateGarageWebsite()" value="Update" />
            </td>


        </tr>

    </table>

</div>