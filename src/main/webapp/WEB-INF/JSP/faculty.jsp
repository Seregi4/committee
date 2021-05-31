<%--
  Created by IntelliJ IDEA.
  User: Seregi4
  Date: 30.05.2021
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<style>
    <%@include file='/WEB-INF/CSS/faculty.css' %>
</style>
<head>
    <title>Faculty - ${fac.getName()}</title>
</head>
<body>
<div class="center-fac" >
    <div class="name-fac" >${fac.getName()}</div>
    <div class="description-fac" >${fac.getDescription()}</div>
    <div class="block-spec" >

        <c:forEach items="${fac.getList()}" var="spec">
            <div class="spec-el" >
                <div class="block-name-spec" >
                    <div class="name-spec" >${spec.getName()}</div>
                    <form action="/faculty" >
                        <input name="fac-id" value="${fac.getId()}" readonly style="display:none;" >
                        <input name="spec-id" value="${fac.getList().indexOf(spec)}" readonly style="display:none;" >
                        <button type="submit" >Submit</button>
                    </form>
                </div>
                <div class="name-desc" >${spec.getDescription()}</div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>

