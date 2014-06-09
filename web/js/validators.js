function areYouSure(x)
{
    var r = confirm(x);
    if (r == true)
    {
        return true;
    }
    return false;

}


function isNotEmpty(x)
{
    alert($(x).getVal()!=null);
    return $(x).getVal()!=null;
    
}
