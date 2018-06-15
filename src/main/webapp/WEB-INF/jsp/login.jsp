<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<%@ include file="aside.jsp" %>

<div class="main">
    <div class="main_content">
        <div class="login">
            <c:if test="${login_error != null}">
                <div style="color: Red;">
                    <b>${login_error}</b><br>
                    <div><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></div>
                </div>
                <br>
            </c:if>
            <form action='/j_spring_security_check' method='POST'>
                <h2>Вход в систему</h2>
                <input type='text'  style="width: 204px; height: 32px; font-size: 16px;"
                    name='j_username' placeholder="Введите логин" required autofocus><br><br>
                <input type='password' style="width: 204px; height: 32px; font-size: 16px;"
                       name='j_password' placeholder="Введите пароль" required><br><br>
                <button type="submit" value="OK">Вход</button>
            </form>
            <br>
            <a href="${pageContext.request.contextPath}/registration">Регистрация</a>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
