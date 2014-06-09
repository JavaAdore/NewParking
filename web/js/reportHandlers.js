

$(document).ready(function()
{

    $("#selection").change(function() {
        $.ajax({url: "ReportHandler", async: false, data: 'selection=' + $("#selection").val(), success: function(result)
            {
                $("#fromDiv").html(result);
                viewReport();
                assignListeners();
            }});
    })
});


function getPeriod()
{

    return $("#from").val() + "  :  " + $("#to").val();
}

function assignListeners()
{
    $("#from").change(function() {
        $.ajax({url: "ReportInitializer", async: false, data: 'from=' + $("#from").val() + '&to=' + $("#to").val(), success: function(result)
            {
                viewReport();

            }});
    });
    $("#to").change(function() {
        $.ajax({url: "ReportInitializer", async: false, data: 'from=' + $("#from").val() + '&to=' + $("#to").val(), success: function(result)
            {
                viewReport();
            }});
    });


}

function viewReport()
{


    $('#reportBodyDiv').load("ReportBody.jsp");

}

function loadPeriod()
{


    $('#period').text(getPeriod());
}