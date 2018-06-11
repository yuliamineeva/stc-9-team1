<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.innopolis.stc9.t1.pojo.User" %>
<%@ page import="ru.innopolis.stc9.t1.db.dao.GroupListDAOImpl" %>
<%@ page import="ru.innopolis.stc9.t1.service.GroupListService" %>

<%@ include file="header.jsp" %>
<%@ include file="aside.jsp" %>
<div class="main">
    <div class="main_content">
        <%
            GroupListService groupListService = new GroupListService(new GroupListDAOImpl());
        %>
        <table border="1" cellspacing="0" cellpadding="3" align="center">
            <tr>
                <th>ID пользователя</th>
                <th>ФИО Пользователя</th>
                <th>Удалить из группы</th>
            </tr>

            <%
                String paramGroupId = request.getParameter("groupId");
                int groupId = Integer.valueOf(paramGroupId);
                ArrayList<User> arrayGroupList = groupListService.getGroupList(groupId);
                request.setAttribute("arrayGroupList", arrayGroupList);
            %>
            <c:if test="${arrayGroupList != null}">
                <c:forEach var="user" items="${arrayGroupList}" >
                    <c:if test="${user != null}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td><a href="${pageContext.request.contextPath}/todo">Удалить из группы</a></td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:if>
            <c:if test="${arrayGroupList == null}">
                <tr>
                    <td>null</td><td>null</td>
                </tr>
            </c:if>
        </table>
        <p align="center">
            <a href="${pageContext.request.contextPath}/todo">Добавить пользователя в группу</a>
        </p>
    </div>
</div>
<%@ include file="footer.jsp" %>
