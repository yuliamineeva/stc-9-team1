<%@ page import="ru.innopolis.stc9.t1.service.GroupService" %>
<%@ page import="ru.innopolis.stc9.t1.db.dao.GroupDAOImpl" %>
<%@ page import="ru.innopolis.stc9.t1.pojo.Group" %>
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
            String paramAct = request.getParameter("act");
            if(paramAct != null && !paramAct.equals("")) {
                request.setAttribute("act", paramAct);
            }
            String paramGroupId = request.getParameter("groupId");
            if(paramGroupId != null && !paramGroupId.equals("")) {
                request.setAttribute("groupId", paramGroupId);
            }
            GroupService groupService = new GroupService(new GroupDAOImpl());
            Group group = groupService.getGroupById(Integer.valueOf(paramGroupId));
            if(group != null) {
                request.setAttribute("groupName", group.getName());
            }
            String paramUserId = request.getParameter("userId");
            if(paramUserId != null && !paramUserId.equals("")) {
                request.setAttribute("userId", paramUserId);
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

    </div>
</div>
<%@ include file="footer.jsp" %>