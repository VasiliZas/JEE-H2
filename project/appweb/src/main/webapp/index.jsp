<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset="UTF-8">
    <title>My Web Project</title>
</head>
<body align=center>
<jsp:include page="href.html"/>
<h1 center> School web project </h1>

<h1> Please enter your status, name, login and password </h1>
<form action="/myweb/auth" method=post>
    <input name=type placeholder=type type=text> <br/>
    <input name=name placeholder=name type=text> <br/>
    <input name=login placeholder=login type=text> <br/>
    <input name=password placeholder=password type=text> <br/>
    <input type=submit value=OK>
</form>

<jsp:include page="my.jsp"/>

</body>
</html>