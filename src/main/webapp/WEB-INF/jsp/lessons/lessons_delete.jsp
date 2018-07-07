<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="../header.jsp" %>
<%@ include file="../aside.jsp" %>
<div class="main">
    <div class="main_content">
        <H2>Удаление занятия</H2>
        <br>
        <sec:authorize access="hasRole('ROLE_USER')">
            <H2>Доступ студентов к удалению занятий запрещен!</H2>
            <br>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
            <br>
            <c:if test="${deleteLesson != null}">
                <p align="center">Вы действительно хотите удалить лекцию с ID = ${deleteLesson.getLsn_id()}? </p>
                <div class="form_lesson_delete">
                    <form name="lesson_delete" action="${pageContext.request.contextPath}/lessons_delete" method="post">
                        <input type="hidden" name="lessonId" value=${deleteLesson.getLsn_id()}>
                        <input type="submit" value="Удалить">
                    </form>
                    <br>
                    <input type="button" onclick="history.back();" value="Отмена"/>
                </div>
            </c:if>
            <br>

            <c:if test="${message != null && message.equals('successfully')}">
                <p align="center">Лекция с ID = ${lessonId} успешно удалена </p>
            </c:if>
            <c:if test="${message != null && message.equals('errDeleteLesson')}">
                <p align="center"> Ошибка при удалении лекции с ID = ${lessonId} </p>
            </c:if>
        </sec:authorize>
        <br>
        <p align="center"><a href="${pageContext.request.contextPath}/lessons">Вернуться к списку лекций</a></p>

    </div>
</div>
<%@ include file="../footer.jsp" %>
