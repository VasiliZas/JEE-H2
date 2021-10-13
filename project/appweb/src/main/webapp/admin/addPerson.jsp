
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset=UTF-8>
    <title>Add Teacher parametr</title>
</head>
<body align="center">
<h1 align="center">Work with teacher </h1>
<h2 align="center"> Please enter age, name, login and password teacher </h2>
<form action="/myweb/addteacher" method=post>
    <input required name=name placeholder=name type=text> <br/>
    <input required name=age placeholder=age type=text> <br/>
    <input required name=login placeholder=login type=text> <br/>
    <input required name=password placeholder=password type=password> <br/>
    <input type=submit value=Add!> <input type=reset value=Clean>
</form>
<p > </p>
<h2 align="center"> Please enter name, login and salary teacher </h2>
<form action="/myweb/teacher-salary" method=post>
    <input required name=name placeholder=name type=text> <br/>
    <input required name=login placeholder=login type=text> <br/>
    <input required name=salary placeholder=salary type=password> <br/>
    <input type=submit value=Add!> <input type=reset value=Clean>
</form>
<p > </p>
<h2 align="center"> Please enter name, login and password student </h2>
<form action="/myweb/add-student" method=post>
    <input required name=name placeholder=name type=text> <br/>
    <input required name=age placeholder=age type=text> <br/>
    <input required name=login placeholder=login type=text> <br/>
    <input required name=password placeholder=password type=password> <br/>
    <input type=submit value=Add!> <input type=reset value=Clean>
</form>
</body>
</html>
