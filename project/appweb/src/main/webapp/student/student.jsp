<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset="UTF-8">
    <title>student</title>
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
<body align=center background="img/fon_pero_cvet.jpg">
<jsp:include page="href.html"/>
<h1 center> Your work page </h1>
<h1 center> You can see your grades here. </h1>
<h1 center></h1>
<h2 center> Your name. </h2>
<h1 center></h1>
<h2 center style="color: blue"><%= session.getAttribute("name") %>
</h2>
<h1 center></h1>
<h2 center> Your grades. </h2>
<h1 center></h1>

<table align="center">

    <th>Theme</th>
    <th>Marks</th>

    <c:forEach var="value" items="${marks}">
        <tr>
            <td><c:out value="${value.theme}"/></td>
            <td><c:out value="${value.grade}"/></td>
        </tr>
    </c:forEach>
</table>

<div id="footer">
    <jsp:include page="my.jsp"/>
</div>
</body>
</html>
