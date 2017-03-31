$(document).ready(function () {
    var $url = 'Controller';
    var $actorId;
    var $firstName;
    var $lastName;
    var $initials;

    $('.delete-actor').click(function () {
        if (confirm("Delete?")) {
            var idActor = $(this).siblings('.actor-id').text();
            var current = $(this);
            $.ajax({
                type: 'POST',
                url: $url,
                data: {command: 'delete-actor', actorId: idActor},
                success: function (result) {
                    //TODO confirm action
                    if (result === "true") {
                        current.parent().siblings().first().remove();
                        current.parent().remove();
                    } else {
                        //message for error;
                    }
                }
            });
        }
    });
    $('.img-edit').click(function () {
        var current = $(this);
        current.parent().find('.form-img').css('display', 'inline-block');
        current.parent().find('.cancel-img').css('display', 'inline-block');
        current.css('display', 'none');
    });
    $('.cancel-img').click(function () {
        var current = $(this);
        current.parent().find('.form-img').css('display', 'none');
        current.parent().find('.img-edit').css('display', 'block');
        current.css('display', 'none');
    });


    $('.save-img').click(function () {
        var idActor = $(this).parent().siblings('.actor-id').text();
        var current = $(this);
        var img;
        $.ajax({
            type: 'POST',
            url: 'UploadServlet',
            enctype: 'multipart/form-data',
            upload: true,
            data: {command: 'reload-actor-image', actorId: idActor},
            success: function (result) {
                //TODO confirm action
                /*if (result === "true") {
                 var d = new Date();
                 $("#myimg").attr("src", "/myimg.jpg?"+d.getTime());
                 } else {
                 //message for error;
                 }*/
                var filePath = result[0];
                var d = new Date();
                current.siblings().attr("src", "./" + filePath);
            }
        });
    });

    $('#edit-modal').on('show.bs.modal', function (event) {
        var modal = $(this);
        var form = $('#actorForm');
        var firstName = $.trim(form.find('input[name="firstName"]').val($firstName));
        var lastName = $.trim(form.find('input[name="lastName"]').val($lastName));
    });

    $('.open-modal-edit').click(function () {
        var current = $(this);
        $actorId = $.trim(current.parent().parent().find('.actor-id').text());
        var whitespace = String.fromCharCode(160);
        $initials = current.siblings().first();
        $firstName = current.siblings().first().text().split(whitespace)[0];
        $lastName = current.siblings().first().text().split(whitespace)[1];
    });
    $('.save-edit-actor').click(function () {
        var form = $('#actorForm');
        //var idActor=$.trim(form.siblings().text());
        var firstName = $.trim(form.find('input[name="firstName"]').val());
        var lastName = $.trim(form.find('input[name="lastName"]').val());
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'edit-actor', actorId: $actorId, firstName: firstName, lastName: lastName},
            success: function (result) {
                //TODO confirm action
                if (result === "true") {
                    $('#edit-modal').modal('toggle');
                    $initials.text(firstName + String.fromCharCode(160) + lastName);
                } else {
                    modalError("Ошибка", "during add procedure");
                    //al();
                }
            }
        });
    });
    $('#apply-add-actor').click(function () {
        var form = $('#actorAddForm');
        var firstNameEn = $.trim(form.find('input[name="firstNameEn"]').val());
        var lastNameEn = $.trim(form.find('input[name="lastNameEn"]').val());
        var firstNameRu = $.trim(form.find('input[name="firstNameRu"]').val());
        var lastNameRu = $.trim(form.find('input[name="lastNameRu"]').val());
        $.ajax({
            type: 'POST',
            url: $url,
            data: {
                command: 'add-actor', firstNameEn: firstNameEn,
                lastNameEn: lastNameEn, firstNameRu: firstNameRu, lastNameRu: lastNameRu
            },
            dataType: 'json',
            success: function (actorId) {
                form.replaceWith("<form method='post'" +
                    "action='UploadServlet?command=upload-actor-image&actorId=" + actorId + "'" +
                    "enctype='multipart/form-data'>" +
                    "<input name='data' type='file'>" +
                    "<input type='submit'>" +
                    "</form>");
                $('#apply-add-actor').css('display','none');
            }
        });
    });


});
