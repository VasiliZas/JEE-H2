
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset=UTF-8>
    <title>Add person and parameter</title>
    <style>
        .text {
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
<jsp:include page="href.jsp"/>
<h1 align="center">Work with teacher </h1>
<h2 align="center"> Please enter the name and the number of months for which you want to receive an average
    salary. </h2>
<form action="/myweb/averagesalary" method=post>
    <input required name=id placeholder=id type=text> <br/>
    <input required name=number placeholder=number type=text> <br/>
    <input type=submit value=Go!> <input type=reset value=Clean>
</form>
<p></p>
<p></p>
<% if (session.getAttribute("avgSalary") == null ){
    session.setAttribute("avgSalary", "There will result your work");} %>
<h3 style="color: blue"> <%= session.getAttribute("avgSalary")%></h3>
<% session.setAttribute("avgSalary",null); %>
<div id="footer">
    <jsp:include page="my.jsp"/>
</div>
</body>
</html>
