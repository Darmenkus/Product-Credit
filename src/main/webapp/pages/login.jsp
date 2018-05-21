<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Кабинет пользователя</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script src="../js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div align="center">
    <form name="loginForm" method="post">
        <table>
            <tr>
                <td colspan="2">
                    <h2>Вход для пользователя</h2>
                </td>
            </tr>
            <tr>
                <td><input type="text" name="login" placeholder="Логин" required/></td>
            </tr>
            <tr>
                <td><input type="password" name="password" placeholder="Пароль" required/></td>
                <td><input type="submit" value="Войти" class="button button-blue" style="margin-left: 5px"/></td>
            </tr>
        </table>

        <span style="color:red">
            <%=(request.getAttribute("errorMessage") == null) ? "" : request.getAttribute("errorMessage")%>
        </span>
    </form>
</div>
</body>
</html>
