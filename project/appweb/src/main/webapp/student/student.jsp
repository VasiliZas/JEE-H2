<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset="UTF-8">
    <title>student</title>
</head>
<body align=center>
<jsp:include page="href.html"/>
<h1 center> Your work page </h1>
<h1 center> You can see your grades here. </h1>
<h1 center>   </h1>
<h1 center> Your name. </h1>
<h1 center>   </h1>
<h1 center> <%= session.getAttribute("name") %> </h1>
<h1 center>   </h1>
<h1 center> Your grades. </h1>
<h1 center>   </h1>
<h1 center> <%= session.getAttribute("marks") %> </h1>
</body>
</html>
