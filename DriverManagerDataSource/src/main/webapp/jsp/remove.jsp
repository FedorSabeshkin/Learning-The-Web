
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Удаление аккаунта</title>
    <link href="./css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
Пожалуйста, подтвердите Ваш логин и пароль.
    </div>

<form method="post" action="/app/remove">
    <label for="name">Name
        <input class="input-field" type="text" id="name" name="name">
    </label>
    <label for="password">Password
        <input class="input-field" type="password" id="password" name="password">
    </label>
    <input type="submit" value="Удалить аккаунт!">
</form>
</div>
</body>
</html>
