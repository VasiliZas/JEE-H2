<%@ page import="static vasilizas.repository.TeacherRepository.teacherList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
<p center>     <c></c> </p>
<br>
<br> <h3 style="color: blue"><%= teacherList.toString() %> <br/>
<p center>     <c></c>  </p>
<div id="footer">
    <jsp:include page="my.jsp"/>
</div>
</body>
</html>