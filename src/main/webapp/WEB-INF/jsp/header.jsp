<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authentication property="principal.username" var="username"/>

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
            <c:if test="${username != null}">
                <c:out value="Вы вошли как: "/>
                <c:out value="${username}"/>
                <c:out value="|   "/>
                <a href="<c:url value='/j_spring_security_logout'/>">Logout</a>
            </c:if>
        </div>
    </header>