$(document).ready(function () {
    var $url = 'Controller';
    var $titleEn;
    var $titleRu;
    var $releaseYear;
    var $descrEn;
    var $descrRu;
    var $movieId;

    $('#addMovieBtn').click(function () {
        var dataArray=$('#movieForm').serializeArray();
        $titleEn=$.trim(dataArray[0].value);
        $titleRu=$.trim(dataArray[1].value);
        $releaseYear=dataArray[2].value;
        $descrEn=dataArray[3].value;
        $descrRu=dataArray[4].value;

        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'add-movie', titleEn: $titleEn, titleRu: $titleRu,
                releaseYear: $releaseYear, descriptionEn: $descrEn, descriptionRu:$descrRu},
            dataType: 'json',
            success: function (data) {
                $('#addMovieBtn').css("display", "none");
                $("textarea").prop('disabled', true);
                $("input").prop('disabled', true);
                $('.thumbnail').remove();
                $movieId=data;
                $('.menu').css("display", "block");
                /*$('.img-form').css('display','block').append("<form method='post'" +
                    "action='UploadServlet?command=upload-movie-image&movieId=" + data + "'" +
                    "enctype='multipart/form-data'>" +
                    "<input name='data' type='file'>" +
                    "<input type='submit'>" +
                    "</form>");*/

            },
            error: function (textStatus) {
                alert(textStatus);
            }
        });
    });
    $('#3rdStep').click(function () {
        $('.menu').css("display", "none");
        $('.img-form').css('display','block').append("<form method='post'" +
            "action='UploadServlet?command=upload-movie-poster&movieId=" + $movieId + "'" +
            "enctype='multipart/form-data'>" +
            "<input name='data' type='file'>" +
            "<input type='submit'>" +
            "</form>");
    });
    
    $('#addCountry').click(function () {
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'view-all-countries'},
            dataType: 'json',
            success: function (data) {
                var options;
                for(var i=0; i<data.length;i++){
                    options+="<option>"+data[i].name+"</option>";
                }
                $('#dropdown-add-country').children().children().children().last().html(options).selectpicker('refresh');
                $('.bootstrap-select').css("width", "500px");
            },
            error: function (textStatus) {
                alert(textStatus);
            }
        });
    });
    $('#addGenre').click(function () {
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'view-all-genres'},
            dataType: 'json',
            success: function (data) {
                var options;
                for(var i=0; i<data.length;i++){
                    options+="<option>"+data[i].name+"</option>";
                }
                $('#dropdown-add-genre').children().children().children().last().html(options).selectpicker('refresh');
            },
            error: function (textStatus) {
                alert(textStatus);
            }
        });
    });
    $('#addActor').click(function () {
        $.ajax({
            type: 'POST',
            url: $url,
            data: {command: 'view-all-actors-json'},
            dataType: 'json',
            success: function (data) {
                var options;
                for(var i=0; i<data.length;i++){
                    options+="<option>"+data[i].firstName+" "+data[i].lastName+"</option>";
                }
                $('#dropdown-add-actor').children().children().children().last().html(options).selectpicker('refresh');
            },
            error: function (textStatus) {
                alert(textStatus);
            }
        });
    });
    $('#addCountryBtn').click(function (){
        var countryNames = $('.selectpicker option:selected');
        for (var i=0; i<countryNames.length;i++){
            var name=countryNames[i].text;
            $.ajax({
                type: 'POST',
                url: $url,
                data: {command:'add-country-for-movie',movieId: $movieId, countryName: name},
                success: function (result) {
                    if (result==="true"){
                    } else {
                        //message for error;
                    }
                }
            });
        }
        var text="";
        for (var j=0; j<countryNames.length;j++){
                text+=countryNames[j].text+" ";
        }
        $('#country-li').replaceWith('<p class="panel panel-text">'+text+'</p>');
        
    });
    $('#addGenreBtn').click(function (){
        var genreNames = $('.selectpicker option:selected');
        for (var i=0; i<genreNames.length;i++){
            var name=genreNames[i].text;
            $.ajax({
                type: 'POST',
                url: $url,
                data: {command:'add-genre-for-movie',movieId: $movieId, genreName: name},
                success: function (result) {
                    if (result==="true"){
                    } else {
                        //message for error;
                    }
                }
            });
        }
        var text="";
        for (var j=0; j<genreNames.length;j++){
                text+=genreNames[j].text+" ";
        }
        $('#genre-li').replaceWith('<p class="panel panel-text">'+text+'</p>');
    });
    $('#addActorBtn').click(function (){
        var actorNames = $('.selectpicker option:selected');
        for (var i=0; i<actorNames.length;i++){
            var name=actorNames[i].text;
            var firstName=$.trim(name.split(" ")[0]);
            var lastName=$.trim(name.split(" ")[1]);
            $.ajax({
                type: 'POST',
                url: $url,
                data: {command:'add-actor-for-movie',movieId: $movieId, firstName: firstName, lastName: lastName},
                success: function (result) {
                    if (result==="true"){
                    } else {
                        //message for error;
                    }
                }
            });
        }
        var text="";
        for (var j=0; j<actorNames.length;j++){
            text+=actorNames[j].text+" ";
        }
        $('#actor-li').replaceWith('<p class="panel panel-text">'+text+'</p>');
    });

});
