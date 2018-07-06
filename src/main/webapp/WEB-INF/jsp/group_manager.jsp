<%@ page import="ru.innopolis.stc9.t1.service.GroupService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>
<%@ include file="aside.jsp" %>
<div class="main">
    <div class="main_login">
        <c:if test="${act != null && act.equals('delete')}">
            <p align="center">Вы действительно хотите удалить группу с ID=${group.group_id}?
            <form name="group_delete" action="${pageContext.request.contextPath}/groups/delete" method="post">
                <input type="submit" value="Удалить">
                <input type="hidden" name="groupId" value=${group.group_id}>
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
                            <input type="text" name="groupName" size="50" value=${group.name}>
                            <input type="hidden" name="groupId" value=${group.group_id}>
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