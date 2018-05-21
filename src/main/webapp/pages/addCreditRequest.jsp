<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Данные о кредите</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script src="../js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div align="center">
    <h2>Данные о кредите</h2>
    <form name="requestForm" action="addCreditRequest" method="post">
        <table>
            <tr>
                <td><label for="amount">Сумма</label></td>
                <td><input id="amount" type="number" name="amount" min="0"/></td>
            </tr>
            <tr>
                <td><label for="period">Срок</label></td>
                <td>
                    <select id="period" name="period">
                        <c:forEach items="${periods}" var="period">
                            <option value="${period.period}">${period.period}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="rate">Ставка</label></td>
                <td>
                    <select id="rate" name="rate">
                        <option value="0.09">9%</option>
                        <option value="0.01">10%</option>
                        <option value="0.11">11%</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <hr/>
                </td>
            </tr>
            <tr>
                <td><label for="monthlyPayment">Еж. платеж</label></td>
                <td><input id="monthlyPayment" type="number" step="0.01" name="monthlyPayment"/></td>
            </tr>
            <tr>
                <td><label for="amountInUSD">Сумма в $</label></td>
                <td><input id="amountInUSD" type="number" step="0.01" name="amountInUSD"/></td>
            </tr>
            <tr>
                <td><label for="totalPayout">Общая сумма выплат</label></td>
                <td><input id="totalPayout" type="number" step="0.01" name="totalPayout"/></td>
            </tr>
            <tr>
                <td><label for="overpayment">Переплата</label></td>
                <td><input id="overpayment" type="number" step="0.01" name="overpayment"/></td>
            </tr>
        </table>
        <input type="submit" value="Подтвердить" class="button button-blue" style="margin: 10px 0"/>
    </form>
</div>

<script>
    $("#amount").focusout(function () {
        makeCalculation();
    });
    $("#period").focusout(function () {
        makeCalculation();
    });
    $("#rate").focusout(function () {
        makeCalculation();
    });

    function makeCalculation() {
        var amount = $("#amount").val();
        if (amount) {
            var period = $("#period").val();
            var rate = $("#rate").val();

            var monthlyPayment
                = amount * ((rate / 12) * (Math.pow(1 + (rate / 12), period))) / (Math.pow(1 + (rate / 12), period) - 1);

            // var monthlyPayment = amount * ((rate / 12) / (1 - Math.pow((1 + (rate / 12)), -period)));
            var totalPayout = monthlyPayment * period;
            var overpayment = totalPayout - amount;
            $("#monthlyPayment").val(monthlyPayment.toFixed(2));
            $("#totalPayout").val(totalPayout.toFixed(2));
            $("#overpayment").val(overpayment.toFixed(2));

            $.get("getRatesXML", function (responseXml) {
                $(responseXml).find('item').each(function () {
                    var sTitle = $(this).find('title').text();
                    if (sTitle === 'USD') {
                        var sDescription = $(this).find('description').text();
                        $("#amountInUSD").val((amount / parseFloat(sDescription)).toFixed(2));
                        return false;
                    }
                });
            });
        } else {
            $("#monthlyPayment").val(null);
            $("#amountInUSD").val(null);
            $("#totalPayout").val(null);
            $("#overpayment").val(null);
        }
    }
</script>
</body>
</html>
