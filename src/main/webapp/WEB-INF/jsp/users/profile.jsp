<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<%@ include file="../aside.jsp" %>

<div class="main">
    <div class="main_content_30">

        <%--block - response message about the result of the action--%>
        <c:if test="${errorGetUser!=null}">
            <div style="color: red">
                    ${errorGetUser}
            </div>
            <br>
        </c:if>
        <c:if test="${errorEditUser!=null}">
            <div style="color: red">
                    ${errorEditUser}
            </div>
            <br>
        </c:if>
        <c:if test="${nameChanged}">
            имя изменено
            <br><br>
        </c:if>

        <%--block - user list--%>
        Профиль:
        <table class="table_list">
            <tr>
                <td class="cell_list" style="font-weight: bold">ID</td>
                <td class="cell_list">${user.id}&nbsp&nbsp&nbsp</td>
            </tr>
            <tr>
                <td class="cell_list" style="font-weight: bold">login</td>
                <td class="cell_list">${user.login}&nbsp&nbsp&nbsp</td>
            </tr>
            <tr>
                <td class="cell_list" style="font-weight: bold">name</td>
                <td class="cell_list">${user.name}&nbsp&nbsp&nbsp</td>
            </tr>
            <tr>
                <td class="cell_list" style="font-weight: bold">role</td>
                <td class="cell_list">${user.role.role_description}&nbsp&nbsp&nbsp</td>
            </tr>
        </table>

        <%--block - form: change name and password--%>
        <br>
        изменить имя:
        <form action="${pageContext.request.contextPath}/profileChangeName" method="post">
            <input type="text" name="newName" maxlength="150" style="width: 200px" required>
            <input type="submit" value="change" style="min-width: 80px">
        </form>
        <br>
        изменить пароль (потребуется заново аутентифицироваться):
        <form action="${pageContext.request.contextPath}/profileChangePass" method="post">
            <input type="password" name="newPass" maxlength="50" style="width: 200px" required>
            <input type="submit" value="change" style="min-width: 80px">
        </form>

    </div>
</div>
<%@ include file="../footer.jsp" %>
