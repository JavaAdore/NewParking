function freeTableRows(tableId)
{
    var tempVar = tableId + "> tbody  > tr";


    $(tempVar).each(function()
    {

        $(this).remove();
    });
}

function applyCss(tableId)
{
    var temp = 1;
    var tempVar = tableId + "> tbody  > tr";

    $(tempVar).each(function()
    {
        if (temp % 2 == 1)
        {
            $(this).addClass("first");
        }
        else
        {
            $(this).addClass("second");
        }
        temp++;
    });

}
function applyJavascript()
{
   
}


function positMe(span, table)
{

    $(span).css("left", $(table).position().left + $(table).width() + 10);
    $(span).css("top", $(table).position().top);
}


function updatePositions()
{
//     positMe("#addContact", "#contactTable");
//     positMe("#addFax", "#faxTable");
//     positMe("#addEmail", "#emailTable");
}








