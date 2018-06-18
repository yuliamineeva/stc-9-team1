<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="ru.innopolis.stc9.t1.db.dao.LessonDAO" %>
<%@ page import="ru.innopolis.stc9.t1.db.dao.LessonDAOImpl" %>
<%@ page import="ru.innopolis.stc9.t1.pojo.Lesson" %>

<%@ include file="header.jsp" %>
<%@ include file="aside.jsp" %>
<div class="main">
    <div class="main_content">
        <%
            LessonDAO lessonDAO = new LessonDAOImpl();
        %>
        <table border="1" cellspacing="0" cellpadding="3" align="center">
            <tr>
                <th>ID лекции</th>
                <th>ID лектор</th>
                <th>ID группы</th>
                <th> Топик</th>
                <th>Дата</th>
                <th>Название группы</th>
                <th>ФИО лектора</th>
                <th>Редактировать лекцию</th>
                <th>Удалить лекцию</th>
            </tr>

            <%
                //todo сделать в виде таблицы
                Lesson lesson = lessonDAO.getLessonById(1);
                String message = lesson.toString();

            %>

        </table>
        <c:out value="Лекция 1: "/>

        <%=message%>
    </div>
</div>
<%@ include file="footer.jsp" %>
