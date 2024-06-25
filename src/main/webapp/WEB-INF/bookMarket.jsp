<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Book Market</title>
</head>
<body>
    <h1>Welcome to the Book Market, ${user.username}</h1>
    <a href="/logout">Logout</a>

    <h2>Available Books to Borrow</h2>
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
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.owner.username}</td>
                    <td>
                        <a href="/bookmarket/${book.id}">Borrow</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Books I'm Borrowing</h2>
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
            <c:forEach var="myBook" items="${myBooks}">
                <tr>
                    <td>${myBook.title}</td>
                    <td>${myBook.author}</td>
                    <td>${myBook.owner.username}</td>
                    <td>
                        <a href="/bookmarket/return/${myBook.id}">Return</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
