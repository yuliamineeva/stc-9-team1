<%@ page import="ru.innopolis.stc9.t1.service.GroupService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>
<%@ include file="aside.jsp" %>
<div class="main">
    <div class="main_login">
        <c:if test="${result != null}">
            <div style="color: Red;">
            <c:if test="${result.equals('addErr')}">
                <b>Ошибка при добавлении группы. Проверьте корреткность введенных данных</b><br>
                <b>Возможно, такая группа уже существует!</b>
            </c:if>
            <c:if test="${result.equals('editErr')}">
                <b>Ошибка при изменении группы. Проверьте корреткность введенных данных</b><br>
                <b>Возможно, группа с таким именем уже существует!</b>
            </c:if>
            <c:if test="${result.equals('delErr')}">
                <b>Ошибка при удалении группы.</b>
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
        %>

        <c:if test="${act != null && act.equals('delete')}">
            <p align="center">Вы действительно хотите удалить группу с ID=${groupId}?
            <form name="group_delete" action="${pageContext.request.contextPath}/groups/delete" method="post">
                <input type="submit" value="Удалить">
                <input type="hidden" name="groupId" value=${groupId}>
            </form>
            </p>
        </c:if>
        <c:if test="${act != null && act.equals('add')}">
            <p align="center">Добавить группу:
            <form name="group_add" action="${pageContext.request.contextPath}/groups/add" method="post">
                <table border="2" align="center" cellspacing="5" cellpadding="13" rules="none">
                    <tr>
                        <td align="left">Введите название группы:</td>
                        <td align="left">
                            <input type="text" name="groupName" size="50">
                        </td>
                    </tr>
                    <tr>
                        <td align="left"><input type="reset" value="Сбросить"></td>
                        <td align="right"><input type="submit" value="Добавить"></td>
                    </tr>
                </table>
            </form>
            </p>
        </c:if>
        <c:if test="${act != null && act.equals('edit')}">
            <p align="center">Редактировать группу:
            <form name="group_edit" action="${pageContext.request.contextPath}/groups/edit" method="post">
                <table border="2" align="center" cellspacing="5" cellpadding="13" rules="none">
                    <tr>
                        <td align="left">Название группы:</td>
                        <td align="left">
                            <%
                                GroupService groupService = new GroupService();
                                int id = Integer.valueOf((String)request.getAttribute("groupId"));
                                String strName = groupService.getGrouptById(id).getName();
                                request.setAttribute("groupName", strName);
                            %>
                            <input type="text" name="groupName" size="50" value=${groupName}>
                            <input type="hidden" name="groupId" value=${groupId}>
                        </td>
                    </tr>
                    <tr>
                        <td align="left"><input type="reset" value="Сбросить"></td>
                        <td align="right"><input type="submit" value="Редактировать"></td>
                    </tr>
                </table>
            </form>
            </p>
        </c:if>
    </div>
</div>
<%@ include file="footer.jsp" %>