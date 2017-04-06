$(document).ready(function () {
    var $url = 'Controller';
    $.ajax({
        type: 'POST',
        url: $url,
        data: {command: 'get-user-statistics'},
        dataType: 'json',
        success: function (data) {
            new Morris.Line({
                element: 'user-chart',
                data: data,
                xkey: 'label',
                ykeys: ['value'],
                labels: ['Count']
            });

        },
        error: function (textStatus) {
            alert('err');
        }
    });
    $.ajax({
        type: 'POST',
        url: $url,
        data: {command: 'get-review-statistics'},
        dataType: 'json',
        success: function (data) {
            new Morris.Bar({
                element: 'review-chart',
                data: data,
                xkey: 'label',
                ykeys: ['value'],
                labels: ['Count']
            });

        },
        error: function (textStatus) {
            alert('err');
        }
    });
    $.ajax({
        type: 'POST',
        url: $url,
        data: {command: 'get-genre-statistics'},
        dataType: 'json',
        success: function (data) {
            new Morris.Donut({
                element: 'genre-chart',
                data: data
            });
        },
        error: function (textStatus) {
            alert('err');
        }
    });
    $('.delete-genre').click(function () {
        if (confirm("Delete?")) {
            var idGenre = $(this).siblings('.id-genre').text();
            var current = $(this);
            $.ajax({
                type: 'POST',
                url: $url,
                data: {command: 'delete-genre', genreId: idGenre},
                success: function (result) {
                    //TODO confirm action
                    if (result === "true") {
                        current.parent().remove();
                    } else {
                        //message for error;
                    }
                }
            });
        }
    });

});

