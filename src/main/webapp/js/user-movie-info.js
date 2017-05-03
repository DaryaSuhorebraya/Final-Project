$(document).ready(function () {
    var $url = 'Controller';
    var movieId = $('#movie-id').text();
    var userId = $('#user-id').text();
    var ratingCount = 0;
    var mark;
    var lang = $('html').attr("lang");
    var yetReviewedMsg;
    var needAuthMsg;
    var rateFirstMsg;
    var errorMsg;
    var bannedUser;

    if (lang === "ru_RU") {
        yetReviewedMsg = "Вы уже оставили отзыв на этот фильм";
        needAuthMsg = "Для этого действия необходимо авторизоваться";
        rateFirstMsg = "Необходимо сначало оценить фильм";
        errorMsg = "Ошибка в процессе выполнения операции";
        bannedUser = "У вас нет прав на совершение данной операции";
    } if (lang === "en_EN") {
        yetReviewedMsg = "You have reviewed this movie yet";
        needAuthMsg = "You have to log in for this action";
        rateFirstMsg = "You should rate the movie first";
        errorMsg = "Error during procedure";
        bannedUser = "You have no rights to do this procedure";
    }
    $('.user-stars').rating("refresh", {displayOnly: true, stars: 10, disabled: false});

    function hideMsg() {
        setTimeout(function () {
            $('#message').empty();
        }, 3000);
    }

    $(".add-review-block").on("click", ".leave-review", function () {
        var current = $(this);
        if (document.getElementById('user-login') !== null) {

            $.ajax({
                type: 'POST',
                url: $url,
                data: {command: 'check-review-opportunity', movieId: movieId, userId: userId},
                success: function (result) {
                    if (result === "true") {
                        $('#message').html('<div class="alert alert-info fade in">' +
                            '<button type="button" class="close close-alert" data-dismiss="alert"' +
                            ' aria-hidden="true">×</button>' + yetReviewedMsg +
                            '</div>');
                        hideMsg();
                    } if (result=='isBanned'){
                        $('#message').html('<div class="alert alert-danger fade in">' +
                            '<button type="button" class="close close-alert" data-dismiss="alert"' +
                            ' aria-hidden="true">×</button>'+bannedUser +
                            '</div>');
                        hideMsg();
                    } if (result === "false") {
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
                                            mark = data;
                                            $('.add-star-rating').replaceWith("<div class='review-block-rate'>" +
                                                "<input class='inserted-rating rating rating-loading'  data-size='xs' value='" + data + "' data-min='0'" +
                                                " data-max='10' data-step='1'>" +
                                                "</div>");
                                            $('.inserted-rating').rating("refresh", {
                                                displayOnly: true,
                                                stars: 10,
                                                disabled: false
                                            });
                                            ratingCount = 1;
                                        }
                                    });

                                } if (result=='isBanned'){
                                    $('#message').html('<div class="alert alert-danger fade in">' +
                                        '<button type="button" class="close close-alert" data-dismiss="alert"' +
                                        ' aria-hidden="true">×</button>'+bannedUser +
                                        '</div>');
                                    hideMsg();
                                }if (result === "false") {
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
            $('#message').html('<div class="alert alert-info fade in">' +
                '<button type="button" class="close close-alert" data-dismiss="alert"' +
                ' aria-hidden="true">×</button>' + needAuthMsg +
                '</div>');
            hideMsg();
        }
    });

    $(".add-review-block").click(function (event) {
        event.stopPropagation();
    });
    $('html').click(function () {
        $('.text-right').css('display', 'block');
        $('.add-review').css('display', 'none');
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

                }
                if (result == 'isBanned') {
                    $('#message').html('<div class="alert alert-danger fade in">' +
                        '<button type="button" class="close close-alert" data-dismiss="alert"' +
                        ' aria-hidden="true">×</button>' + bannedUser +
                        '</div>');
                    hideMsg();
                } if (result === "false") {
                    $('#message').html('<div class="alert alert-danger fade in">' +
                        '<button type="button" class="close close-alert" data-dismiss="alert"' +
                        ' aria-hidden="true">×</button>' + errorMsg +
                        '</div>');
                    hideMsg();
                }
            }
        });
    });

    $('#save-review').click(function () {
        if (ratingCount === 1) {
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
                        $('.add-review').css('display', 'none');
                        $('.text-right').css('display', 'block');

                        var login = document.getElementById('user-login').textContent;
                        $("<hr><div class='row review'>" +
                            "<div class='col-md-12'>" +
                            "<div class='review-block-rate'>" +
                            "<input class='user-stars rating rating-loading'  data-size='xs'" +
                            " value='" + mark + "' data-min='0' data-max='10' data-step='1'>" +
                            "</div>" + login +
                            "<h4 class='review-title'>" + title + "</h4>" +
                            "<span class='pull-right'>" + new Date().toJSON().split('T')[0] + "</span>" +
                            "<p class='review-text'>" + review + "</p>" +
                            "</div>" +
                            "</div>").insertBefore(".add-review");
                        $('.user-stars').rating("refresh", {displayOnly: true, stars: 10, disabled: false});

                    } if (result == 'isBanned') {
                        $('#message').html('<div class="alert alert-danger fade in">' +
                            '<button type="button" class="close close-alert" data-dismiss="alert"' +
                            ' aria-hidden="true">×</button>' + bannedUser +
                            '</div>');
                        hideMsg();
                    } if (result === "false") {
                        $('#message').html('<div class="alert alert-danger fade in">' +
                            '<button type="button" class="close close-alert" data-dismiss="alert"' +
                            ' aria-hidden="true">×</button>' + errorMsg +
                            '</div>');
                        hideMsg();
                    }
                }
            });
        } else {
            $('#message').html('<div class="alert alert-info fade in">' +
                '<button type="button" class="close close-alert" data-dismiss="alert"' +
                ' aria-hidden="true">×</button>' + rateFirstMsg +
                '</div>');
            hideMsg();
        }
    });

});
