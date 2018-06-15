<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<%@ include file="../aside.jsp" %>

<div class="main">
    <div class="main_content_30">

        <div style="color: red">
            <c:out value="${err}" default=""/>
        </div>

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
                    <td class="cell_list">${user.userType.type_name}&nbsp&nbsp&nbsp</td>
                    <td class="cell_list">
                        delete&nbsp&nbsp&nbsp
                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>
<%@ include file="../footer.jsp" %>
