<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books Library</title>
</head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="application/javascript">
    var prefix = '/library';
    var RestPut = function() {
        var JSONObject= {
            'id': $('#id').val(),
            'title': $('#title').val(),
            'year': $('#year').val(),
            'author': $('#author').val(),
            'category': $('#category').val(),

        };

        $.ajax({
            type: 'PUT',
            url:  prefix + '/book',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(JSONObject),
            dataType: 'json',
            async: true,
            success: function(result) {
                alert(result);
//                window.location.replace("/library/");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
    }

</script>
<h1>Edit Book</h1>
    <table >
        <tr>
            <td></td>
            <td><input id="id" path="id" required="required" value="${book.id}" hidden="hidden"/></td>
        </tr>
        <tr>
            <td>Title : </td>
            <td><input id="title" required="required" value="${book.title}"/></td>
        </tr>
        <tr>
            <td>Year :</td>
            <td><input id="year" required="required" value="${book.year}"/></td>
        </tr>
        <tr>
            <td>Author :</td>
            <td><input id="author" value="${book.author}"/></td>
        </tr>

        <tr>
            <td>Category :</td>
            <td><input id="category" value="${book.category}" /></td>
        </tr>

    </table>

<tr>
    <td> </td>
    <td><button type="button" onclick="RestPut()">Save Book</button></td>
</tr>
</html>