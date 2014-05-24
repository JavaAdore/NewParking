var from;
var to;





function getPeriod()
{

    return $("#from").val() + "  :  " + $("#to").val();
}

function assignListeners(from, to)
{   
    correctDates(from , to);
    
    $.ajax({url: "ReportInitializer", async: false, data: 'from=' + from + '&to=' + to, success: function(result)
        {
        }});
}
function detailedReportInitializer(from, to)
{   
    correctDates(from , to);
    
    $.ajax({url: "DetailedReportInitializer", async: false, data: 'from=' + from + '&to=' + to, success: function(result)
        {
        }});
}

function viewReport(from , to)
{
    assignListeners(from, to);
    $('#reportBodyDiv').load("ReportBody.jsp");
}
function viewDetailedReportReport(from , to)
{
    assignListeners(from, to);
    $('#reportBodyDiv').load("ReportBody.jsp");
}
function loadPeriod()
{


    $('#period').text(getPeriod());
}


function load()
{

}

function setStartDate(x)
{
    from = x;
}

function setToDate(x)
{
    to = x;
}
function getStartDate()
{

    return from;


}
function getToDate()
{
    return to;
}


function correctDates(from , to )
{
    var userMinDate = new Date(from);
    var userMaxDate = new Date(to);



    if (userMinDate == "Invalid Date")
    {

        userMinDate = new Date('${minDate}');

    }
    if (userMaxDate == "Invalid Date")
    {

        userMaxDate = new Date('${maxDate}');
    }


    date1 = dateSwapper(userMinDate, userMaxDate, 0);

    date2 = dateSwapper(userMinDate, userMaxDate, 1);


    if (date1 < new Date('${minDate}'))

    {
        date1 = new Date('${minDate}');

    }
    if (date2 > new Date('${maxDate}'))
    {

        date2 = new Date('${maxDate}');

    }

    from = dateToString(date1)
    to = dateToString(date2);

    $('#minDate').html(dateToString(date1));
    $('#maxDate').html(dateToString(date2));
}

function dateSwapper(date1, date2, x)
{

    switch (x)
    {
        case
                1:
            if (date1 > date2)
            {

                return  date1;
            } else
                return  date2;
            break;
        case 0:
            if (date1 > date2)
            {
                return date2;
            }
            return  date1;
            break;

    }


}

function dateToString(date)
{

    var formattedDate = new Date(date);
    var d = formattedDate.getDate();
    var m = formattedDate.getMonth();
    m += 1;  // JavaScript months are 0-11
    var y = formattedDate.getFullYear();

    return (d + "-" + m + "-" + y);

}

