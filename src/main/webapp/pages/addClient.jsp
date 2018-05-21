<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Данные о клинте</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script src="../js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div align="center">
    <h2>Данные о клиенте</h2>
    <span id="iinError" style="color: red"></span>
    <span style="color:red">
        <%=(request.getAttribute("iinErrorMessage") == null) ? "" : request.getAttribute("iinErrorMessage")%>
    </span>
    <form id="addClient" name="clientDataForm" action="addClient" method="post">
        <table>
            <tr>
                <td><label for="iin">ИИН</label></td>
                <td><input id="iin" type="text" name="iin" minlength="12" maxlength="12" required/></td>
            </tr>
            <tr>
                <td><label for="lastName">Фамилия</label></td>
                <td><input id="lastName" type="text" name="lastName" required/></td>
            </tr>
            <tr>
                <td><label for="firstName">Имя</label></td>
                <td><input id="firstName" type="text" name="firstName" required/></td>
            </tr>
            <tr>
                <td><label for="middleName">Отчество</label></td>
                <td><input id="middleName" type="text" name="middleName" required/></td>
            </tr>
            <tr>
                <td><label for="phoneNumber">Мобильный телефон</label></td>
                <td><input id="phoneNumber" type="text" name="phoneNumber" required/></td>
            </tr>
            <tr>
                <td><label for="birthDate">Дата рождения</label></td>
                <td><input id="birthDate" type="date" name="birthDate" required/></td>
            </tr>
            <tr>
                <td><label>Пол</label></td>
                <td>
                    <input type="radio" name="gender" value="MALE" checked> М
                    <input type="radio" name="gender" value="FEMALE"> Ж
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <hr/>
                </td>
            </tr>
            <tr>
                <td><label for="documentNumber">Номер документа</label></td>
                <td><input id="documentNumber" type="text" name="documentNumber" required/></td>
            </tr>
            <tr>
                <td><label for="documentIssuedBy">Кем выдан</label></td>
                <td><input id="documentIssuedBy" type="text" name="documentIssuedBy" required/></td>
            </tr>
            <tr>
                <td><label for="documentDateOfIssue">Дата выдачи</label></td>
                <td><input id="documentDateOfIssue" type="date" name="documentDateOfIssue" required/></td>
            </tr>
            <tr>
                <td><label for="documentValidTo">Действителен до</label></td>
                <td><input id="documentValidTo" type="date" name="documentValidTo" required/></td>
            </tr>
            <tr>
                <td><label for="salary">Заработная плата</label></td>
                <td><input id="salary" type="number" name="salary" min="0" required/></td>
            </tr>
            <tr>
                <td><label for="payments">Коммунальные платежи, аренда</label></td>
                <td><input id="payments" type="number" name="payments" min="0" required/></td>
            </tr>
        </table>
        <input type="submit" value="Подтвердить" class="button button-blue" style="margin: 10px 0"/>
    </form>
</div>
<script>
    $("#addClient").submit(function (event) {
        var iin = $("#iin").val();
        var date = $("#birthDate").val();
        var gender = $("input[name=gender]").val();

        var url = "checkIin?iin=" + iin + "&date=" + date + "&gender=" + gender;

        var msg;
        $.get(url, function (text) {
            msg = text;
        });

        if ($.trim(msg) === "false") {
            $("#iinError").text("Некорректный ИИН").show();
        } else {
            $("#iinError").hide();
            return;
        }

        event.preventDefault();
    });
</script>
</body>
</html>
