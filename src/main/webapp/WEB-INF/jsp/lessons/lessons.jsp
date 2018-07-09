<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="../header.jsp" %>
<%@ include file="../aside.jsp" %>
<div class="main">
    <div class="main_content">
        <H2>Расписание занятий</H2>
        <br>
        <div class="selected_form">
            <div class="selected_part">
                Выбрать занятия по группе: &nbsp
            </div>
            <div class="selected_part">
                <form action="${pageContext.request.contextPath}/lessons" name="lessonsByGroup" id="selectsGroup">
                    <select onChange="this.form.submit()" class="select-groups" id="byGroup" name="lessonsByGroup"
                            style="width: 135px; margin-right: 20px;">
                        <option value=all>Все группы</option>
                        <c:forEach items="${allGroups}" var="group">
                            <option value="${group.getGroup_id()}" ${group.getGroup_id() == lessonsByGroup ? 'selected' : ''}>${group.getName()}</option>
                        </c:forEach>
                    </select>
                </form>
                &nbsp &nbsp
            </div>
        </div>

        <div class="selected_form">
            <div class="selected_part">
                Выбрать занятия по дате: &nbsp
            </div>
            <div class="selected_part">
                <form action="${pageContext.request.contextPath}/lessons" name="lessonsByDate" id="selectsDate">
                    <select onChange="this.form.submit()" class="select-date" id="byDate" name="lessonsByDate"
                            style="width: 135px; margin-right: 20px;">
                        <option value=all>Все даты</option>
                        <c:forEach items="${dates}" var="date">
                            <option value="${date}" ${date == lessonsByDate ? 'selected' : ''}>${date}</option>
                        </c:forEach>
                    </select>
                </form>
            </div>
        </div>

        <div class="selected_form">
            <div class="selected_part">
                Выбрать занятия по лектору: &nbsp
            </div>
            <div class="selected_part">
                <form action="${pageContext.request.contextPath}/lessons" name="lessonsByTutor" id="selectsTutor">
                    <select onChange="this.form.submit()" class="select-tutor" id="byTutor" name="lessonsByTutor"
                            style="width: 135px; margin-right: 20px;">
                        <option value=all>Все лекторы</option>
                        <c:forEach items="${allTutors}" var="tutor">
                            <option value="${tutor.getId()}" ${tutor.getId() == lessonsByTutor ? 'selected' : ''}>${tutor.getName()}</option>
                        </c:forEach>
                    </select>
                </form>
                &nbsp &nbsp
            </div>
        </div>

        <a href="${pageContext.request.contextPath}/lessons">Выбрать все занятия</a>
        <br>
        <br>

        <table border="1" cellspacing="0" cellpadding="3" align="center">
            <tr>
                <th>ID лекции</th>
                <th>Топик</th>
                <th>Дата</th>
                <th>Время лекции</th>
                <th>Название группы</th>
                <th>ФИО лектора</th>
                <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
                    <th>Редактировать лекцию</th>
                    <th>Удалить лекцию</th>
                </sec:authorize>
            </tr>
            <c:if test="${lessons != null}">
                <c:forEach items="${lessons}" var="lesson">
                    <tr>
                        <td align="center">${lesson.getLsn_id()}</td>
                        <td align="left">${lesson.getTopic()}</td>
                        <td align="center">${lesson.getDate()}</td>
                        <td align="center">${lesson.getTime()}</td>
                        <td align="left">${lesson.getGroup().getName()}</td>
                        <td align="left">${lesson.getTutor().getName()}</td>
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
                            <td align="center"><a
                                    href="${pageContext.request.contextPath}/lessons_edit?lesson_id=${lesson.getLsn_id()}">Редактировать</a>
                            </td>
                            <td align="center"><a
                                    href="${pageContext.request.contextPath}/lessons_delete?lesson_id=${lesson.getLsn_id()}">Удалить</a>
                            </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </c:if>

            <c:if test="${listLessonsByGroup != null}">
                <c:forEach items="${listLessonsByGroup}" var="lesson">
                    <tr>
                        <td align="center">${lesson.getLsn_id()}</td>
                        <td align="left">${lesson.getTopic()}</td>
                        <td align="center">${lesson.getDate()}</td>
                        <td align="center">${lesson.getTime()}</td>
                        <td align="left">${lesson.getGroup().getName()}</td>
                        <td align="left">${lesson.getTutor().getName()}</td>
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
                            <td align="center"><a
                                    href="${pageContext.request.contextPath}/lessons_edit">Редактировать</a></td>
                            <td align="center"><a
                                    href="${pageContext.request.contextPath}/lessons_delete?lesson_id=${lesson.getLsn_id()}">Удалить</a>
                            </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </c:if>

            <c:if test="${listLessonsByDate != null}">
                <c:forEach items="${listLessonsByDate}" var="lesson">
                    <tr>
                        <td align="center">${lesson.getLsn_id()}</td>
                        <td align="left">${lesson.getTopic()}</td>
                        <td align="center">${lesson.getDate()}</td>
                        <td align="center">${lesson.getTime()}</td>
                        <td align="left">${lesson.getGroup().getName()}</td>
                        <td align="left">${lesson.getTutor().getName()}</td>
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
                            <td align="center"><a
                                    href="${pageContext.request.contextPath}/lessons_edit">Редактировать</a></td>
                            <td align="center"><a
                                    href="${pageContext.request.contextPath}/lessons_delete?lesson_id=${lesson.getLsn_id()}">Удалить</a>
                            </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </c:if>

            <c:if test="${listLessonsByTutor != null}">
                <c:forEach items="${listLessonsByTutor}" var="lesson">
                    <tr>
                        <td align="center">${lesson.getLsn_id()}</td>
                        <td align="left">${lesson.getTopic()}</td>
                        <td align="center">${lesson.getDate()}</td>
                        <td align="center">${lesson.getTime()}</td>
                        <td align="left">${lesson.getGroup().getName()}</td>
                        <td align="left">${lesson.getTutor().getName()}</td>
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
                            <td align="center"><a
                                    href="${pageContext.request.contextPath}/lessons_edit">Редактировать</a></td>
                            <td align="center"><a
                                    href="${pageContext.request.contextPath}/lessons_delete?lesson_id=${lesson.getLsn_id()}">Удалить</a>
                            </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
            <p align="center">
                <a href="${pageContext.request.contextPath}/lessons_add">Добавить занятие</a>
            </p>
        </sec:authorize>
        <%
            String message = " ";
            if (request.getAttribute("lessons") == null &&
                    request.getAttribute("listLessonsByGroup") == null &&
                    request.getAttribute("listLessonsByDate") == null &&
                    request.getAttribute("listLessonsByTutor") == null &&
                    request.getAttribute("lessonsByTutor") == null &&
                    request.getAttribute("lessonsByDate") == null &&
                    request.getAttribute("lessonsByGroup") == null) {
                message = "Занятия не найдены";
            }
            if (request.getAttribute("listLessonsByDate") == null &&
                    request.getAttribute("lessonsByDate") != null) {
                message = "Занятия по данной дате не найдены";
            }
            if (request.getAttribute("listLessonsByGroup") == null &&
                    request.getAttribute("lessonsByGroup") != null) {
                message = "Занятия по данной группе не найдены";
            }
            if (request.getAttribute("listLessonsByTutor") == null &&
                    request.getAttribute("lessonsByTutor") != null) {
                message = "Занятия по данному лектору не найдены";
            }
        %>
        <%=message%>
    </div>
</div>
<%@ include file="../footer.jsp" %>
