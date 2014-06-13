$(document).ready(function()
{


    $.ajax({url: "ReportHandler", async: false, success: function(result)
        {

        }});
});
function getPeriod()
{

    return $("#from").val() + "  :  " + $("#to").val();
}

function assignListeners()
{

    $.ajax({url: "ReportInitializer", async: false, data: 'from=' + $("#from").val() + '&to=' + $("#to").val(), success: function(result)
        {

        }});
}

function viewReport()
{

    assignListeners();
    $('#reportBodyDiv').load("ReportBody.jsp");
}

function loadPeriod()
{


    $('#period').text(getPeriod());
}


function load()
{

}
function  getStartDate()
{
            
    return $('#from').val();
    ;

}
function getToDate()
{
    return $('#to').val();

}

