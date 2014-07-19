<%-- 
    Document   : contactTable
    Created on : Jul 3, 2014, 8:52:26 PM
    Author     : orcl
--%>
<script src="js/jquery-2.0.3.min.js"/></script>
<style type="text/css">
    
</style>

<script>
    deleteButtonFormatingClass = "handleFeedback";
    deleteButtonMethod = "handleFeedback";
    $(document).ready(function()
    {
       // $("#feedbackTable").makeTableScrollable({cssClass: "blackborder"});

        setInterval("loadFeedback()", "5000");


    });

    function loadFeedback()
    {

        $.ajax(
                {url: "GetFeedback", async: false, data: 'deleteButtonFormatingClass=test&deleteButtonMethod=handleFeedback&identifier=p', success: function(result)
                    {

                        if (result.length == 0)
                        {
                            freeTableRows("#feedbackTable");
                        } else
                        {
                            freeTableRows("#feedbackTable");
                            $("#feedbackTable").append(result);
                            applyCss("#feedbackTable");
                            applyJavascript("#feedbackTable");
                        }
                    }
                });
    }

    function handleFeedback(x)
    {
        var result = window.confirm("Are you sure?")
        if (result)
        {

            $.ajax(
                    {url: "FeedbackHandler", async: false, data: 'feedback=' + x, success: function(result)
                        {
                            loadFeedback();

                        }
                    });
        }
    }



</script>
<div id="page-wrapper">
    <div id="page-bgtop">
        <div id="page-bgbtm">
            <div id="page" class="5grid-layout">
                <div id="page-content-wrapper">
                    <CENTER>
                        <span class="blue">  Garage feedback</span>
                        <table id="feedbackTable"  style="width: 70%;"  class="blackborder" >
                            <thead>
                                <tr class='gridheader'>
                                    <th style='width: 100px' >Feedback </th>
                                    <th style='width: 100px' >Date</th> 
                                    <th style='width: 100px' >Sender</th> 
                                    <th style='width: 100px' >Action</th> 

                                </tr>
                            </thead>


                        </table>
                    </CENTER>
                </div>
            </div>

        </div>
    </div>
</div>