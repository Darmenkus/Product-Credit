<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Поиск обращений клиента</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script src="../js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div align="center">
    <form name="searchForm" method="post">
        <table>
            <tr>
                <td><input type="text" name="iin" minlength="12" maxlength="12" placeholder="ИИН"/></td>
                <td><input type="submit" value="Поиск" class="button button-blue"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
