<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="sidebar">

    <c:if test="${username == null}">
        <div style="text-align: center; font-weight: bold">
            Welcome
        </div>
    </c:if>

    <sec:authorize access="isAuthenticated()">
        <p><b>МЕНЮ:</b></p>
        <ul>
            <li><a href="${pageContext.request.contextPath}/user/profile">Профиль (в разработке)</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/groups">Группы</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/ToDo3">Меню 3</a></li>
            <br>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="${pageContext.request.contextPath}/admin/userList">Список пользователей</a></li>
                <br>
            </sec:authorize>
        </ul>
    </sec:authorize>


</div>
