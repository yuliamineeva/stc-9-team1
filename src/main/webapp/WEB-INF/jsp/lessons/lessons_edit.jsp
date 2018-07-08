<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="../header.jsp" %>
<%@ include file="../aside.jsp" %>
<div class="main">
    <div class="main_content">
        <H2>Редактирование занятия</H2>
        <br>
        <sec:authorize access="hasRole('ROLE_USER')">
            <H2>Доступ студентов к редактированию занятий запрещен!</H2>
            <br>
            <br>
            <p align="center"><a href="${pageContext.request.contextPath}/lessons">Вернуться к списку лекций</a></p>
        </sec:authorize>

        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
            <div class="form_lesson_add">
                <p align="center">Внесите необходимые изменения:
                <form name="lesson_update" action="${pageContext.request.contextPath}/lessons_edit" method="post">
                    <table border="2" align="center" cellspacing="5" cellpadding="15" rules="none">
                        <tr>
                            <td align="left">Тема лекции:</td>
                            <td align="left">
                                <input type="text" name="lessonTopic" value="${lesson.getTopic()}"
                                       style="width: 400px;">
                                <input type="hidden" name="lessonId" value=${lesson.getLsn_id()}>
                            </td>
                        </tr>
                        <tr>
                            <td align="left">Дата лекции:</td>
                            <td align="left">
                                <input id="date" type="date" name="lessonDate" value="${lesson.getDate()}">
                            </td>
                        </tr>
                        <tr>
                            <td align="left">Время лекции:</td>
                            <td align="left">
                                <select class="select-time" id="selectTime" name="lessonTime"
                                        style="width: 135px; margin-right: 20px;">
                                    <c:if test="${lesson != null}">
                                        <option>${lesson.getTime()}</option>
                                    </c:if>
                                    <option>08:00:00</option>
                                    <option>10:00:00</option>
                                    <option>12:00:00</option>
                                    <option>14:00:00</option>
                                    <option>16:00:00</option>
                                    <option>18:00:00</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="left">Выберете группу:</td>
                            <td align="left">
                                <select class="select-groups" id="selectGroup" name="lessonGroup"
                                        style="width: 135px; margin-right: 20px;">
                                    <c:forEach items="${allGroups}" var="group">
                                        <%--<option value="${group.getGroup_id()}">${group.getName()}</option>--%>
                                        <option value="${group.getGroup_id()}" ${group.getGroup_id() == lesson.getGroup().getGroup_id() ? 'selected' : ''}>${group.getName()}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="left">Выберете лектора:</td>
                            <td align="left">
                                <select class="select-tutor" id="selectTutor" name="lessonTutor"
                                        style="width: 135px; margin-right: 20px;">
                                    <c:forEach items="${allTutors}" var="tutor">
                                        <%--<option value="${tutor.getId()}">${tutor.getName()}</option>--%>
                                        <option value="${tutor.getId()}" ${tutor.getId() == lesson.getTutor().getId() ? 'selected' : ''}>${tutor.getName()}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="left"></option><input type="button" onclick="history.back();" value="Отмена">
                            </td>
                            <td align="right"><input type="submit" value="Сохранить"></td>
                        </tr>
                    </table>
                </form>
                </p>
            </div>

            <c:if test="${message != null && message.equals('EmptyField')}">
                <p align="center"> Необходимо заполнить все поля </p>
            </c:if>
            <c:if test="${message != null && message.equals('errEditLesson')}">
                <p align="center"> Ошибка при редактировании лекции </p>
            </c:if>
        </sec:authorize>
    </div>
</div>
<%@ include file="../footer.jsp" %>
