<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset="UTF-8">
    <title>Authentication</title>
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

<h1 center>Authentication </h1>

<h1> Please enter your status, name, login and password </h1>
<form action="/myweb/auth" method=post>
    <input required name=type placeholder=type type=text> <br/>
    <input required name=name placeholder=name type=text> <br/>
    <input required name=login placeholder=login type=text> <br/>
    <input required name=password placeholder=password type=password> <br/>
    <input type=submit value=Go!> <input type=reset value=Clean>
</form>
<div id="footer">
    <jsp:include page="my.jsp"/>
</div>
</body>
</html>
