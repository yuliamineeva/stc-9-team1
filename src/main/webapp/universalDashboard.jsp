<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<%@ include file="aside.jsp" %>
<div class="main">
    <main class="dashboard">
        <%if ((Integer) request.getSession().getAttribute("userType") == 3) {%>
        Страница администратора
        <%} else { %>
        Страница пользователя:
        <%} %>
        <BR>
        <div class="user">
            <BR>
            <c:out value="${sessionScope.userInfo}"/>
        </div>
    </main>
</div>

<%@ include file="footer.jsp" %>



