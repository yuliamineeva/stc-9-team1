<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="ru.innopolis.stc9.t1.service.GroupService" %>
<%@ page import="ru.innopolis.stc9.t1.pojo.Group" %>

<%@ include file="header.jsp" %>
<%@ include file="aside.jsp" %>
<div class="main">
    <div class="main_content">
        <%
            GroupService groupService = new GroupService();
        %>
        <table border="1" cellspacing="0" cellpadding="3" align="center">
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>

                <%
                    Group group = groupService.getGrouptById(1);
                    request.setAttribute("group", group);
                %>
                <c:if test="${group != null}">
                    <tr>
                        <td>${group.group_id}</td>
                        <td>${group.name}</td>
                    </tr>
                </c:if>
                <c:if test="${group == null}">
                    <tr>
                        <td>null</td><td>null</td>
                    </tr>
                </c:if>

        </table>
    </div>
</div>
<%@ include file="footer.jsp" %>
