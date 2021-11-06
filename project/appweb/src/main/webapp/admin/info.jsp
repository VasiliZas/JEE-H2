<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset=UTF-8>
    <title>Teacher info</title>
    <style>
        #footer {
            position: fixed;
            bottom: 0;
            padding: 2px;
            color: black;
            width: 100%;
        }
    </style>
</head>
<body align=center background="../img/fon_pero_cvet.jpg">
<jsp:include page="href.html"/>
<p center>
    <c></c>
</p>
<br>
<br>
<table align="center">
    <h2 align="center" style="color: black">
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Password</th>
        <th>Salary</th>
    </h2>
    <h3 align="center" style="color: blue">
        <c:forEach var="teacher" items="${teacherInfo}">
            <tr>
                <td><c:out value="${teacher.id}"/></td>
                <td><c:out value="${teacher.name}"/></td>
                <td><c:out value="${teacher.login}"/></td>
                <td><c:out value="${teacher.password}"/></td>
                <td><c:out value="${teacher.salary}"/></td>
            </tr>
        </c:forEach>
    </h3>
</table>
<br/>
<p center>
    <c></c>
</p>
<div id="footer">
    <jsp:include page="my.jsp"/>
</div>
</body>
</html>
