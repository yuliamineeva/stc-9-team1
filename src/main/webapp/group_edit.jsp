<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>
<%@ include file="aside.jsp" %>
<div class="main">
    <div class="main_login">
        <%=("addErr".equals(request.getParameter("result"))) ?
                "<div style=\"color: Red;\">" +
                        "<b>Ошибка при добавлении группы. Проверьте корреткность введенных данных</b><br>" +
                        "<b>Возможно, такая группа уже существует!</b>" +
                        "</div>" +
                        "<br><br>" : ""
        %>
        <%=("delErr".equals(request.getParameter("result"))) ?
                "<div style=\"color: Red;\">" +
                        "<b>Ошибка при удалении группы.</b>" +
                        "</div>" +
                        "<br><br>" : ""
        %>
        <%
            request.setAttribute("attr_act", request.getParameter("act"));
            request.setAttribute("attr_groupId", request.getParameter("group_id"));
        %>
        <c:if test="${attr_act != null && attr_act.equals('delete')}">
            <p align="center">Вы действительно хотите удалить группу с ID=${attr_groupId}?
            <form name="group_delete" action="${pageContext.request.contextPath}/groups/edit" method="post">
                <input type="submit" name="submit" value="Удалить">
                <input type="hidden" name="groupId" value=${attr_groupId}>
            </form>
            </p>
        </c:if>
        <c:if test="${attr_act != null && attr_act.equals('add')}">
            <p align="center">Добавить группу:
            <form name="group_add" action="${pageContext.request.contextPath}/groups/edit" method="post">
                <table border="2" align="center" cellspacing="5" cellpadding="13" rules="none">
                    <tr>
                        <td align="left">Введите название группы:</td>
                        <td align="left">
                            <input type="text" name="groupName" size="50">
                        </td>
                    </tr>
                    <tr>
                        <td align="left"><input type="reset" value="Сбросить"></td>
                        <td align="right"><input type="submit" name="submit" value="Добавить"></td>
                    </tr>
                </table>
            </form>
            </p>
        </c:if>
    </div>
</div>
<%@ include file="footer.jsp" %>