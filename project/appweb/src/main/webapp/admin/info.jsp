<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset=UTF-8>
    <title>Teacher info</title>
    <style>
        BODY {
            background-image: url(/img/fon_pero_cvet.jpg); /* Путь к фоновому рисунку */
        }

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
<body align=center background="../img/fon_pero_cvet.jpg">
<jsp:include page="href.html"/>
<p center>
    <c></c>
</p>
<br>
<br>

<table align="center">

    <th>Id</th>
    <th>Name</th>
    <th>Login</th>
    <th>Password</th>
    <th>Salary</th>

    <c:forEach var="teacher" items="${teacherInfo}">
            <tr>
                <td><c:out value="${teacher.id}"/></td>
                <td><c:out value="${teacher.name}"/></td>
                <td><c:out value="${teacher.login}"/></td>
                <td><c:out value="${teacher.password}"/></td>
                <td><c:out value="${teacher.salary}"/></td>
            </tr>
        </c:forEach>

</table>
<br/>
<p center>
    <c></c>
</p>
<div id="footer">
    <jsp:include page="my.jsp"/>
</div>
</body>
</html>
