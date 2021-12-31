<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset=UTF-8>
    <title>Add person and parameter</title>
    <style>
        #text {
            text-align: center;
        }

        #footer {
            position: fixed;
            bottom: 0;
            padding: 2px;
            color: black;
            width: 100%;
        }
    </style>
</head>
<body align="center">
<jsp:include page="href.jsp"/>
<h1 align="center">Work with users </h1>
<h2 align="center"> Please enter age, name, login and password teacher </h2>
<form action="/myweb/admins/addteacher" method=post>
    <input required name=name placeholder=name type=text><br/>
    <input required name=age placeholder=age type=text> <br/>
    <input required name=login placeholder=login type=text> <br/>
    <input required name=password placeholder=password type=text> <br/>
    <input type=submit value=Add!> <input type=reset value=Clean>
</form>
<p></p>
<h2 align="center"> Please enter name, id and salary teacher </h2>
<form action="/myweb/admins/teachersalary" method=post>
    <input required name=name placeholder=name type=text> <br/>
    <input required name=id placeholder=id type=text> <br/>
    <input required name=salary placeholder=salary type=text> <br/>
    <input type=submit value=Add!> <input type=reset value=Clean>
</form>
<p></p>
<h2 align="center"> Please enter name, age, login and password student </h2>
<form action="/myweb/admins/addstudent" method=post>
    <input required name=name placeholder=name type=text> <br/>
    <input required name=age placeholder=age type=text> <br/>
    <input required name=login placeholder=login type=text> <br/>
    <input required name=password placeholder=password type=text> <br/>
    <input type=submit value=Add!> <input type=reset value=Clean>
</form>
<p></p>
<c:forEach var="person" items="${add}">
    <h3 style="color: blue"><c:out value="${person}"/></h3></c:forEach>
<div id="footer">
    <jsp:include page="my.jsp"/>
</div>
</body>
</html>
