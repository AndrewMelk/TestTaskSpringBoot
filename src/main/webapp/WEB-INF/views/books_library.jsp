<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books Library</title>
</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
    var prefix = '/library';

    var RestGetAllBooks = function() {
        $.ajax({
            type: 'GET',
            url: prefix + '/books',
            dataType: 'json',
            async: true,
            success: function(result) {
                $('.result').text('');
                $.each(result, function(i, field){
                    $(".result").append("Id: " + field.id + "<br>");
                    $(".result").append("Title: " + field.title + "<br>");
                    $(".result").append("Year: " + field.year + "<br>");
                    $(".result").append("Author: " + field.author + "<br>");
                    $(".result").append("Category: " + field.category + "<br><br>");
                });
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });

    }

    var RestGetBookById = function() {
        $.ajax({
            type: 'GET',
            url: prefix + '/book/' + $('#book_id').val(),
            dataType: 'json',
            async: true,
            success: function(result) {
                $(".result").text('');
                $(".result").append("Id: " + result.id + "<br>");
                $(".result").append("Title: " + result.title + "<br>");
                $(".result").append("Year: " + result.year + "<br>");
                $(".result").append("Author: " + result.author + "<br>");
                $(".result").append("Category: " + result.category + "<br><br>");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + 'Book with id = ' + $('#book_id').val() + ' not found');
            }
        });

    }



    var RestDeleteBookById = function() {
        $.ajax({
            type: 'DELETE',
            url: prefix + '/book/' + $('#book_id').val(),
            dataType: 'json',
            async: true,
            success: function(result) {
                alert(result);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
    }

    var redirectToEditForm = function () {
        window.location.replace("/library/editForm/"+ $('#book_id').val());
    }
</script>

<body>

<h3>Book Library</h3>
<button type="button" onclick="RestGetAllBooks()">Get All Books</button>
<button type="button" onclick="RestGetBookById()">Get Book By Id</button>
<a href="/library/storeBookForm"><button type="button"> Store Book</button></a>
<button type="button" onclick="RestDeleteBookById()">Delete Book by id</button>
<button type="button" onclick="redirectToEditForm()">Update Book</button>
</a>

<input id="book_id" placeholder="Book Id"/>
<h3 class="result"></h3>
</body>
</html>