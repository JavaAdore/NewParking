function test()
{
    $('#myImage').attr('src', 'http://s3.media.squarespace.com/production/459789/8419961/wp-content/uploads/2009/06/IMG_0175.PNG');
}

function addDoor()
{
    $('#MyAwesomeDiv').after('<input type= "button" value="door" id="x" onclick="exposeMyLocation(this)" />');
}

function exposeMyLocation(x)
{
    alert($(x).offset().left + ","+$("#x").offset().top);
    
    ;


}