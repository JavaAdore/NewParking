function areYouSure(select, x)
{
    if ($(select).length != 0)
    {
        if (($(select).val() != "-1"))
        {
            var r = confirm(x);
            if (r == true)
            {
                return true;
            }
        }
    }
    return false;
}


function isNotEmpty(x)
{

    return $(x).val() != -1;

}

function test()
{


    alert('test');
    return false;
}

function checkEmail(emailTextBox)
{
    var email = $('#emailTextBox').val();
    if (email != null)
    {
        $(document).ready(function()
        {
        });
    }


}



