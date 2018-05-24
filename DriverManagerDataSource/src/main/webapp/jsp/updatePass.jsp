<%--
  Created by IntelliJ IDEA.
  User: 777
  Date: 23.04.2018
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменение пароля</title>
    <link href="./css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
Изменение пароля
    </div>
<form method="post" action="/app/updatePass">
    <label for="name">Name
        <input class="input-field" type="text" id="name" name="name">
    </label>
    <label for="password">New password
        <input class="input-field" type="password" id="password" name="password">
    </label>
    <input type="submit" value="Изменить пароль">
</form>
</div>
</body>
</html>
