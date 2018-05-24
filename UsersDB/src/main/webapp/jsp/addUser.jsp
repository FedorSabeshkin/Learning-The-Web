<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please add user
    </div>
    <form method="post" action="/app/addUser">
        <label for="name">First Name
            <input class="input-field" type="text" id="name" name="name">
        </label>
        <label for="password">Last Name
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <input type="submit" value="Add user">
    </form>
</div>
</body>
</html>
