<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset="UTF-8">
    <title>admin</title>
    <style type="text/css">
        BODY {
            background-image: url(/img/fon_pero_cvet.jpg); /* Путь к фоновому рисунку */
        }

        #footer {
            position: fixed;
            bottom: 0;
            padding: 2px;
            color: black;
            width: 100%;
        } </style>
</head>
<body align=center>
<jsp:include page="href.jsp"/>
<h1 center> Admin work page </h1>
<nav><a href="/myweb/admins/addperson">Add student, teacher and teacher's salary</a> |
    <a href="/myweb/admins/avg-salary">Teacher's salary average</a> |
    <a href="/myweb/admins/info2">Teacher's info</a>
</nav>
<div id="footer">
    <jsp:include page="my.jsp"/>
</div>
</body>
</html>
