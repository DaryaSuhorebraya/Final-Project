$(document).ready(function () {
    var idUser;
    var $url= 'Controller';
    var $value;
    var banString;
    var index;
    
    $('tr').click(function () {
        banString=$(this).children()[8];
        $value =$.trim(banString.childNodes[0].textContent);
        idUser=$.trim($(this).children()[0].childNodes[0].textContent);
        index=$(this).index();
        if ($value==="Yes"){
            $('#for-ban').html('Unban').css("visibility","visible");
        } else {
            $('#for-ban').html('Ban').css("visibility","visible");
        }
        $('#edit').attr("href","Controller?command=view-user&userId="+idUser).css("visibility","visible");
        $('#delete').css("visibility","visible");
    });

    $('#for-ban').click(function (){
        var $current = $(this),
            status=$.trim($current[0].childNodes[0].textContent);
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command:'change-ban-status',userId: idUser, status: status},
            success: function (result) {
                if (result==="true"){
                    if (status==="Unban"){
                        $current.html('Ban');
                        $(banString).html('No');
                    } else {
                        $current.html('Unban');
                        $(banString).html('Yes');
                    }
                } else {
                    //message for error;
                }
            }
        });
    });
    $('#delete').click(function (){
        if(confirm("Delete?")){
            $.ajax({
                type: 'POST',
                url: $url,
                data: {command:'delete-user',userId: idUser},
                success: function (result) {
                    if (result==="true"){
                        $('#delete').css("visibility","hidden");
                        $('#edit').css("visibility","hidden");
                        $('#for-ban').css("visibility","hidden");
                        $("tr").eq(index).remove();
                    } else {
                        //message for error;
                    }
                }
            });
        }
    });
    
});
