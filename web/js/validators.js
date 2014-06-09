function areYouSure(select, x)
{
    alert('ok');
    if (($('#'+select).getVal())==="-1") 
    {
        
        alert("it's minus one dude");
    }
    
        var r = confirm(x);
        if (r == true)
        {
            return true;
        }
    
    return false;

}


function isNotEmpty(x)
{
    alert($(x).getVal() != null);
    return $(x).getVal() != null;

}
