<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>
<%@ include file="aside.jsp" %>
<div class="main">
    <div class="main_login">
        <c:if test="${act != null && act.equals('delete')}">
            <p align="center">Вы действительно хотите удалить пользователя (ID=${user.id}) из группы ${group.name}?
            <form name="group_list_delete" action="${pageContext.request.contextPath}/group_list/delete" method="post">
                <input type="submit" value="Удалить">
                <input type="hidden" name="groupId" value=${group.group_id}>
                <input type="hidden" name="userId" value=${user.id}>
            </form>
            </p>
        </c:if>
        <c:if test="${act != null && act.equals('add')}">
            <p align="center">Добавить в группу ${group.name} пользователя:
                <table border="1" cellspacing="0" cellpadding="3" align="center">
                    <tr>
                        <th>ID пользователя</th>
                        <th>ФИО Пользователя</th>
                        <th>Добавить в группу</th>
                    </tr>
                    <c:if test="${usersNotGroup != null}">
                        <c:forEach var="user" items="${usersNotGroup}" >
                            <c:if test="${user != null}">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.name}</td>
                                    <td><a href="${pageContext.request.contextPath}/group_list/add?groupId=${group.group_id}&userId=${user.id}">Добавить</a></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${usersNotGroup == null}">
                        <tr>
                            <td>null</td><td>null</td><td>null</td>
                        </tr>
                    </c:if>
                </table>
            </p>
        </c:if>

    </div>
</div>
<%@ include file="footer.jsp" %>