<%@ page import="static vasilizas.repository.TeacherRepository.teacherList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset=UTF-8>
    <title>Teacher info</title>
</head>
<body align=center>

<%=
session.getAttribute("info")
%>
<p center>    <c></c>  </p>
<a href="/myweb/home.html">Back to HOME</a>
<p center>    <c></c>  </p>
<a href="/myweb/admin/admin.html">Back to work page</a>
</body>
</html>
