<%--
  Created by IntelliJ IDEA.
  User: 777
  Date: 16.04.2018
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
    <link href="./css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
<span style="color: ${cookie.color.value}"> Приветствуем Вас в личном кабинете!</span>
    </div>
<form method="post" action="/app/home">
    <label for="color">
        <select name="color" id="color">
            <option value="red">Красный</option>
            <option value="black">Черный</option>
            <option value="white">Белый</option>
            <option value="green">Зеленый</option>
        </select>
    </label>
    <input type="submit" value="Изменить цвет">
</form>

    <form method="get" action="/app/home/updatePass">
        <input type="submit" value="Изменение пароля ">
    </form>

    <form method="get" action="/app/home/logout">
        <input type="submit" value="Выход">
    </form>

    <form method="get" action="/app/home/remove">
        <input type="submit" value="Удаление аккаунта">
    </form>
</div>
</body>
</html>
