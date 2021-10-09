<%@ page import="static vasilizas.repository.TeacherRepository.teacherList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset=UTF-8>
    <title>Teacher Add</title>
</head>
<body aling = center>
<h1 > You add teacher </h1>"
<%=
session.getAttribute("newteacher")
%>

<a href="/myweb/index.html">Back to HOME</a>
</body>
</html>
