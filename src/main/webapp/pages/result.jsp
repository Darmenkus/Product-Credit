<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Результат</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script src="../js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div align="center">
    <h2>
        <%= request.getAttribute("resultMessage") %>
    </h2>
    <p><a href="requestList">К списку заявок</a></p>
</div>
</body>
</html>
