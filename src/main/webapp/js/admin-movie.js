$(document).ready(function () {
    var $url= 'Controller';

    $('.delete-movie').click(function (){
        var idMovie=$(this).siblings('#id').text();
        var current=$(this);
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command:'delete-movie',movieId: idMovie},
            success: function (result) {
                //TODO confirm action
                if (result==="true"){
                    current.parent().parent().remove()
                } else {
                    //message for error;
                }
            }
        });
    });
   
    $('#list').click(function(event){
        event.preventDefault();
        $('#products .item').addClass('list-group-item');
    });
    $('#grid').click(function(event){
        event.preventDefault();
        $('#products .item').removeClass('list-group-item');
        $('#products .item').addClass('grid-group-item');
    });
});