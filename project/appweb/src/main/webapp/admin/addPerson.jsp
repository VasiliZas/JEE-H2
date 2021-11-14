<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset=UTF-8>
    <title>Add person and parameter</title>
    <style>
        #text {
            text-align:  center;
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
<body align="center" background="img/fon_pero_cvet.jpg">
<jsp:include page="href.html"/>
<h1 align="center">Work with users </h1>
<h2 align="center"> Please enter age, name, login and password teacher </h2>
<form action="/myweb/addteacher" method=post>
    <input required name=name placeholder=name type=text> <br/>
    <input required name=age placeholder=age type=text> <br/>
    <input required name=login placeholder=login type=text> <br/>
    <input required name=password placeholder=password type=text> <br/>
    <input type=submit value=Add!> <input type=reset value=Clean>
</form>
<p></p>
<h2 align="center"> Please enter name, id and salary teacher </h2>
<form action="/myweb/teacher-salary" method=post>
    <input required name=name placeholder=name type=text> <br/>
    <input required name=id placeholder=id type=text> <br/>
    <input required name=salary placeholder=salary type=text> <br/>
    <input type=submit value=Add!> <input type=reset value=Clean>
</form>
<p></p>
<h2 align="center"> Please enter name, age, login and password student </h2>
<form action="/myweb/add-student" method=post>
    <input required name=name placeholder=name type=text> <br/>
    <input required name=age placeholder=age type=text> <br/>
    <input required name=login placeholder=login type=text> <br/>
    <input required name=password placeholder=password type=text> <br/>
    <input type=submit value=Add!> <input type=reset value=Clean>
</form>
<p > </p>
<% if (session.getAttribute("add") == null ){
       session.setAttribute("add", "There will result your work");} %>
<h3 style="color: blue"> <%= session.getAttribute("add")%></h3>
<% session.setAttribute("add",null); %>
<div id="footer">
    <jsp:include page="my.jsp"/>
</div>
</body>
</html>
