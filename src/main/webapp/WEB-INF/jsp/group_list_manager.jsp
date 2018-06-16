<%@ page import="ru.innopolis.stc9.t1.service.GroupService" %>
<%@ page import="ru.innopolis.stc9.t1.db.dao.GroupDAOImpl" %>
<%@ page import="ru.innopolis.stc9.t1.pojo.Group" %>
<%@ page import="ru.innopolis.stc9.t1.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.innopolis.stc9.t1.service.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>
<%@ include file="aside.jsp" %>
<div class="main">
    <div class="main_login">
        <c:if test="${result != null}">
            <div style="color: Red;">
                <c:if test="${result.equals('addErr')}">
                    <b>Ошибка при добавлении пользователя в группу. Проверьте корреткность введенных данных</b><br>
                    <b>Возможно, такой пользователь уже в группе!</b>
                </c:if>
                <c:if test="${result.equals('delErr')}">
                    <b>Ошибка при удалении пользователя из группы.</b>
                </c:if>
            </div>
            <br><br>
        </c:if>

        <%
            String paramGroupId = request.getParameter("groupId");
            GroupService groupService = new GroupService(new GroupDAOImpl());
            Group group = groupService.getGroupById(Integer.valueOf(paramGroupId));
            if(group != null) {
                request.setAttribute("groupName", group.getName());
            }
        %>
        <c:if test="${act != null && act.equals('delete')}">
            <p align="center">Вы действительно хотите удалить пользователя (ID=${groupId}) из группы ${groupName}?
            <form name="group_list_delete" action="${pageContext.request.contextPath}/group_list/delete" method="post">
                <input type="submit" value="Удалить">
                <input type="hidden" name="groupId" value=${groupId}>
                <input type="hidden" name="userId" value=${userId}>
            </form>
            </p>
        </c:if>
        <c:if test="${act != null && act.equals('add')}">
            <%
                UserService userService = new UserService();
                List<User> usersNotGroup = userService.getAllUsersNotInGroup(Integer.valueOf(paramGroupId));
                request.setAttribute("usersNotGroup", usersNotGroup);
            %>
            <p align="center">Добавить в группу ${groupName} пользователя:
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
                                    <td><a href="${pageContext.request.contextPath}/group_list/add?groupId=${groupId}&userId=${user.id}">Добавить</a></td>
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