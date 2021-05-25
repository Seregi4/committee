

<%--
  Created by IntelliJ IDEA.
  User: Seregi4
  Date: 25.05.2021
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c"     uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Specialty</title>
</head>
<body>
<div><h1>Create Specialty</h1></div>
<form action="/new_specialty" class="create_specialty" method="post">

    <input type="text" name="name_specialty" placeholder="Name specialty"></br>
    <ul>
        <c:forEach var="user" items="${users}">
            <li><c:out value="${user}" /></li>
        </c:forEach>
    </ul>

    <button type="submit">Confirm</button>
</form>

</body>
</html>
