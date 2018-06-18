<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<%@ include file="../aside.jsp" %>

<div class="main">
    <div class="main_content_30">

        <c:choose>
            <c:when test="${himself}">
                Вы не можете удалить себя<br>
                <a href="${pageContext.request.contextPath}/admin/userList">назад</a>
            </c:when>
            <c:otherwise>
                Удалить пользователя?
                <form action="${pageContext.request.contextPath}/admin/userList/deleteUser" method="post">
                    <input type="hidden" name="userId" value=${user.id}>
                    <input type="hidden" name="userLogin" value=${user.login}>
                    <table class="table_list">
                        <tr>
                            <th class="cell_list">ID &nbsp&nbsp</th>
                            <th class="cell_list">login &nbsp&nbsp</th>
                            <th class="cell_list">name</th>
                            <th class="cell_list">role</th>
                        </tr>
                        <tr>
                            <td class="cell_list">${user.id}&nbsp&nbsp&nbsp</td>
                            <td class="cell_list">${user.login}&nbsp&nbsp&nbsp</td>
                            <td class="cell_list">${user.name}&nbsp&nbsp&nbsp</td>
                            <td class="cell_list">${user.userType.type_name}&nbsp&nbsp&nbsp</td>
                        </tr>
                    </table>
                    <input type="submit" value="delete" style="min-width: 80px">
                </form>

                <br>
                <a href="${pageContext.request.contextPath}/admin/userList">назад к списку пользователей</a>
            </c:otherwise>
        </c:choose>

    </div>
</div>
<%@ include file="../footer.jsp" %>