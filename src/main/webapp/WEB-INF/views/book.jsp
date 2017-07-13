<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Book ${book.title}</h1>
<table border="2" width="70%" cellpadding="2">
    <tr><th>Id</th><th>Title</th><th>Year</th><th>Author</th></th><th>Category</th><th>Edit</th><th>Delete</th></tr>
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.year}</td>
            <td>${book.author}</td>
            <td>${book.category}</td>
            <td><a href="edit_book/${book.id}">Edit</a></td>
            <td><a href="book/${book.id}" data-method="delete">Delete</a></td>
        </tr>

</table>
<br/>
<a href="/library/add_book_form">Add New Book</a>
