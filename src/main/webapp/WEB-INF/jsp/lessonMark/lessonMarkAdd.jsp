<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="../header.jsp" %>
<%@ include file="../aside.jsp" %>
<div class="main">
    <div class="main_content">
        <H2>Добавление посящяемости</H2>
        <br>
    </div>
    <div class="form_lesson_add">
        <p align="center">Заполните все поля:
        <form name="lesson_add" action="${pageContext.request.contextPath}/lessonMark_add" method="post">
            <table border="2" align="center" cellspacing="5" cellpadding="15" rules="none">
                <tr>
                    <td align="left">Выберите лекцию:</td>
                    <td align="left">
                        <select class="select-lesson" id="byLesson" name="lessonsMarkByLessonOnCh" style="width: 135px; margin-right: 20px;">
                        <c:forEach items="${allLessons}" var="lessons">
                            <option  value="${lessons.getLsn_id()}" ${lessons.getLsn_id() == lessonsMarkByLessonOnCh ? 'selected' : ''}>${lessons.getTopic()}</option>
                        </c:forEach>
                        </select>
                    </td>
                    </td>
                </tr>

                <tr>
                    <td align="left">Выберите Студента:</td>
                    <td align="left">
                        <select  class="select-lesson" id="byUsers" name="lessonsMarkByuser" style="width: 135px; margin-right: 20px;">
                        <c:forEach items="${alluser}" var="users">
                            <option value="${users.getId()}" ${users.getId() == lessonsMarkByuser ? 'selected' : ''}>${users.getName()}</option>
                        </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="left">Присутствие на лекции:</td>
                    <td align="left">
                        <input id="visit" type="checkbox" name="visit">
                    </td>
                </tr>
                <tr>
                    <td align="left">Оценка:</td>
                    <td align="left">
                    <input id="mark" type="text" name="mark">
                    </td>
                </tr>
                <tr>
                    <td align="left"></option><input type="button" onclick="history.back();" value="Отмена"></td>
                    <td align="right"><input type="submit" value="Добавить"></td>
                </tr>
            </table>
        </form>
        </p>
    </div>
</div>
<%@ include file="../footer.jsp" %>
