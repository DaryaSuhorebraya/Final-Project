$(document).ready(function () {
    var $url = 'Controller';
    var userId = $('#user-id').text();
    $('.input-stars').rating("refresh",{ stars: 10});
    $('.caption').remove();
    $('.clear-rating').remove();
    $('.rating-xs').css('font-size',' 1.2em');
    $('.rating-container').css('display', 'inline-block');

    if (document.getElementById('user-login')!==null) {
        var collection = $('.single-star');
        (collection).each(function() {
            var current=$(this);
            var movieId = current.parent().parent().parent().find('.movie-id').text();
            
            $.ajax({
                type: 'POST',
                url: $url,
                data: {command: 'check-rate-opportunity', movieId: movieId, userId: userId},
                success: function (result) {
                    if (result === "true") {
                        current.css("color", "red");
                    } else {

                    }
                }
            });
        });
    }

    $('.single-star').click(function (event) {
        var current=$(this);
        if (document.getElementById('user-login')!==null){
            
            var movieId=current.parent().parent().parent().find('.movie-id').text();
            
            $.ajax({
                type: 'POST',
                url: $url,
                data: {command: 'check-rate-opportunity', movieId: movieId, userId: userId},
                success: function (result) {
                    if (result === "true") {
                        //TODO message for error;
                        alert('you have rated this movie yet');
                    } else {
                        event.stopPropagation();
                        current.css('display','none');
                        current.parent().parent().parent().find(".star-rating").fadeIn(1500).css('display','inline-block');
                        current.parent().parent().parent().find('.btn-rate').css('display',"inline-block");
                    }
                }
            });
        } else {
            alert("Необходимо зарегистрироваться ");
        }
    });

    $('.btn-rate').click(function () {
        var current = $(this);
        var movieId=current.parent().find('.movie-id').text();
        var mark=current.parent().parent().parent().find(".rating-input").val();
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'rate-movie', movieId: movieId, mark: mark},
            success: function (result) {
                if (result === "true") {
                    current.css('display','none');
                    $('.star-rating').css('display','none');
                    $('.single-star').css('display','block').css('color','rgb(242, 56, 6)');
                } else {
                    //TODO message for error;
                }
            }
        });
    });
    $('html').click(function() {
        $('.star-rating').css('display','none');
        $('.single-star').css('display','block');
    });
    
});
