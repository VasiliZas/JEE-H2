<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style type="text/css">
        #footer {
            position: fixed;
            bottom: 0;
            padding: 2px;
            color: black;
            width: 100%;
        } </style>
</head>
<body align=center>
<jsp:include page="href.jsp"/>
<h1 center style="color: red"> Please try again </h1>
<h3> Something went wrong. Please try again or email us about the problem. </h3>
<div id="footer">
    <jsp:include page="my.jsp"/>
</div>
</body>
</html>
