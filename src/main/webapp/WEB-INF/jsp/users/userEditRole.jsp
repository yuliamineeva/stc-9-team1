<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<%@ include file="../aside.jsp" %>

<div class="main">
    <div class="main_content_30">

        <c:if test="${errorGetUser!=null}">
            <div style="color: red">
                    ${errorGetUser}
            </div>
            <br>
        </c:if>

        <c:choose>
            <c:when test="${himself}">
                Вы не можете изменить права самому себе<br>
                <a href="${pageContext.request.contextPath}/admin/userList">назад</a>
            </c:when>
            <c:otherwise>
                Изменение прав:
                <form action="${pageContext.request.contextPath}/admin/userList/editRole" method="post">
                    <input type="hidden" name="userId" value=${user.id}>
                    <input type="hidden" name="userLogin" value=${user.login}>
                    <table class="table_list">
                        <tr>
                            <th class="cell_list">ID &nbsp&nbsp</th>
                            <th class="cell_list">login &nbsp&nbsp</th>
                            <th class="cell_list">name</th>
                            <th class="cell_list">role</th>
                            <th class="cell_list">new role &nbsp&nbsp</th>
                        </tr>
                        <tr>
                            <td class="cell_list">${user.id}&nbsp&nbsp&nbsp</td>
                            <td class="cell_list">${user.login}&nbsp&nbsp&nbsp</td>
                            <td class="cell_list">${user.name}&nbsp&nbsp&nbsp</td>
                            <c:choose>
                                <c:when test="${user.role.role_int==1}">
                                    <td class="cell_list">преподаватель &nbsp&nbsp&nbsp</td>
                                    <td class="cell_list">
                                        <select size="2" name="newRoleId" required>
                                            <option value="2">студент</option>
                                            <option value="3">админ</option>
                                        </select>
                                    </td>
                                </c:when>
                                <c:when test="${user.role.role_int==2}">
                                    <td class="cell_list">студент &nbsp&nbsp&nbsp</td>
                                    <td class="cell_list">
                                        <select size="2" name="newRoleId" required>
                                            <option value="1">преподаватель</option>
                                            <option value="3">админ</option>
                                        </select>
                                    </td>
                                </c:when>
                                <c:when test="${user.role.role_int==3}">
                                    <td class="cell_list">админ &nbsp&nbsp&nbsp</td>
                                    <td class="cell_list">
                                        <select size="2" name="newRoleId" required>
                                            <option value="1">преподаватель</option>
                                            <option value="2">студент</option>
                                        </select>
                                    </td>
                                </c:when>
                            </c:choose>
                        </tr>
                    </table>
                    <input type="submit" value="change" style="min-width: 80px">
                </form>

                <br>
                <a href="${pageContext.request.contextPath}/admin/userList">назад к списку пользователей</a>
            </c:otherwise>
        </c:choose>

    </div>
</div>
<%@ include file="../footer.jsp" %>