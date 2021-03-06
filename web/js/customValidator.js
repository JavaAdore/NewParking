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
            error.innerHTML = "email like someone@oracle.com";
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
            $(emailErrorId).html("mail like someone@oracle.com");
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
    if ($(fieldId).val().length == 0)
    {
        $(fieldError).html("(Required)");
        return false;
    }
    var regex = /^[A-Za-z]+$/

    if (regex.test($(fieldId).val()) && $(fieldId).val().trim().length < 25)
    {
        $(fieldError).html("");

        return true;
    }
    else
    {
        $(fieldError).html("maximum length(25) characters");

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
    if ($(dateField).val().length == 0)
    {

        $(dateError).html("valid date like 12/31/2014 ");

        return false;


    }
    var matches = /^(\d{2})[-\/](\d{2})[-\/](\d{4})$/.exec($(dateField).val());


    if (matches == null)
    {


        $(dateError).html("valid date like 12/31/2014 ");
        return false;
    }
    $(dateError).html("");

    $.ajax(
            {url: "ValidateDate", async: false, data: 'birthdate=' + $(dateField).val(), success: function(result)
                {
                    $(dateError).html(result);



                }
            });
    return true;


}
function isAReportDate(dateField, dateError)
{
    var matches = /^(\d{2})[-\/](\d{2})[-\/](\d{4})$/.exec($(dateField).val());
    if (matches === null)
    {

        $(dateError).html("date like 12/31/2013 ");
        return false;
    }
    $(dateError).html("");
    var flag = false;
    $.ajax(
            {url: "ValidateReportDate", async: false, data: 'calDate=' + $(dateField).val(), success: function(result)
                {
                    $(dateError).html(result);
                    if (result.length == 0)
                    {
                        flag = true;
                    } else
                    {

                        flag = false;
                    }
                }
            }
    );
    return flag;
}

function isEmailAvailable(email, feedback)
{

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
    if (!(isNaN($(fieldId).val())) && $(fieldId).val().trim().length != 0)
    {
        if ($(fieldId).val() >= min && $(fieldId).val() <= max)
        {
            $(feedback).html("");
            return true;
        }
    }
    $(feedback).html("number between " + min + "and " + max);
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
    $(fileError).html("choose an image to upload as garage map");
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
function isGarageNameAvailableForUpdate(garage, garageId, feedback)
{
    if (isText(garage, feedback))
    {
        var flag = false;

        $.ajax(
                {url: "CheckGarageName", async: false, data: 'newGarageName=' + $(garage).val() + "&garageId=" + garageId, success: function(result)
                    {
                        switch (result)
                        {
                            case "0":
                                $(feedback).html("<h3 style 'color:red'>" + $(garage).val() + " accepted</h3>");
                                flag = true;
                                break;
                            case "-1" :
                                $(feedback).html("<h3 style 'color:red'>" + $(garage).val() + " not accepted</h3>");

                                flag = false;
                        }
                    }
                });

        return flag;
    }
}

function isTextWithSpace(fieldId, fieldError)
{
    if ($(fieldId).val() != null)
    {
        var regex = /^[A-Za-z\d\s]+$/

        if (regex.test($(fieldId).val()) && $(fieldId).val().trim().length < 25)
        {
            $(fieldError).html("");

            return true;
        }
        else
            $(fieldError).html("Required maximum length(25) characters");

        return false;
    }

}

function isImageAcceptNull(fileId, fileError)
{
    var fileName;


    fileName = $(fileId).val();
    if (fileName.length !== 0)
    {
        var acceptedExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];
        extension = fileName.substring(fileName.indexOf('.'), fileName.length);
        for (i = 0; i < acceptedExtensions.length; i++)
        {

            if (extension.toLowerCase() == acceptedExtensions[i].toLowerCase())
            {
                $(fileError).html("");

                return true;
            } else
            {
                $(fileError).html("Please choose an image to upload as garage map");

            }
        }

    } else {
        $(fileError).html("");
        return true;
    }
    return true;
}


function isPassword(password, error)
{
    if ($(password).val() != null)
    {
        if ($(password).val().length < 8 || $(password).val().length > 25)
        {
            $(error).html("password should be between 8-25 characters ");
            return false;
            F
        }
        var regex = /^(?:(?=.*[a-z])(?:(?=.*[A-Z])(?=.*[\d\W])|(?=.*\W)(?=.*\d))|(?=.*\W)(?=.*[A-Z])(?=.*\d)).{8,}$/
        if (!$(password).val().match(regex))
        {
            $(error).html(" should contains numbers , capital & small characters ");
            return false;
        }
        else {
            $(error).html("");

        }
    }
    return true;



}

function isPhoneNumber(phoneNumber, minLength, maxLength)
{   
    var regex = /^(0|[0-9][0-9]*)$/;
    if (regex.test(phoneNumber) && phoneNumber.length >= minLength && phoneNumber.length <= maxLength)
    {
        
        return true;
    }
    alert('Please enter correct number');
    return false;
}
function isAnEmail(email)
{

    var regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z]{2,3}$/
    if (!email.match(regex))
    {
        return false;
    }
    else
        return true;
}
