<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<%@ include file="../aside.jsp" %>

<div class="main">
    <div class="main_content_30">
        users:<br>
        <c:forEach var="user" items="${users}">
            ${user.toString()}<br>
        </c:forEach>
        roles:<br>
        <c:forEach var="role" items="${roles}">
            ${role.toString()}<br>
        </c:forEach>
    </div>
</div>
<%@ include file="../footer.jsp" %>
