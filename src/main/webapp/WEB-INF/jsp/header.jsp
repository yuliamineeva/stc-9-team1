<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>MyUniversity</title>
    <meta name="keywords" content="MyUniversity"/>
    <meta name="description" content="test site"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css"/>"/>
</head>

<body>

<div class="container">

    <header class="header">
        <div class="logo">My University - teamOne project</div>
        <div class="logout">
            <c:if test="${sessionScope.user != null}">
                <c:out value="Вы вошли как: "/>
                <c:out value="${sessionScope.login}"/>
                <c:out value="${sessionScope.userInfo}"/>
                <c:out value="|   "/>
                <a href="${pageContext.request.contextPath}/login?action=logout">Выйти</a>
            </c:if>
        </div>
    </header>