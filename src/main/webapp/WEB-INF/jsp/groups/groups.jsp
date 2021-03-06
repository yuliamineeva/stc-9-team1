<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../header.jsp" %>
<%@ include file="../aside.jsp" %>
<div class="main">
    <div class="main_content">
        <table border="1" cellspacing="0" cellpadding="3" align="center">
            <tr>
                <th>ID группы</th>
                <th>Название группы</th>
                <th>Редактировать группу</th>
                <th>Удалить группу</th>
            </tr>
                <c:if test="${arrayGroup != null}">
                    <c:forEach var="group" items="${arrayGroup}" >
                        <c:if test="${group != null}">
                            <tr>
                                <td>${group.group_id}</td>
                                <td><a href="${pageContext.request.contextPath}/group_list?groupId=${group.group_id}">${group.name}</a></td>
                                <td><a href="${pageContext.request.contextPath}/groups/manager?act=edit&groupId=${group.group_id}">Изменить</a></td>
                                <td><a href="${pageContext.request.contextPath}/groups/manager?act=delete&groupId=${group.group_id}">Удалить</a></td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:if>
                <c:if test="${arrayGroup == null}">
                    <tr>
                        <td>null</td><td>null</td>
                    </tr>
                </c:if>
        </table>
        <p align="center">
            <a href="${pageContext.request.contextPath}/groups/manager?act=add">Добавить новую группу</a>
        </p>
    </div>
</div>
<%@ include file="../footer.jsp" %>
