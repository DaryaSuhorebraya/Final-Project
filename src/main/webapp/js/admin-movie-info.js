$(document).ready(function () {
    var $url= 'Controller';
    var movieId=$('#movie-id').text();
    var field;
    var $current;
    var input;
    var text;

    $('#year-edit').click(function () {
        $current=$(this);
        input=$current.siblings();
        text=$.trim($current.siblings().text());
        field=$.trim($current.parent().text()).split(':')[0];
         $('#year-text').replaceWith( "<form id='form-year'>" +
            "<input type='text' value="+text+">" +
            "</form>" );
        $('#save-edit-year').css("display", "inline-block");
        $('#cancel-edit-year').css("display", "inline-block");
        $current.css("display","none");

    });

    $('#save-edit-year').click(function () {
        var value=$.trim($('#form-year').children().first().val());
        $.ajax({
           type: 'POST',
            url: $url,
            data: {command: 'edit-movie', movieId: movieId, field: 'release_year', value: value},
            success: function (result) {
                if (result==="true"){
                    input[0].textContent=" "+value;
                    $('#form-year').replaceWith(input[0]);
                    $current.css("display","inline-block");
                    $('#save-edit-year').css("display","none");
                    $('#cancel-edit-year').css("display","none");
                } else {
                    //TODO message for error;
                }
            }
        });
    });


});