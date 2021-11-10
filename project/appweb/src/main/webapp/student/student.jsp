<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset="UTF-8">
    <title>student</title>
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
<h1 center> Your work page </h1>
<h1 center> You can see your grades here. </h1>
<h1 center>   </h1>
<h2 center> Your name. </h2>
<h1 center>   </h1>
<h2 center style="color: blue"> <%= session.getAttribute("name") %> </h2>
<h1 center>   </h1>
<h2 center> Your grades. </h2>
<h1 center>   </h1>
<h2 center style="color: blue"> <%= session.getAttribute("marks") %> </h2>
<div id="footer">
    <jsp:include page="my.jsp"/>
</div>
</body>
</html>
