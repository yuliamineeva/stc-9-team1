<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="../header.jsp" %>
<%@ include file="../aside.jsp" %>
<div class="main">

    <div class="main_content">


        <table border="1" cellspacing="0" cellpadding="3" align="center">
            <tr>
                <th>ID</th>
                <th>ФИО Пользователя</th>
                <th>Наименование Лекции</th>
                <th>Присутствие на Лекции</th>
                <th>Оценка</th>
                <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
                    <th>Редактировать</th>
                    <th>Удалить</th>
                </sec:authorize>
            </tr>

            <c:forEach items="${lessonMark}" var="lessonMark">
            <tr>
                <td align="center">${lessonMark.getLmId()}</td>
                <td align="left">${lessonMark.getUserId()}</td>
                <td align="center">${lessonMark.getLsnId()}</td>
                <td align="center">${lessonMark.getVisit()}</td>
                <td align="left">${lessonMark.getMark()}</td>
                    <td align="center"><a
                            href="${pageContext.request.contextPath}/lessonMarkEdit}">Редактировать</a>
                    </td>
                    <td align="center">
                        <a href="${pageContext.request.contextPath}/lessonMark_delete?lessonMark_id=${lessonMark.getLmId()}">Удалить</a>
                    </td>
            </tr>
            </c:forEach>


            <a href="${pageContext.request.contextPath}/lessonMarkAdd">Добавить посещяемость</a>
    </div>
</div>
<%@ include file="../footer.jsp" %>
