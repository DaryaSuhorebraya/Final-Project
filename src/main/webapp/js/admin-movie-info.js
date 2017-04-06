$(document).ready(function () {
    var $url = 'Controller';
    var movieId = $('#movie-id').text();
    var $currentYear;
    var $currentTitle;
    var input;
    var text;

    $('#input-stars').rating("refresh", {displayOnly: true, stars: 10, disabled: false});

    $('#year-edit').click(function () {
        $currentYear = $(this);
        input = $currentYear.siblings();
        text = $.trim($currentYear.siblings().text());
        $('#year-text').replaceWith("<form id='form-year'>" +
            "<input type='text' value=" + text + ">" +
            "</form>");
        $('#save-edit-year').css("display", "inline-block");
        $('#cancel-edit-year').css("display", "inline-block");
        $currentYear.css("display", "none");

    });

    $('#save-edit-year').click(function () {
        var value = $.trim($('#form-year').children().first().val());
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'edit-movie', movieId: movieId, field: 'release_year', value: value},
            success: function (result) {
                if (result === "true") {
                    input[0].textContent = " " + value;
                    $('#form-year').replaceWith(input[0]);
                    $currentYear.css("display", "inline-block");
                    $('#save-edit-year').css("display", "none");
                    $('#cancel-edit-year').css("display", "none");
                } else {
                    //TODO message for error;
                }
            }
        });
    });
    $('#cancel-edit-year').click(function () {
        $('#save-edit-year').css("display", "none");
        $('#cancel-edit-year').css("display", "none");
        input[0].textContent = " " + text;
        $('#form-year').replaceWith(input[0]);
        $currentYear.css("display", "inline-block");
    });

    $('#title-edit').click(function () {
        $currentTitle = $(this);
        input = $currentTitle.siblings();
        text = $.trim($currentTitle.siblings().first().text());
        $('#title-text').replaceWith("<form id='form-title'>" +
            "<input type='text' value=" + text + ">" +
            "</form>");
        $('#save-edit-title').css("display", "inline-block");
        $('#cancel-edit-title').css("display", "inline-block");
        $currentTitle.css("display", "none");
    });
    $('#save-edit-title').click(function () {
        var value = $.trim($('#form-title').children().first().val());
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'edit-movie', movieId: movieId, field: 'title_', value: value},
            success: function (result) {
                if (result === "true") {
                    input[0].textContent = " " + value;
                    $('#form-title').replaceWith(input[0]);
                    $currentTitle.css("display", "inline-block");
                    $('#save-edit-title').css("display", "none");
                    $('#cancel-edit-title').css("display", "none");
                } else {
                    //TODO message for error;
                }
            }
        });
    });
    $('#cancel-edit-title').click(function () {
        $('#save-edit-title').css("display", "none");
        $('#cancel-edit-title').css("display", "none");
        input[0].textContent = " " + text;
        $('#form-title').replaceWith(input[0]);
        $currentTitle.css("display", "inline-block");
    });

    $('#description-edit').click(function () {
        $currentTitle = $(this);
        input = $currentTitle.siblings();
        text = $('#description-text').text();
        //field=$.trim($currentTitle.parent().text()).split(':')[0];
        /*$('#description-text').replaceWith( "<form id='form-description'>" +
         "<input type='text' value="+text+">" +
         "</form>" );*/
        $('#description-text').replaceWith("<textarea id='decr-text-area'></textarea>");
        $('#decr-text-area').val(text);
        $('#save-edit-description').css("display", "inline-block");
        $('#cancel-edit-description').css("display", "inline-block");
        $currentTitle.css("display", "none");
    });
    $('#save-edit-description').click(function () {
        var value = $('#decr-text-area').val();
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'edit-movie', movieId: movieId, field: 'description_', value: value},
            success: function (result) {
                if (result === "true") {
                    input[5].textContent = " " + value;
                    $('#decr-text-area').replaceWith(input[5]);
                    $currentTitle.css("display", "inline-block");
                    $('#save-edit-description').css("display", "none");
                    $('#cancel-edit-description').css("display", "none");
                } else {
                    //TODO message for error;
                }
            }
        });
    });
    $('#cancel-edit-description').click(function () {
        $('#save-edit-description').css("display", "none");
        $('#cancel-edit-description').css("display", "none");
        input[5].textContent = " " + text;
        $('#decr-text-area').replaceWith(input[5]);
        $currentTitle.css("display", "inline-block");
    });

    // edit genre for country
    $('#genre-edit').click(function () {
        $currentTitle = $(this);
        input = $currentTitle.siblings();
        $('.delete-genre').css("display", "inline-block");
        $('#plus-genre').css("display", "inline-block");
        $('#cancel-edit-genre').css("display", "inline-block");
        $currentTitle.css("display", "none");
    });
    $('.delete-genre').click(function () {
        if (confirm("Delete?")) {
            $currentTitle = $(this);
            var name = $.trim($(this).prev().text());
            $.ajax({
                type: 'POST',
                url: $url,
                data: {command: 'delete-genre-for-movie', name: name, movieId: movieId},
                success: function (result) {
                    if (result === "true") {
                        $currentTitle.prev().remove();
                        $currentTitle.remove();
                    } else {
                        //TODO message for error;
                    }
                }
            });
        }
    });
    $('#cancel-edit-genre').click(function () {
        $('.delete-genre').css("display", "none");
        $('#plus-genre').css("display", "none");
        $('#cancel-edit-genre').css("display", "none");
        $('#select-genre').css("display", "none");
        $('#save-edit-genre').css("display", "none");
        $('#genre-edit').css("display", "inline-block");
    });
    $('#plus-genre').click(function () {
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'view-genres-not-in-movie', movieId: movieId},
            dataType: 'json',
            success: function (data) {
                var options;
                for (var i = 0; i < data.length; i++) {
                    options += "<option>" + data[i].name + "</option>";
                }
                $('#select-genre').children().children().last().html(options).selectpicker('refresh');
            },
            error: function (textStatus) {
                alert(textStatus);
            }
        });
        $('.delete-genre').css("display", "none");
        $('#plus-genre').css("display", "none");
        $('#select-genre').css("display", "inline-block");
        $('#save-edit-genre').css("display", "inline-block");
    });

    $('#save-edit-genre').click(function () {
        var genreName = $('.selectpicker option:selected').text();
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'add-genre-for-movie', movieId: movieId, genreName: genreName},
            success: function (result) {
                var attr = "Controller?command=view-movies-by-genre-name&genreName=" + genreName;
                //TODO confirm action
                if (result === "true") {
                    $("<a href=" + attr + ">" + genreName + " " + "</a>").insertBefore("#select-genre");
                    $('#save-edit-genre').css("display", "none");
                    $('#select-genre').css("display", "none");
                    $('.delete-genre').css("display", "inline-block");
                    $('#plus-genre').css("display", "inline-block");
                } else {
                    //message for error;
                }
            }
        });
    });

    //edit country
    $('#country-edit').click(function () {
        $currentTitle = $(this);
        input = $currentTitle.siblings();
        $('.delete-country').css("display", "inline-block");
        $('#plus-country').css("display", "inline-block");
        $('#cancel-edit-country').css("display", "inline-block");
        $currentTitle.css("display", "none");
    });

    $('.delete-country').click(function () {
        if (confirm("Delete?")) {
            $currentTitle = $(this);
            var name = $.trim($(this).prev().text());
            $.ajax({
                type: 'POST',
                url: $url,
                data: {command: 'delete-country-for-movie', name: name, movieId: movieId},
                success: function (result) {
                    if (result === "true") {
                        $currentTitle.prev().remove();
                        $currentTitle.remove();
                    } else {
                        //TODO message for error;
                    }
                }
            });
        }
    });
    $('#cancel-edit-country').click(function () {
        $('.delete-country').css("display", "none");
        $('#plus-country').css("display", "none");
        $('#cancel-edit-country').css("display", "none");
        $('#select-country').css("display", "none");
        $('#save-edit-country').css("display", "none");
        $('#country-edit').css("display", "inline-block");
    });

    $('#plus-country').click(function () {
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'view-countries-not-in-movie', movieId: movieId},
            dataType: 'json',
            success: function (data) {
                var options;
                for (var i = 0; i < data.length; i++) {
                    options += "<option>" + data[i].name + "</option>";
                }
                $('#select-country').children().children().last().html(options).selectpicker('refresh');
            },
            error: function (textStatus) {
                alert(textStatus);
            }
        });
        $('.delete-country').css("display", "none");
        $('#plus-country').css("display", "none");
        $('#select-country').css("display", "inline-block");
        $('#save-edit-country').css("display", "inline-block");
    });
    $('#save-edit-country').click(function () {
        var countryName = $('.selectpicker option:selected').text();
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'add-country-for-movie', movieId: movieId, countryName: countryName},
            success: function (result) {
                var attr = "Controller?command=view-movies-by-country-name&countryName=" + countryName;
                //TODO confirm action
                if (result === "true") {
                    $("<a href=" + attr + ">" + countryName + "</a>").insertBefore("#select-country");
                    $('#save-edit-country').css("display", "none");
                    $('#select-country').css("display", "none");
                    $('.delete-country').css("display", "inline-block");
                    $('#plus-country').css("display", "inline-block");
                } else {
                    modalError("Ошибка", "during add procedure");
                    //al();
                }
            }
        });
    });

    //edit actor

    $('#actor-edit').click(function () {
        $currentTitle = $(this);
        input = $currentTitle.siblings();
        $('.delete-actor').css("display", "inline-block");
        $('#plus-actor').css("display", "inline-block");
        $('#cancel-edit-actor').css("display", "inline-block");
        $currentTitle.css("display", "none");
    });

    $('.delete-actor').click(function () {
        if (confirm("Delete?")) {
            $currentTitle = $(this);
            var text = $.trim($(this).prev().text());
            var fName = $.trim(text.split(" ")[0]);
            var lName = $.trim(text.split(" ")[1]);
            $.ajax({
                type: 'POST',
                url: $url,
                data: {command: 'delete-actor-for-movie', firstName: fName, lastName: lName, movieId: movieId},
                success: function (result) {
                    if (result === "true") {
                        $currentTitle.prev().remove();
                        $currentTitle.remove();
                    } else {
                        //TODO message for error;
                    }
                }
            });
        }
    });
    $('#cancel-edit-actor').click(function () {
        $('.delete-actor').css("display", "none");
        $('#plus-actor').css("display", "none");
        $('#cancel-edit-actor').css("display", "none");
        $('#select-actor').css("display", "none");
        $('#save-edit-actor').css("display", "none");
        $('#actor-edit').css("display", "inline-block");
    });

    $('#plus-actor').click(function () {
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'view-actors-not-in-movie', movieId: movieId},
            dataType: 'json',
            success: function (data) {
                var options;
                for (var i = 0; i < data.length; i++) {
                    options += "<option>" + data[i].firstName + " " + data[i].lastName + "</option>";
                }
                $('#select-actor').children().children().last().html(options).selectpicker('refresh');
            },
            error: function (textStatus) {
                alert(textStatus);
            }
        });
        $('.delete-actor').css("display", "none");
        $('#plus-actor').css("display", "none");
        $('#select-actor').css("display", "inline-block");
        $('#save-edit-actor').css("display", "inline-block");
    });
    $('#save-edit-actor').click(function () {
        var text = $('.selectpicker option:selected').text();
        var firstName = $.trim(text.split(" ")[0]);
        var lastName = $.trim(text.split(" ")[1]);
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'add-actor-for-movie', movieId: movieId, firstName: firstName, lastName: lastName},
            success: function (result) {
                var attr = "Controller?command=view-movies-by-actor-initial&actorFName=" + firstName + "&actorLName" + lastName;
                //TODO confirm action
                if (result === "true") {
                    $("<a href=" + attr + ">" + text + "</a>").insertBefore("#select-actor");
                    $('#save-edit-actor').css("display", "none");
                    $('#select-actor').css("display", "none");
                    $('.delete-actor').css("display", "inline-block");
                    $('#plus-actor').css("display", "inline-block");
                } else {
                    modalError("Ошибка", "during add procedure");
                    //al();
                }
            }
        });
    });
    $('#img-edit').click(function () {
        /*var current=$(this);
         $("<form enctype='multipart/form-data'>" +
         "<input name='data' type='file'><br>"+
         "</form>"+
         "<button id='save-img'>Save</button><br>").insertBefore(current);*/
        var current = $(this);
        current.parent().find('.form-img').css('display', 'block');
        current.css('display', 'none');
    });
    $('#save-img').click(function () {
        $.ajax({
            type: 'POST',
            url: 'UploadServlet',
            data: {movieId: movieId},
            success: function (result) {
                //TODO confirm action
                if (result === "true") {

                } else {
                    modalError("Ошибка", "during add procedure");
                    //al();
                }
            }
        });
    });

});
function modalError(title, message) {
    var html = '<div id="modalError" class="modal fade" tabindex="-1" role="dialog" modal="shouldBeOpen" >' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<h4 class="modal-title" id="startModalLabel">' + title + '</h4>' +
        '</div>' +
        '<div class="modal-body">' + message +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-primary" data-dismiss="modal">' +
        '<a href="">продолжить</a>' +
        '</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';
    $('body').append(html);
    $("#modalError").modal();
    $('#modalError').on('shown.bs.modal', function () {
        $(this).find('.modal-dialog').css({
            'margin-top': function () {
                return -($(this).outerHeight() / 2);
            },
            'margin-left': function () {
                return -($(this).outerWidth() / 2);
            },
            'top': '50%',
            'left': '50%',
            'position': 'absolute'
        });
    });

}
function al() {
    var html = '<div class="alert alert-danger">' +
        '<a href="#" class="close" data-dismiss="alert">×</a>' +
        'error' +
        '</div>';
    $('body').append(html);
}