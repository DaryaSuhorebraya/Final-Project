$(document).ready(function () {
   /* var $editedText = $("#edited_text");
    var text = $editedText.text();
    $editedText.text(shorten(text, 400, "...", false));*/
    var $url= 'Controller';

    $('.delete-movie').click(function (){
        if(confirm("Delete?")){
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
        }
    });
   
    $('#list').click(function(event){
        event.preventDefault();
        $('#products .item').addClass('list-group-item');
        $('.btn-info').addClass('pull-right');
    });
    $('#grid').click(function(event){
        event.preventDefault();
        $('#products .item').removeClass('list-group-item');
        $('#products .item').addClass('grid-group-item');
        $('.btn-info').removeClass('pull-right');
    });

    $(".thumbnail").dotdotdot({
        ellipsis	: '... '
    });

});