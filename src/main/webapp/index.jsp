<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.javawebinar.topjava.web.SecurityUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Java Enterprise (Topjava)</title>
</head>
<body>
<h3>Проект <a href="https://github.com/JavaWebinar/topjava" target="_blank">Java Enterprise (Topjava)</a></h3>
<hr>

<c:set var="currentUserId" value="<%= SecurityUtil.getAuthUserId() %>"/>

<form action="users" method="post" style="margin-bottom: 20px;">
    <label for="userId">Select User:</label>
    <select id="userId" name="userId" onchange="this.form.submit()">
        <option value="1" ${currentUserId == '1' ? 'selected' : ''}>User 1</option>
        <option value="2" ${currentUserId == '2' ? 'selected' : ''}>User 2</option>
    </select>
</form>


<ul style="font-size: large">
    <li><a href="users">Users</a></li>
    <li><a href="meals">Meals</a></li>
</ul>
</body>
</html>
