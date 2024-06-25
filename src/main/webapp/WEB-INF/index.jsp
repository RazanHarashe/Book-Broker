<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Book Broker - Login/Register</title>
</head>
<body>
    <h1>Welcome to Book Broker</h1>
    <form action="/register" method="POST">
        <h2>Register</h2>
        <label>Username:</label>
        <input type="text" name="username" value="${newUser.username}">
        <c:if test="${not empty errors['username']}">
            <span style="color:red">${errors['username']}</span>
        </c:if>
        <br>
        <label>Email:</label>
        <input type="text" name="email" value="${newUser.email}">
        <c:if test="${not empty errors['email']}">
            <span style="color:red">${errors['email']}</span>
        </c:if>
        <br>
        <label>Password:</label>
        <input type="password" name="password">
        <c:if test="${not empty errors['password']}">
            <span style="color:red">${errors['password']}</span>
        </c:if>
        <br>
        <label>Confirm Password:</label>
        <input type="password" name="passwordConfirmation">
        <c:if test="${not empty errors['passwordConfirmation']}">
            <span style="color:red">${errors['passwordConfirmation']}</span>
        </c:if>
        <br>
        <input type="submit" value="Register">
    </form>

    <form action="/login" method="POST">
        <h2>Login</h2>
        <label>Email:</label>
        <input type="text" name="email" value="${newLogin.email}">
        <c:if test="${not empty errors['email']}">
            <span style="color:red">${errors['email']}</span>
        </c:if>
        <br>
        <label>Password:</label>
        <input type="password" name="password">
        <c:if test="${not empty errors['password']}">
            <span style="color:red">${errors['password']}</span>
        </c:if>
        <br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
