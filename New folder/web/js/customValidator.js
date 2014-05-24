function isEmail(emailId, emailErrorId)
{
    var email = document.getElementById(emailId);
    if (email != null)
    {
        var x = email.value;
        var error = document.getElementById(emailErrorId);
        var regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z]{2,3}$/
        if (!x.match(regex))
        {
            error.innerHTML = "Please enter valid email address like someone@oracle.com";
            return false;
        }
        else {
            error.innerHTML = "";

        }
        return true;
    }
}
function isEmailWithValidation(emailId, emailErrorId)
{
    if ($(emailId).val() != null)
    {
        var regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z]{2,3}$/
        if (!regex.test($(emailId).val()))
        {
            $(emailErrorId).html("Please enter valid email address like someone@oracle.com");
            return false;
        }
        else {
            $(emailErrorId).html("");
            return isEmailAvailable(emailId, emailErrorId);

        }

    }
}


function validateLength(elementId, errorId)
{
    var password = document.getElementById(elementId);
    if (password != null)
    {
        var x = password.value;
        var error = document.getElementById(errorId);
        if (x.length < 6 || x.length > 25)
        {
            error.innerHTML = "Please password between 6 - 25 character or number";
            return false;
        }

        else
            error.innerHTML = "";
        return true;
    }
}

function isText(fieldId, fieldError)
{
    if ($(fieldId).val() != null)
    {
        var regex = /^[A-Za-z]+$/

        if (regex.test($(fieldId).val()) && $(fieldId).val().length < 25)
        {
            $(fieldError).html("");

            return true;
        }
        else
            $(fieldError).html("Please enter this field maximum charactes are (25)");

        return false;
    }
}

function areTheSame(firstField, secondField, errorField)
{


    if (($(firstField).val()) === ($(secondField).val()))
    {
        $(errorField).html("");
        return true;


    }
    $(errorField).html("Please confirm password");
    return false;
}


function isAdate(dateField, dateError)
{
    var regex = /^\d{4}-\d{1,2}-\d{1,2}$/
    if (regex.test($(dateField).val()))
    {
        $(dateError).html("");
        return true;
    }
    $(dateError).html("Please enter valid date like 31-12-1990 ");
    return false;


}

function isEmailAvailable(email, feedback)
{
    var flag = false;
    $.ajax(
            {url: "EmailChecking", async: false, data: 'email=' + $(email).val(), success: function(result)
                {
                    switch (result)
                    {
                        case "-1":
                            $(feedback).html("<h3 style 'color:red'>" + $("#email").val() + " accepted</h3>");
                            flag = true;
                            break;
                        case "0" :
                            $(feedback).html("<h3 style 'color:red'>" + $("#email").val() + " not accepted</h3>");
                            flag = false;
                    }
                }
            });
    return flag;
}


function isAnumber(fieldId, feedback, min, max)
{
    if (!(isNaN($(fieldId).val()))||$(fieldId).val().length==0)
    {
        if ($(fieldId).val() >= min && $(fieldId).val() <= max)
        {
            $(feedback).html("");
            return true;
        }
    }
    $(feedback).html("Please enter a number between " + min + "and " + max);
    return false;

}

function isImage(fileId, fileError)
{
    var fileName;

    if ($(fileId).val().length != 0)
    {

        fileName = $(fileId).val();
        var acceptedExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];
        extension = fileName.substring(fileName.indexOf('.'), fileName.length);
        for (i = 0; i < acceptedExtensions.length; i++)
        {
            if (extension.toLowerCase() === acceptedExtensions[i].toLowerCase())
            {
                $(fileError).html("");

                return true;
            }
        }
       
    }
    $(fileError).html("Please choose an image to upload as garage map");
    return false;
}

function isGarageNameAvailable(garage, feedback)
{
    if (isText(garage, feedback))
    {
        var flag = false;
        $.ajax(
                {url: "GarageNameCheck", async: false, data: 'garage=' + $(garage).val(), success: function(result)
                    {
                        switch (result)
                        {
                            case "-1":
                                $(feedback).html("<h3 style 'color:red'>" + $(garage).val() + " accepted</h3>");
                                flag = true;
                                break;
                            case "0" :
                                $(feedback).html("<h3 style 'color:red'>" + $(garage).val() + " not accepted</h3>");

                                flag = false;
                        }
                    }
                });
        return flag;
    }
}

