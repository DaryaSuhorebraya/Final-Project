$(document).ready(function () {
    var $url = 'Controller';
    var userId = $('#user-id').text();
    $('.input-stars').rating("refresh",{ stars: 10});
    $('.caption').remove();
    $('.clear-rating').remove();
    $('.rating-xs').css('font-size',' 1.2em');
    $('.rating-container').css('display', 'inline-block');
    var lang = $('html').attr("lang");
    var yetRatedMsg;
    var needAuthMsg;
    var errorMsg;
    var bannedUser;

    if (lang === "ru_RU") {
        yetRatedMsg="Вы уже оценили этот фильм";
        needAuthMsg="Для этого действия необходимо авторизоваться";
        errorMsg="Ошибка в процессе выполнения операции";
        bannedUser="У вас нет прав на совершение данной операции";
    } if (lang === "en_EN") {
        yetRatedMsg="You have reviewed this movie yet";
        needAuthMsg="You have to log in for this action";
        errorMsg="Error during procedure";
        bannedUser="You have no rights to do this procedure";
    }

    function hideMsg(){
        setTimeout(function(){
            $('#message').empty();
        }, 3000);
    }
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
                        $('#message').html('<div class="alert alert-info fade in">' +
                            '<button type="button" class="close close-alert" data-dismiss="alert"' +
                            ' aria-hidden="true">×</button>'+yetRatedMsg +
                            '</div>');
                        hideMsg();
                    } if (result=='isBanned'){
                        $('#message').html('<div class="alert alert-danger fade in">' +
                            '<button type="button" class="close close-alert" data-dismiss="alert"' +
                            ' aria-hidden="true">×</button>'+bannedUser +
                            '</div>');
                        hideMsg();
                    }  if (result === "false") {
                        event.stopPropagation();
                        current.css('display','none');
                        current.parent().parent().parent().find(".star-rating").fadeIn(1500).css('display','inline-block');
                        current.parent().parent().parent().find('.btn-rate').css('display',"inline-block");
                    }
                }
            });
        } else {
            $('#message').html('<div class="alert alert-info fade in">' +
                '<button type="button" class="close close-alert" data-dismiss="alert"' +
                ' aria-hidden="true">×</button>'+needAuthMsg +
                '</div>');
            hideMsg();
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
                    current.parent().parent().find('.single-star').css('display','block').css('color','rgb(242, 56, 6)');
                } if (result=='isBanned'){
                    $('#message').html('<div class="alert alert-danger fade in">' +
                        '<button type="button" class="close close-alert" data-dismiss="alert"' +
                        ' aria-hidden="true">×</button>'+bannedUser +
                        '</div>');
                    hideMsg();
                }  if (result === "false") {
                    $('#message').html('<div class="alert alert-danger fade in">' +
                        '<button type="button" class="close close-alert" data-dismiss="alert"' +
                        ' aria-hidden="true">×</button>'+errorMsg +
                        '</div>');
                    hideMsg();
                }
            }
        });
    });
    $('html').click(function() {
        $('.star-rating').css('display','none');
        $('.single-star').css('display','block');
    });
    
});
