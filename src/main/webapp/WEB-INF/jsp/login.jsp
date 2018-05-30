<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<%@ include file="aside.jsp" %>

<div class="main">
    <div class="main_content">
        <div class="login">
            <%=request.getAttribute("message") + "<br><br>"%>
            <%=("authErr".equals(request.getParameter("errorMsg"))) ? "Неверное имя пользователя или пароль" : ""%><BR>
            <%=("noAuth".equals(request.getParameter("errorMsg"))) ? "Необходимо пройти авторизацию" : ""%><BR>

            <!--
            <form action="${pageContext.request.contextPath}/login" method="post">
                <label>Введите логин </label>
                <input type="text" name="userName"
                       style="width: 204px; height: 36px; font-size: 16px;"><BR>
                <label>Введите пароль </label>
                <input type="password" maxlength="25" size="40" name="userPassword"
                       style="width: 204px; height: 36px; font-size: 16px;"><BR>
                <input type="submit" value="OK"
                       style="width: 46px; height: 26px; margin-top: 20px;">
            </form>
            -->

            <form action='/j_spring_security_check' method='POST'>
                <h2>Вход в систему</h2>
                <input type='text' name='j_username' placeholder="Введите логин" required autofocus><br>
                <input type='password' name='j_password' placeholder="Введите пароль" required>
                <button type="submit" value="OK">Вход</button>
            </form>

        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
