<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset="UTF-8">
    <title>admin</title>
    <style type="text/css">
        #footer {
            position: fixed;
            bottom: 0;
            padding: 2px;
            color: black;
            width: 100%;
        } </style>
</head>
<body align=center background="img/fon_pero_cvet.jpg">
<jsp:include page="href.html"/>
<h1 center> Admin work page </h1>
<nav><a href="/myweb/admin/addpersonpar">Add teacher and salary</a> |
    <a href="/myweb/admin/avg-salary">Teacher's salary average</a>  |
    <a href="/myweb/admin/info">Teacher's info</a>
    </nav>
<div id="footer">
    <jsp:include page="my.jsp"/>
</div>
</body>
</html>