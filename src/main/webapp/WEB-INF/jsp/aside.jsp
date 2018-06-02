<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="sidebar">

    <c:choose>
        <c:when test="${username == null}">
            <div style="text-align: center; font-weight: bold">
                Welcome
            </div>
        </c:when>
        <c:otherwise>
            <p><b>МЕНЮ:</b></p>
        </c:otherwise>
    </c:choose>

    <%--<sec:authorize access="isAuthenticated()">
        <p><b>МЕНЮ:</b></p>
    </sec:authorize>--%>

    <sec:authorize access="hasAnyRole('ROLE_TEACHER', 'ROLE_USER')">
        <ul>
            <li><a href="${pageContext.request.contextPath}/user/profile">Профиль</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/groups">Группы</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/ToDo3">Меню 3</a></li>
            <br>
        </ul>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <ul>
            <li><a href="${pageContext.request.contextPath}/admin/userlist">Список пользователей</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/groups">Группы</a></li>
            <br>
            <li><a href="${pageContext.request.contextPath}/ToDo3">Меню 3</a></li>
            <br>
        </ul>
    </sec:authorize>

</div>
