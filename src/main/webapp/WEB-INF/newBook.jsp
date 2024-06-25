<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Book</title>
</head>
<body>
    <h1>Add a New Book</h1>
    <form action="/newBook" method="POST">
        <label>Title:</label>
        <input type="text" name="title" value="${book.title}">
        <c:if test="${not empty errors['title']}">
            <span style="color:red">${errors['title']}</span>
        </c:if>
        <br>
        <label>Author:</label>
        <input type="text" name="author" value="${book.author}">
        <c:if test="${not empty errors['author']}">
            <span style="color:red">${errors['author']}</span>
        </c:if>
        <br>
        <label>Thoughts:</label>
        <textarea name="thoughts">${book.thoughts}</textarea>
        <c:if test="${not empty errors['thoughts']}">
            <span style="color:red">${errors['thoughts']}</span>
        </c:if>
        <br>
        <input type="submit" value="Add Book">
    </form>
    <a href="/books">Back to All Books</a>
    <a href="/bookmarket">Back to Book Market</a>
</body>
</html>
