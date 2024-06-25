<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>All Books</title>
</head>
<body>
    <h1>All Books</h1>
    <table>
        <thead>
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>Owner</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td><a href="/books/${book.id}">${book.title}</a></td>
                    <td>${book.author}</td>
                    <td>${book.owner.username}</td>
                    <td>
                        <c:if test="${book.owner.id == sessionScope.userId}">
                            <a href="/books/edit/${book.id}">Edit</a> | 
                            <a href="/books/delete/${book.id}">Delete</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="/newBook">Add a New Book</a>
    <a href="/bookmarket">Back to Book Market</a>
</body>
</html>
