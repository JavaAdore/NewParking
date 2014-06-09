
function check()
{

    var x = $.session.get('seal');
    alert(x == null);
    return (x == null);
}

setInterval(check(), 1000);