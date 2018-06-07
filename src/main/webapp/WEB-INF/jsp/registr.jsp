<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>

<div class="main">
    <div class="registr_content">
        <div class="registr_block">
            <a href="${pageContext.request.contextPath}/login">войти</a>
        </div>
        <form action="${pageContext.request.contextPath}/registr" method="post">
            <div class="registr_table">
                <div style="text-align: center">
                    Регистрация
                </div>
                <div class="table" style="padding: 4px 0">
                    <div class="row">
                        <div class="cell" style="text-align: right">логин</div>
                        <div class="cell">
                            <input type="text" name="userLogin" style="min-width: 200px">
                        </div>
                    </div>
                    <div class="row">
                        <div class="cell" style="text-align: right">имя</div>
                        <div class="cell">
                            <input type="text" name="userName" style="min-width: 200px">
                        </div>
                    </div>
                    <div class="row">
                        <div class="cell" style="text-align: right">пароль</div>
                        <div class="cell">
                            <input type="password" maxlength="25" name="userPassword" style="min-width: 200px">
                        </div>
                    </div>
                </div>
                <div style="text-align: center">
                    <input type="submit" value="OK" style="min-width: 80px">
                </div>
            </div>
        </form>
        <div class="registr_block" style="color: red">
            <c:out value="${RegistrReply}" default=""/>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>

