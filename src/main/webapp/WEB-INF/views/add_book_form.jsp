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

    var RestPostStoreBook = function() {
        var JSONObject= {
            'title': $('#title').val(),
            'year': $('#year').val(),
            'author': $('#author').val(),
            'category': $('#category').val()
        };
        $.ajax({
            type: 'POST',
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
                alert(jqXHR.status + ' ' + 'Mandatory fields Title, Year, Author must be filled');
            }
        });
    }
</script>
<h1>Add New Book</h1>
    <table >
        <tr>
            <td>Title : </td>
            <td><input id="title"/></td>
        </tr>
        <tr>
            <td>Year :</td>
            <td><input id="year"/></td>
        </tr>
        <tr>
            <td>Author :</td>
            <td><input id="author" /></td>
        </tr>
        <tr>
            <td>Category :</td>
            <td><input id="category" /></td>
        </tr>
        <tr>
            <td> </td>
            <td><button type="button" onclick="RestPostStoreBook()">Store Book</button></td>
        </tr>
    </table>
</html>