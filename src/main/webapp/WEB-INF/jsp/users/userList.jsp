<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<%@ include file="../aside.jsp" %>

<div class="main">
    <div class="main_content_30">

        <%--block - response message about the result of the action--%>
        <c:choose>
            <c:when test="${errorEditUser!=null}">
                <div style="color: red">
                        ${errorEditUser}
                </div>
            </c:when>
            <c:otherwise>
                <c:if test="${editedUserLogin!=null}">
                    У пользователя ${editedUserLogin} были изменены права<br><br>
                </c:if>
                <c:if test="${deletedUserLogin!=null}">
                    Пользователь ${deletedUserLogin} был удален<br><br>
                </c:if>
            </c:otherwise>
        </c:choose>

        <%--block - user list--%>
        <div style="color: red">
            <c:out value="${errorGetUserList}" default=""/>
        </div>

        Все пользователи:
        <table class="table_list">
            <tr>
                <th class="cell_list">ID</th>
                <th class="cell_list">login</th>
                <th class="cell_list">name</th>
                <th class="cell_list">role (change)&nbsp&nbsp&nbsp</th>
                <th class="cell_list">delete&nbsp&nbsp&nbsp</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td class="cell_list">${user.id}&nbsp&nbsp&nbsp</td>
                    <td class="cell_list">${user.login}&nbsp&nbsp&nbsp</td>
                    <td class="cell_list">${user.name}&nbsp&nbsp&nbsp</td>
                    <td class="cell_list">
                        <a href="${pageContext.request.contextPath}/admin/userList/editRole?id=${user.id}">
                                ${user.role.role_description}
                        </a>
                        &nbsp&nbsp&nbsp
                    </td>
                    <td class="cell_list">
                        <a href="${pageContext.request.contextPath}/admin/userList/deleteUser?id=${user.id}">delete</a>
                        &nbsp&nbsp&nbsp
                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>
<%@ include file="../footer.jsp" %>
