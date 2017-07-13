<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Book List</h1>
<table border="2" width="70%" cellpadding="2">
    <tr><th>Id</th><th>Title</th><th>Year</th><th>Author</th></th><th>Category</th><th>Edit</th><th>Delete</th></tr>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.id}</td>
            <td><a href="/library/book/${book.id}"/> ${book.title}</td>
            <td>${book.year}</td>
            <td>${book.author}</td>
            <td>${book.category}</td>
            <td><a href="edit_book/${book.id}">Edit</a></td>
            <td><a href="delete_book/${book.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="/library/add_book_form">Add New Book</a>
