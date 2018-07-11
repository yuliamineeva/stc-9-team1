<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../header.jsp" %>
<%@ include file="../aside.jsp" %>
<div class="main">
    <div class="main_content">
        <table border="1" cellspacing="0" cellpadding="3" align="center">
            <tr>
                <th>ID пользователя</th>
                <th>ФИО Пользователя</th>
                <th>Удалить из группы</th>
            </tr>

            <c:if test="${groupList != null}">
                <c:forEach var="user" items="${groupList}" >
                    <c:if test="${user != null}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td><a href="${pageContext.request.contextPath}/group_list/manager?act=delete&groupId=${group.group_id}&userId=${user.id}">Удалить из группы</a></td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:if>
            <c:if test="${groupList == null}">
                <tr>
                    <td>null</td><td>null</td>
                </tr>
            </c:if>
        </table>
        <p align="center">
            <a href="${pageContext.request.contextPath}/group_list/manager?act=add&groupId=${group.group_id}">Добавить пользователя в группу</a>
        </p>
    </div>
</div>
<%@ include file="../footer.jsp" %>
