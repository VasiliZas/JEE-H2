<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset=UTF-8>
    <title>Add person and parameter</title>
    <style>
        .text {
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
<h1 align="center">Work with teacher </h1>
<h2 align="center"> Please enter the name and the number of months for which you want to receive an average
    salary. </h2>
<form action="/myweb/admins/averagesalary" method=post>
    <input required name=id placeholder=id type=text> <br/>
    <input required name=number placeholder=number type=text> <br/>
    <input type=submit value=Go!> <input type=reset value=Clean>
</form>
<p></p>
<p></p>
<c:forEach var="teacher" items="${avgSalary}">
    <h3 style="color: blue"><c:out value="${teacher}"/></h3></c:forEach>
<div id="footer">
    <jsp:include page="my.jsp"/>
</div>
</body>
</html>
