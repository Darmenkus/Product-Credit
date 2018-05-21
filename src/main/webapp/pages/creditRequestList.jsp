<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Заявки</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script src="../js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div align="center">
    <p>
        <a href="search" class="button button-blue" style="margin: 10px 0">Выбрать другого клиента</a>
    </p>
    <table id="requests">
        <thead>
        <tr>
            <th>Дата обращения</th>
            <th>Номер заявки</th>
            <th>Сумма</th>
            <th>Срок</th>
            <th>Статус</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${creditRequests}" var="creditRequest">
            <tr>
                <td><fmt:formatDate pattern="dd.MM.yyyy" value="${creditRequest.requestDate}"/></td>
                <td><c:out value="${creditRequest.requestNumber}"/></td>
                <td><fmt:formatNumber type="number" groupingUsed="false" maxFractionDigits="2"
                                      value="${creditRequest.amount}"/></td>
                <td><c:out value="${creditRequest.period.period}"/></td>
                <td><c:out value="${creditRequest.status}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p><a href="addCreditRequest" class="button button-blue" style="margin: 10px 0">Новая заявка</a></p>
</div>
</body>
</html>
