$(document).ready(function () {
    var $url = 'Controller';
    var movieId = $('#movie-id').text();
    var userId = $('#user-id').text();
    var ratingCount = 0;
    var mark;
    $('.user-stars').rating("refresh", {displayOnly: true, stars: 10, disabled: false});


    $('.leave-review').click(function (event) {
        var current = $(this);
        if (document.getElementById('user-login') !== null) {

            $.ajax({
                type: 'POST',
                url: $url,
                data: {command: 'check-review-opportunity', movieId: movieId, userId: userId},
                success: function (result) {
                    if (result === "true") {
                        //TODO message for error;
                        alert('you have reviewed this movie yet');
                    } else {
                        event.stopPropagation();
                        $('.text-right').css('display', 'none');
                        $('.add-review').css('display', 'block');

                        $.ajax({
                            type: 'POST',
                            url: $url,
                            data: {command: 'check-rate-opportunity', movieId: movieId, userId: userId},
                            success: function (result) {
                                if (result === "true") {

                                    $.ajax({
                                        type: 'POST',
                                        url: $url,
                                        data: {
                                            command: 'view-rating-on-movie-by-user-id',
                                            movieId: movieId,
                                            userId: userId
                                        },
                                        success: function (data) {
                                            $('.add-star-rating').replaceWith("<div class='review-block-rate'>" +
                                                "<input class='inserted-rating rating rating-loading'  data-size='xs' value='" + data + "' data-min='0'" +
                                                " data-max='10' data-step='1'>" +
                                                "</div>");
                                            $('.inserted-rating').rating("refresh", {
                                                displayOnly: true,
                                                stars: 10,
                                                disabled: false
                                            });
                                            ratingCount=1;
                                        }
                                    });

                                } else {
                                    $('.add-star-rating').css('display', 'block');
                                    $('.add-rating').rating("refresh", {stars: 10});
                                    $('.caption').remove();
                                    $('.clear-rating').remove();
                                    $('.rating-container').css('display', 'inline-block');
                                }
                            }
                        });
                    }
                }
            });
        } else {
            alert("Необходимо зарегистрироваться ");
        }
    });

    $('.btn-rate').click(function () {
        var current = $(this);
        mark = current.parent().parent().parent().find(".add-rating").val();
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'rate-movie', movieId: movieId, mark: mark},
            success: function (result) {
                if (result === "true") {
                    ratingCount = 1;
                    current.css('display', 'none');
                    $('.add-rating').rating("refresh", {displayOnly: true, stars: 10, disabled: false, value: mark})
                        .css('font-size', ' 1.2em');

                } else {
                    //TODO message for error;
                }
            }
        });
    });

    $('#save-review').click(function () {
        if (ratingCount === 1) {
            var current = $(this);
            var dataArray = $('#form-add-review').serializeArray();
            var title = dataArray[0].value;
            var review = dataArray[1].value;
            $.ajax({
                type: 'POST',
                url: $url,
                data: {command: 'review-movie', movieId: movieId, title: title, review: review},
                success: function (result) {
                    if (result === "true") {
                        ratingCount = 0;
                        $('.add-review').css('display','none');
                        $('.text-right').css('display', 'block');

                        var login=document.getElementById('user-login').textContent;
                        $("<hr><div class='row review'>"+
                        "<div class='col-md-12'>"+
                            "<div class='review-block-rate'>"+
                            "<input class='user-stars rating rating-loading'  data-size='xs'" +
                            " value='"+mark+"' data-min='0' data-max='10' data-step='1'>"+
                            "</div>"+ login+
                            "<h4 class='review-title'>"+title+"</h4>"+
                            "<span class='pull-right'>"+new Date().toJSON().split('T')[0]+"</span>"+
                            "<p class='review-text'>"+review+"</p>"+
                            "</div>"+
                            "</div>").insertBefore(".add-review");
                        $('.user-stars').rating("refresh", {displayOnly: true, stars: 10, disabled: false});

                    } else {
                        //TODO message for error;
                    }
                }
            });
        } else {
            alert("You should rate the movie first");
        }
    });

});
