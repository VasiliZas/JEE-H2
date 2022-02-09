<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        }

        TABLE {

            border-collapse: collapse; /* Убираем двойные границы между ячейками */
            background: beige; /* Цвет фона таблицы */
            border: 4px solid #000; /* Рамка вокруг таблицы */
        }

        TH {
            size: A3;
            color: black;
            padding: 5px; /* Поля вокруг текста */
            border: 2px solid green; /* Рамка вокруг ячеек */
        }

        TD {
            size: A3;
            color: blue;
            padding: 5px; /* Поля вокруг текста */
            border: 2px solid green; /* Рамка вокруг ячеек */
        }
    </style>
</head>
<body align=center>
<jsp:include page="href.jsp"/>
<h1 align="center">Work with students </h1>
<h2 align="center"> Please enter id, student name, theme and grade to add new grade. </h2>
<form action="/myweb/teachers/addmarks" method=post>
    <input required name=id placeholder=id type=text> <br/>
    <input required name=theme placeholder=theme type=text> <br/>
    <input required name=grade placeholder=grade type=text> <br/>
    <input type=submit value=Add!> <input type=reset value=Clean>
</form>
<p></p>
<p></p>
<h2 align="center"> Please enter id, student name, theme and grade to remove. </h2>
<form action="/myweb/teachers/removemarks" method=post>
    <input required name=id placeholder=id type=text> <br/>
    <input required name=theme placeholder=theme type=text> <br/>
    <input type=submit value=Remove!> <input type=reset value=Clean>
</form>
<p></p>
<c:forEach var="mark" items="${grade}">
    <h3 style="color: blue"><c:out value="${mark}"/></h3></c:forEach>
</h3>
<h1 center></h1>
<h2 center> Your group. </h2>
<h1 center></h1>

<table align="center">

    <th>Name group</th>
    <th>Themes</th>

    <c:forEach var="value" items="${groups}">
        <tr>
            <td><c:out value="${value.group.name}"/></td>
            <td><c:out value="${value.group.them}"/></td>

        </tr>
    </c:forEach>
</table>
<h1 center></h1>
<h2 center> Your students. </h2>
<h1 center></h1>

<table align="center">

    <th>Name</th>
    <th>Age</th>
    <th>Student's id</th>
    <th>Groups</th>
    <c:forEach var="value" items="${yourStudent}">
        <tr>
            <td><c:out value="${value.name}"/></td>
            <td><c:out value="${value.age}"/></td>
            <td><c:out value="${value.id}"/></td>
            <td><c:out value="${value.groups}"/></td>
        </tr>
    </c:forEach>

</table>
<div id="footer">
    <jsp:include page="my.jsp"/>
</div>
</body>
</html>
