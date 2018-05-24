<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.ivmiit.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Регистрация</title>
    <link href="./css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Пожалуйста зарегестрируйтесь! Это займет у Вас всего пару секунд!
    </div>
    <form method="post" action="/app/signUp">
        <label for="name">Name
            <input class="input-field" type="text" id="name" name="name">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <input type="submit" value="Зарегестрироваться">
    </form>

    <form method="get" action="/app/login">
        <input type="submit" value="Уже зарегестрирован">
    </form>

</div>


<div class="form-style-2">
    <div class="form-style-2-heading">
        Уже зарегестрированы!
    </div>

    <table>
        <tr>
            <th>User name</th>
            <th>Password</th>
        </tr>
        <c:forEach items="${usersFromServer}" var="user">
            <tr>
                <td>${user.name}</td>
                <td></td><td></td>
                <td>${user.password}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>