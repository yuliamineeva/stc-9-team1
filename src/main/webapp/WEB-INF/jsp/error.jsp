<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>
<%@ include file="aside.jsp" %>
<div class="main">
    <div class="main_login">
        <div style="color: Red;">
            <c:if test="${result.equals('addErr')}">
                <b>Ошибка при операции добавления</b><br>
            </c:if>
            <c:if test="${result.equals('delErr')}">
                <b>Ошибка при операции удаления</b>
            </c:if>
            <c:if test="${result.equals('editErr')}">
                <b>Ошибка при операции изменения</b>
            </c:if>
        </div>
        <br><br>
    </div>
</div>
<%@ include file="footer.jsp" %>
