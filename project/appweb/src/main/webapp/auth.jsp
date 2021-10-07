<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset=UTF-8>
    <title>authentication</title>
</head>
<body align=center>
<p> You are on the login page
</p>
<h1> Please enter your status, name, login and password </h1>
<form action="/myweb/auth" method=post>
    <input name=type placeholder=type type=text> <br/>
    <input name=name placeholder=name type=text> <br/>
    <input name=login placeholder=login type=text> <br/>
    <input name=password placeholder=password type=text> <br/>
    <input type=submit value=OK>
</form>
<p center>
    <c></c>
</p>
<button onclick="history.back()">Go Back</button>
</body>
</html>