<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset="UTF-8">
    <title>teacher</title>
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
<h1 align="center">Work with students </h1>
<h2 align="center"> Please enter id, student name, theme and grade to add or edit. </h2>
<form action="/myweb/addmarks" method=post>
    <input required name=name placeholder=name type=text> <br/>
    <input required name=id placeholder=id type=text> <br/>
    <input required name=theme placeholder=theme type=text> <br/>
    <input required name=grade placeholder=grade type=text> <br/>
    <input type=submit value=Add!> <input type=reset value=Clean>
</form>
<p > </p>
<p > </p>
<h2 align="center"> Please enter id, student name, theme and grade to remove. </h2>
<form action="/myweb/removemarks" method=post>
    <input required name=name placeholder=name type=text> <br/>
    <input required name=id placeholder=id type=text> <br/>
    <input required name=theme placeholder=theme type=text> <br/>
    <input type=submit value=Remove!> <input type=reset value=Clean>
</form>
<p > </p>
<% if (session.getAttribute("grade") == null ){
    session.setAttribute("grade", "There will result your work");} %>
<h3 style="color: blue"> <%= session.getAttribute("grade")%></h3>
<% session.setAttribute("grade",null); %>
<div id="footer">
    <jsp:include page="my.jsp"/>
</div>
</body>
</html>
